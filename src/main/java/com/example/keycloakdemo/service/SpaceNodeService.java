package com.example.keycloakdemo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.keycloakdemo.config.SmartProjectBusinessException;
import com.example.keycloakdemo.dao.LocationDao;
import com.example.keycloakdemo.dao.SpaceNodeDao;
import com.example.keycloakdemo.dao.SpaceSetDao;
import com.example.keycloakdemo.dao.model.Location;
import com.example.keycloakdemo.dao.model.ProjectSpaceSyncDto;
import com.example.keycloakdemo.dao.model.SpaceNodeInfo;
import com.example.keycloakdemo.enums.ChildNodeSerialType;
import com.example.keycloakdemo.enums.LabelEnum;
import com.example.keycloakdemo.enums.NodeLevelEnum;
import com.example.keycloakdemo.enums.NodeTypeEnum;
import com.example.keycloakdemo.form.*;
import com.example.keycloakdemo.util.Constants;
import com.example.keycloakdemo.vo.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author : ex_yi.chen
 * @ClassName : SpaceNodeService
 * @Description : 空间节点相关处理类
 * @Date: 2022-07-19 17:58
 */
@Slf4j
@Service
public class SpaceNodeService {
    //private final RocketMqProducer rocketMQProducer;
    private final SpaceNodeDao spaceNodeDao;
    private final SpaceSetDao spaceSetDao;
    private final LocationDao locationDao;

    public SpaceNodeService(
            //RocketMqProducer rocketMQProducer,
                            SpaceNodeDao spaceNodeDao,
                            SpaceSetDao spaceSetDao,
                            LocationDao locationDao) {
        //this.rocketMQProducer = rocketMQProducer;
        this.spaceNodeDao = spaceNodeDao;
        this.spaceSetDao = spaceSetDao;
        this.locationDao = locationDao;
    }

    /**
     * 添加空间节点信息
     *
     * @param addSpaceNodeForm 添加空间节点入参
     */
    public void addNodeInfo(AddSpaceNodeForm addSpaceNodeForm) {
        SpaceNodeInfo spaceNodeInfo = SpaceNodeInfo.builder()
                .level(addSpaceNodeForm.getLevel())
                .eid(addSpaceNodeForm.getEid())
                .spaceSetId(addSpaceNodeForm.getSpaceSetId())
                .build();
        SpaceSetVO spaceSetVO = spaceSetDao.spaceSetDetail(addSpaceNodeForm.getSpaceSetId());
        List<SpaceNodeInfo> list;
        NodeTypeEnum code = NodeTypeEnum.getCode(addSpaceNodeForm.getType());
        if (ObjectUtils.isEmpty(code)) {
            throw new SmartProjectBusinessException("传入的节点添加方式不匹配");
        }
        switch (code) {
            //添加子节点
            case CHILD_NODE:
                if (LabelEnum.OTHER_LABEL.getLabelCode() == addSpaceNodeForm.getLabel()) {
                    List<Location> locations = locationDao.locationByNode(addSpaceNodeForm.getId());
                    if (!CollectionUtils.isEmpty(locations)) {
                        throw new SmartProjectBusinessException("该节点已绑定空间，请先解绑设备后再添加节点");
                    }
                }
                if (!StringUtils.isEmpty(addSpaceNodeForm.getName())) {
                    spaceNodeInfo.setName(addSpaceNodeForm.getName());
                } else {
                    list = spaceNodeDao.list(spaceNodeInfo);
                    if (list.size() >= Constants.ORDER_NUM) {
                        throw new SmartProjectBusinessException("节点已达到最大数值");
                    }
                    int names = list.size() + NumberUtils.INTEGER_ONE;
                    spaceNodeInfo.setName("节点" + names);
                }
                spaceNodeInfo.setParentId(addSpaceNodeForm.getParentId());
                list = spaceNodeDao.list(spaceNodeInfo);
                spaceNodeInfo.setIsLast(NumberUtils.INTEGER_ZERO);
                spaceNodeInfo.setOrderNum(list.size() + NumberUtils.INTEGER_ONE);
                spaceNodeDao.updateOrderNum(SpaceNodeInfo.builder()
                        .parentId(addSpaceNodeForm.getParentId())
                        .orderNum(list.size() + NumberUtils.INTEGER_ONE)
                        .build());
                if (spaceNodeInfo.getIsLast().equals(NumberUtils.INTEGER_ZERO)) {
                    String lastName = getLastNameByNodeId(spaceNodeInfo, "");
                    spaceNodeInfo.setLocationName(lastName);
                }
                spaceNodeDao.add(spaceNodeInfo);
                //当添加的子节点是公寓时。 并且只有在第三层的节点添加不做同步
                //当添加第三层数据时。会导致添加两条room的数据
                if (spaceSetVO.getCode() == LabelEnum.APARTMENT_LABEL.getLabelCode()) {
                    if (spaceNodeInfo.getLevel() == NodeLevelEnum.FOUR.getLevel() ||
                            spaceNodeInfo.getLevel() == NodeLevelEnum.TWO.getLevel()) {
                        addByNodeLevel(spaceNodeInfo);
                    }
                } else if (spaceSetVO.getCode() == LabelEnum.DORM_LABEL.getLabelCode()) {
                    spaceNodeInfo.setLevel(spaceNodeInfo.getLevel() + NumberUtils.INTEGER_ONE);
                    addByNodeLevel(spaceNodeInfo);
                } else if (spaceSetVO.getCode() == LabelEnum.ACCESS_LABEL.getLabelCode()) {
                    addRoomNode(spaceNodeInfo);
                } else if (spaceSetVO.getCode() == LabelEnum.OTHER_LABEL.getLabelCode()) {
                    addRoomNode(spaceNodeInfo);
                }
                SpaceNodeVO parentNode = spaceNodeDao.nodeInfoById(addSpaceNodeForm.getParentId());
                if (!ObjectUtils.isEmpty(parentNode)) {
                    spaceNodeDao.updateNodeName(SpaceNodeInfo.builder()
                            .id(parentNode.getNodeId())
                            .isLast(NumberUtils.INTEGER_ONE)
                            .build());
                }
                Long parentId = spaceNodeInfo.getId();
                insertNode(addSpaceNodeForm, spaceNodeInfo, spaceSetVO, parentId);
                break;
            case UP_NODE:
            case DOWN_NODE:
                spaceNodeInfo.setParentId(addSpaceNodeForm.getParentId());
                list = spaceNodeDao.list(spaceNodeInfo);
                if (list.size() >= Constants.ORDER_NUM) {
                    throw new SmartProjectBusinessException("节点已达到最大数值");
                }
                SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(addSpaceNodeForm.getId());
                int orderNum = spaceNodeVO.getOrderNum();
                int i = list.size() + 1;
                spaceNodeInfo.setName("节点" + i);
                if (addSpaceNodeForm.getType() == NodeTypeEnum.DOWN_NODE.getType()) {
                    orderNum = orderNum + NumberUtils.INTEGER_ONE;
                }
                spaceNodeInfo.setParentId(addSpaceNodeForm.getParentId());
                spaceNodeInfo.setOrderNum(orderNum);
                spaceNodeInfo.setIsLast(NumberUtils.INTEGER_ZERO);
                //修改节点排序 当上方添加时。先更新当前节点以后数值。再把新增的节点插入当前位置
                spaceNodeDao.updateOrderNum(SpaceNodeInfo.builder()
                        .parentId(addSpaceNodeForm.getParentId())
                        .orderNum(orderNum)
                        .build());
                if (spaceNodeInfo.getIsLast().equals(NumberUtils.INTEGER_ZERO)) {
                    String lastName = getLastNameByNodeId(spaceNodeInfo, "");
                    spaceNodeInfo.setLocationName(lastName);
                }
                parentId = spaceNodeDao.add(spaceNodeInfo);
                //需要判断业务标签 防止公寓在第三层的时候直接插入space数据 第三层在业务层不存在单独的id
                if (spaceSetVO.getCode() == LabelEnum.APARTMENT_LABEL.getLabelCode() &&
                        spaceNodeInfo.getLevel() > NodeLevelEnum.TWO.getLevel()) {
                    if (spaceNodeInfo.getLevel() == NodeLevelEnum.FOUR.getLevel()) {
                        addByNodeLevel(spaceNodeInfo);
                    }
                } else if(spaceSetVO.getCode() == LabelEnum.DORM_LABEL.getLabelCode()){
                    spaceNodeInfo.setLevel(spaceNodeInfo.getLevel() + NumberUtils.INTEGER_ONE);
                }else {
                    addByNodeLevel(spaceNodeInfo);
                }
                //判断是否需要添加子级节点
                if (LabelEnum.DORM_LABEL.getLabelCode() == addSpaceNodeForm.getLabel() &&
                        addSpaceNodeForm.getLevel() != NodeLevelEnum.THREE.getLevel()) {
                    spaceNodeDao.updateNodeName(SpaceNodeInfo.builder()
                            .id(parentId)
                            .isLast(NumberUtils.INTEGER_ONE)
                            .build());
                    insertNode(addSpaceNodeForm, spaceNodeInfo, spaceSetVO, parentId);
                }
                if (LabelEnum.APARTMENT_LABEL.getLabelCode() == addSpaceNodeForm.getLabel() &&
                        addSpaceNodeForm.getLevel() != NodeLevelEnum.FOUR.getLevel()) {
                    spaceNodeDao.updateNodeName(SpaceNodeInfo.builder()
                            .id(parentId)
                            .isLast(NumberUtils.INTEGER_ONE)
                            .build());
                    insertNode(addSpaceNodeForm, spaceNodeInfo, spaceSetVO, parentId);
                }
                break;
            default:
                break;
        }
    }

    private String getLastNameByNodeId(SpaceNodeInfo spaceNodeInfo, String name) {
        if (spaceNodeInfo.getParentId().equals(NumberUtils.LONG_ZERO)) {
            if (StringUtils.isEmpty(name)) {
                name = spaceNodeInfo.getName() + "/";
            }
        } else {
            SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(spaceNodeInfo.getParentId());
            name = spaceNodeVO.getName() + "/" + spaceNodeInfo.getName();
            return getLastNameByNodeId(SpaceNodeInfo.builder()
                    .id(spaceNodeVO.getNodeId())
                    .parentId(spaceNodeVO.getParentId())
                    .name(name)
                    .level(spaceNodeInfo.getLevel())
                    .build(), name);
        }
        return name;
    }

    /**
     * 新增节点
     * 每次新增返回的节点id当做下一级 的父级节点插入
     * addSpaceNodeForm.getLevel() 根据传入的等级判断需要添加几层节点
     *
     * @param addSpaceNodeForm 添加入参
     * @param spaceNodeInfo    初始化节点信息
     * @param spaceSetVO       空间集信息
     * @param parentId         父级节点
     */
    private void insertNode(AddSpaceNodeForm addSpaceNodeForm, SpaceNodeInfo spaceNodeInfo, SpaceSetVO spaceSetVO, Long parentId) {
        Integer level = addSpaceNodeForm.getLevel() + 1;
        if (spaceSetVO.getCode() == LabelEnum.APARTMENT_LABEL.getLabelCode()) {
            for (int i = level; i <= 4; i++) {
                parentId = getParentId(spaceNodeInfo, parentId, i, spaceSetVO.getCode());
            }
        } else if (spaceSetVO.getCode() == LabelEnum.DORM_LABEL.getLabelCode()) {
            for (int i = level; i <= 3; i++) {
                parentId = getParentId(spaceNodeInfo, parentId, i, spaceSetVO.getCode());
            }
        }
    }

    /**
     * 返回插入节点id
     * 新增空间节点并返回插入的节点id
     *
     * @param spaceNodeInfo 节点信息
     * @param parentId      父级节点
     * @param i             等级 等同于循环的层数
     * @param code          业务标签
     * @return
     */
    private Long getParentId(SpaceNodeInfo spaceNodeInfo, Long parentId, int i, int code) {
        SpaceNodeInfo build = SpaceNodeInfo.builder()
                .parentId(parentId)
                .level(i)
                .isLast(NumberUtils.INTEGER_ZERO)
                .spaceSetId(spaceNodeInfo.getSpaceSetId())
                .eid(spaceNodeInfo.getEid())
                .build();
        List<SpaceNodeInfo> nodeInfos = spaceNodeDao.list(SpaceNodeInfo.builder()
                .level(i)
                .spaceSetId(spaceNodeInfo.getSpaceSetId())
                .parentId(parentId)
                .build());
        int nodeName = nodeInfos.size() + 1;
        build.setName("节点" + nodeName);
        build.setOrderNum(nodeName);
        spaceNodeDao.updateOrderNum(SpaceNodeInfo.builder()
                .parentId(parentId)
                .orderNum(build.getOrderNum())
                .build());
        if (build.getIsLast().equals(NumberUtils.INTEGER_ZERO)) {
            String lastName = getLastNameByNodeId(build, "");
            build.setLocationName(lastName);
        }
        log.info("SpaceNodeService getParentId build | {}", build);
        Long addId = spaceNodeDao.add(build);
        if (code == LabelEnum.APARTMENT_LABEL.getLabelCode()) {
            if (build.getLevel() == NodeLevelEnum.FOUR.getLevel()) {
                addByNodeLevel(build);
            }
        } else if (code == LabelEnum.DORM_LABEL.getLabelCode()) {
            build.setLevel(build.getLevel() + NumberUtils.INTEGER_ONE);
            addByNodeLevel(build);
        }

        spaceNodeDao.updateNodeName(SpaceNodeInfo.builder()
                .id(parentId)
                .isLast(NumberUtils.INTEGER_ONE)
                .locationName(build.getLocationName())
                .build());
        parentId = addId;
        return parentId;
    }

    /**
     * 修改空间节点名称
     *
     * @param updateNodeForm 节点名称修改入参
     */
    public void updateNodeName(UpdateNodeForm updateNodeForm) {
        List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder()
                .level(updateNodeForm.getLevel())
                .parentId(updateNodeForm.getParentId())
                .spaceSetId(updateNodeForm.getSpaceId())
                .eid(updateNodeForm.getEid())
                .name(updateNodeForm.getNewName())
                .build());
        if (list.size() > NumberUtils.INTEGER_ZERO && !NumberUtils.INTEGER_ONE.equals(updateNodeForm.getLevel())) {
            throw new SmartProjectBusinessException("该节点名称已经在同级存在!");
        }
        SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(updateNodeForm.getId());
        SpaceNodeInfo build = SpaceNodeInfo.builder()
                .id(updateNodeForm.getId())
                .name(updateNodeForm.getNewName())
                .level(updateNodeForm.getLevel())
                .parentId(updateNodeForm.getParentId())
                .build();
        spaceNodeDao.updateNodeName(build);

        List<SpaceNodeInfo> spaceNodeInfos = childNodeByParentId(new ArrayList<>(), updateNodeForm.getId());
        if (!CollectionUtils.isEmpty(spaceNodeInfos)) {
            spaceNodeInfos.stream().forEach(spaceNodeInfo -> {
                String lastNameByNodeId = getLastNameByNodeId(spaceNodeInfo, "");
                String locationName = spaceNodeInfo.getLocationName();
                if (!StringUtils.isEmpty(locationName)) {
                   spaceNodeInfo.setLocationName(lastNameByNodeId);
                    spaceNodeDao.updateNodeName(spaceNodeInfo);
                }
            });
        } else {
            if (spaceNodeVO.getIsLast().equals(NumberUtils.INTEGER_ZERO)) {
                String lastName = getLastNameByNodeId(build, "");
                build.setLocationName(lastName);
            }
            spaceNodeDao.updateNodeName(build);
        }
        build.setIsLast(spaceNodeVO.getIsLast());
        build.setSpaceSetId(spaceNodeVO.getSpaceSetId());
        updateNodeByLevel(build);
    }


    /**
     * 空间节点列表获取
     *
     * @param nodeListForm 空间节点列表入参
     * @return 空间节点列表信息
     */
    public PageResult<SpaceNodeInfo> nodeList(NodeListForm nodeListForm) {
        SpaceNodeInfo spaceNodeInfo = new SpaceNodeInfo();
        BeanUtils.copyProperties(nodeListForm, spaceNodeInfo);
        PageResult<SpaceNodeInfo> pageResult = new PageResult<>();
        int count = spaceNodeDao.count(spaceNodeInfo);
        log.info("nodeList count | {}", count);
        if (NumberUtils.INTEGER_ZERO == count) {
            pageResult.setTotal(count);
            pageResult.setData(new ArrayList<>());
            return pageResult;
        }
        List<SpaceNodeInfo> list = spaceNodeDao.list(spaceNodeInfo);
        log.info("nodeList list size | {}", list.size());
        pageResult.setData(list);
        pageResult.setTotal(list.size());
        return pageResult;
    }

    /**
     * 所有空间节点列表获取
     *
     * @param allNodeListForm 空间节点列表入参
     * @return 空间节点列表信息
     */
    public List<SpaceNodeInfo> allNodeList(AllNodeListForm allNodeListForm) {
        SpaceNodeInfo build = SpaceNodeInfo.builder()
                .spaceSetId(allNodeListForm.getSpaceSetId())
                .build();
        List<SpaceNodeInfo> list = spaceNodeDao.list(build);
        return list;
    }

    /**
     * 删除空间节点
     *
     * @param id 节点id
     */
    public void deleteNode(Long id) {
        //节点id可能为任意节点
        //节点id删除
        SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(id);
        SpaceSetVO spaceSetVO = spaceSetDao.spaceSetDetail(spaceNodeVO.getSpaceSetId());
        int code = spaceSetVO.getCode();
        if (ObjectUtils.isEmpty(spaceNodeVO)) {
            throw new SmartProjectBusinessException("删除的节点信息不存在");
        }
        //判定是否为最后一个空间节点
        if (NumberUtils.INTEGER_ZERO.equals(spaceNodeVO.getIsLast())) {
            List<Location> locations = locationDao.locationByNode(id);
            if (!CollectionUtils.isEmpty(locations)) {
                throw new SmartProjectBusinessException("该空间下存在已经绑定设备,请解绑后再删除。");
            }
        } else {
            List<SpaceNodeInfo> childNodeInfo = childNodeByParentId(new ArrayList<>(), id);
            if (!CollectionUtils.isEmpty(childNodeInfo)) {
                for (SpaceNodeInfo node : childNodeInfo) {
                    if (NumberUtils.INTEGER_ZERO.equals(node.getIsLast())) {
                        List<Location> locations = locationDao.locationByNode(node.getId());
                        if (!CollectionUtils.isEmpty(locations)) {
                            throw new SmartProjectBusinessException("该空间下存在已经绑定设备,请解绑后再删除。");
                        }
                    }
                }
            }
        }
        if (spaceNodeVO.getParentId().equals(NumberUtils.LONG_ZERO)) {
            List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder()
                    .spaceSetId(spaceNodeVO.getSpaceSetId())
                    .build());
            log.info("SpaceNodeService deleteNode parentNode is zero | {}", list.size());

            for (SpaceNodeInfo spaceNodeInfo : list) {
                switch (LabelEnum.getByCode(code)){
                    case APARTMENT_LABEL:
                        deleteByNodeLevel(spaceNodeInfo);
                        break;
                    case DORM_LABEL:
                        spaceNodeInfo.setLevel(spaceNodeInfo.getLevel() + NumberUtils.INTEGER_ONE);
                        deleteByNodeLevel(spaceNodeInfo);
                        break;
                    default:
                        deleteSpace(spaceNodeInfo.getId());
                        break;
                }
            }
            spaceNodeDao.deleteBySpaceSetId(spaceNodeVO.getSpaceSetId());
            return;
        }


        if (code == LabelEnum.DORM_LABEL.getLabelCode() || code == LabelEnum.APARTMENT_LABEL.getLabelCode()) {
            List<SpaceNodeInfo> childNodeList = spaceNodeDao.list(SpaceNodeInfo.builder()
                    .level(spaceNodeVO.getLevel())
                    .spaceSetId(spaceNodeVO.getSpaceSetId())
                    .build());
            if (NumberUtils.INTEGER_ONE == childNodeList.size() && !spaceNodeVO.getParentId().equals(NumberUtils.LONG_ZERO)) {
                throw new SmartProjectBusinessException("只存在单个节点无法删除");
            }
            List<SpaceNodeInfo> childNodeInfo = childNodeByParentId(new ArrayList<>(), spaceNodeVO.getNodeId());
            if (!CollectionUtils.isEmpty(childNodeInfo)) {
                for (SpaceNodeInfo node : childNodeInfo) {
                    spaceNodeDao.deleteNode(node.getId());
                    log.info("SpaceNodeService deleteNode all childNode | {}", node.getId());
                    deleteByNodeLevel(node);
                }
            }
            deleteParentNode(id);
            spaceNodeDao.deleteNode(id);
        } else {
            //根据当前删除的节点查询是否存在子节点
            List<SpaceNodeInfo> spaceNodeInfos = childNodeByParentId(new ArrayList<>(), id);
            log.info("deleteNode childNodeByParentId |{}", spaceNodeInfos.size());
            if (!CollectionUtils.isEmpty(spaceNodeInfos)) {
                for (SpaceNodeInfo info : spaceNodeInfos) {
                    spaceNodeDao.deleteNode(info.getId());
                    log.info("SpaceNodeService deleteNode childNodeByParentId | {}", info.getId());
                    deleteByNodeLevel(info);
                }
            }
            //查询是否存在同级节点 不存在则更改父级节点为最后节点
            List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder()
                    .parentId(spaceNodeVO.getParentId())
                    .spaceSetId(spaceNodeVO.getSpaceSetId())
                    .build());
            // 判断当前父级节点下是否存在有多个字节点 也就是判断当前节点是否只有一个
            // 如果只有一个则需要修改当前节点的父级节点为不是最后一层节点
            if (!CollectionUtils.isEmpty(list) && list.size() == NumberUtils.INTEGER_ONE) {
                spaceNodeDao.updateNodeName(SpaceNodeInfo.builder()
                        .isLast(NumberUtils.INTEGER_ZERO)
                        .id(spaceNodeVO.getParentId())
                        .build());
            }
            spaceNodeDao.deleteNode(id);
            log.info("SpaceNodeService deleteNode deleteNode | {}", spaceNodeVO);
            deleteByNodeLevel(SpaceNodeInfo.builder()
                    .id(spaceNodeVO.getNodeId())
                    .level(spaceNodeVO.getLevel())
                    .parentId(spaceNodeVO.getParentId())
                    .build());
        }
    }

    /**
     * 循环删除父级节点
     *
     * @param id
     */
    private void deleteParentNode(Long id) {
        SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(id);
        if (!spaceNodeVO.getParentId().equals(NumberUtils.LONG_ZERO)) {
            List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder()
                    .spaceSetId(spaceNodeVO.getSpaceSetId())
                    .parentId(spaceNodeVO.getParentId())
                    .build());
            if (!CollectionUtils.isEmpty(list) && list.size() > NumberUtils.INTEGER_ONE) {
                spaceNodeDao.deleteNode(spaceNodeVO.getNodeId());
                deleteByNodeLevel(SpaceNodeInfo.builder()
                        .level(spaceNodeVO.getLevel())
                        .id(spaceNodeVO.getNodeId())
                        .parentId(spaceNodeVO.getParentId())
                        .eid(spaceNodeVO.getEid())
                        .build());
            } else {
                deleteParentNode(spaceNodeVO.getParentId());
                spaceNodeDao.deleteNode(spaceNodeVO.getParentId());
                log.info("SpaceNodeService deleteParentNode and delete last node | {}", spaceNodeVO.getNodeId());
                deleteByNodeLevel(SpaceNodeInfo.builder()
                        .id(spaceNodeVO.getNodeId())
                        .level(spaceNodeVO.getLevel())
                        .parentId(spaceNodeVO.getParentId())
                        .build());
            }
        }
    }


    /**
     * 查询父级节点下的字节点
     *
     * @param resultList 传入一个需要接收结果的空列表
     * @param parentId   父级id
     * @return 父级节点下的所有子节点信息
     */
    public List<SpaceNodeInfo> childNodeByParentId(ArrayList<SpaceNodeInfo> resultList, Long parentId) {
        List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder().parentId(parentId).build());
        if (!CollectionUtils.isEmpty(list)) {
            for (SpaceNodeInfo nodeInfo : list) {
                resultList.add(nodeInfo);
                childNodeByParentId(resultList, nodeInfo.getId());
            }
        }
        return resultList;
    }

    /**
     * 批量添加节点信息
     *
     * @param addBatchNodeForm 批量添加入参
     */
    public void addBatchNode(AddBatchNodeForm addBatchNodeForm) {
        log.info("addBatchNode param |{}", addBatchNodeForm);
        Long spaceId = addBatchNodeForm.getSpaceSetId();
        List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder()
                .spaceSetId(spaceId)
                .parentId(NumberUtils.LONG_ZERO)
                .build());
        if (!CollectionUtils.isEmpty(list)) {
            throw new SmartProjectBusinessException("该空间集下已存在空间节点");
        }
        SpaceNodeInfo build = SpaceNodeInfo.builder()
                .spaceSetId(spaceId)
                .eid(addBatchNodeForm.getEid())
                .parentId(NumberUtils.LONG_ZERO)
                .level(NumberUtils.INTEGER_ONE)
                .isLast(NumberUtils.INTEGER_ONE)
                .orderNum(list.size() + 1)
                .name(addBatchNodeForm.getName())
                .locationName(addBatchNodeForm.getName())
                .build();
        Long firstNodeId = spaceNodeDao.add(build);
        log.info(" addBatchNode build | {}", build);
        if (firstNodeId > NumberUtils.LONG_ZERO) {
            if(addBatchNodeForm.getLabel() == LabelEnum.DORM_LABEL.getLabelCode()){
                build.setLevel(build.getLevel()+NumberUtils.INTEGER_ONE);
            }
            addByNodeLevel(build);
        }
        // 获取二级节点数
        ChildNodeProperty twoChildNodeProperty = addBatchNodeForm.getTwoChildNodeProperty();
        ChildNodeProperty threeChildNodeProperty = addBatchNodeForm.getThreeChildNodeProperty();
        Integer childNodeCount = twoChildNodeProperty.getChildNodeCount();
        //判断批量添加的子节点数是否大于0 如果大于0 则根据总数进行循环插入2级节点
        // 如果三级节点也大于0 在二级节点循环内 插入3级节点
        if (childNodeCount > 0) {
            for (int i = 1; i <= childNodeCount; i++) {
                log.info(" childNodeCount | {}", i);
                String name;
                if (twoChildNodeProperty.getNodeType() == ChildNodeSerialType.LETTER_TYPE.getSerialType()) {
                    name = convert(i - 1);
                } else if (twoChildNodeProperty.getNodeType() == ChildNodeSerialType.TEXT_TYPE.getSerialType()) {
                    name = "节点" + i;
                } else {
                    name = i + NumberUtils.INTEGER_ZERO + "";
                }
                log.info(" gentler node Name |{}", name);
                SpaceNodeInfo twoNodeInfo = SpaceNodeInfo.builder()
                        .name(name + twoChildNodeProperty.getNodeSuffix())
                        .eid(addBatchNodeForm.getEid())
                        .spaceSetId(spaceId)
                        .orderNum(i)
                        .level(NodeLevelEnum.TWO.getLevel())
                        .isLast(NumberUtils.INTEGER_ONE)
                        .parentId(build.getId())
                        .locationName(addBatchNodeForm.getName() + "/" + name + twoChildNodeProperty.getNodeSuffix())
                        .build();

                Long twoNodeId = spaceNodeDao.add(twoNodeInfo);
                //判断返回的二级节点(houseId是否大于0 大于则证明添加成功)
                if (twoNodeId > NumberUtils.LONG_ZERO) {
                    if(addBatchNodeForm.getLabel() == LabelEnum.DORM_LABEL.getLabelCode()){
                        twoNodeInfo.setLevel(twoNodeInfo.getLevel()+NumberUtils.INTEGER_ONE);
                    }
                    addByNodeLevel(twoNodeInfo);
                }
                //判断三级节点参数是否大于0 大于则开始插入三级节点
                if (threeChildNodeProperty.getChildNodeCount() > 0) {
                    for (int three = 1; three <= threeChildNodeProperty.getChildNodeCount(); three++) {
                        String threeNodeName;
                        Integer extendsHigherNode = threeChildNodeProperty.getExtendsHigherNode();
                        if (threeChildNodeProperty.getNodeType() == ChildNodeSerialType.LETTER_TYPE.getSerialType()) {
                            threeNodeName = convert(three - 1);
                        } else if (threeChildNodeProperty.getNodeType() == ChildNodeSerialType.TEXT_TYPE.getSerialType()) {
                            threeNodeName = "节点" + three;
                        } else {
                            if (three < 10 && addBatchNodeForm.getLabel() == LabelEnum.DORM_LABEL.getLabelCode()) {
                                threeNodeName = NumberUtils.INTEGER_ZERO + "" + three;
                            } else {
                                threeNodeName = three + "";
                            }
                        }
                        //判断是否继承上级节点编号 编号就是对应的数值
                        if (extendsHigherNode.equals(NumberUtils.INTEGER_ONE)) {
                            threeNodeName = name + threeNodeName;
                        }
                        SpaceNodeInfo threeNodeInfo = SpaceNodeInfo.builder()
                                .name(threeNodeName + threeChildNodeProperty.getNodeSuffix())
                                .eid(addBatchNodeForm.getEid())
                                .spaceSetId(spaceId)
                                .orderNum(three)
                                .level(NodeLevelEnum.THREE.getLevel())
                                .isLast(NumberUtils.INTEGER_ZERO)
                                .parentId(twoNodeId)
                                .build();
                        if (threeNodeInfo.getIsLast().equals(NumberUtils.INTEGER_ZERO)) {
                            String lastName = getLastNameByNodeId(threeNodeInfo, "");
                            threeNodeInfo.setLocationName(lastName);
                        }
                        Long threeNodeId = spaceNodeDao.add(threeNodeInfo);

                        //判断是否是公寓 公寓有4级 如果不存在4级节点 则只同步空间信息 如果存在
                        //则将楼层也放置到space中
                        if (addBatchNodeForm.getLabel() == LabelEnum.APARTMENT_LABEL.getLabelCode()) {
                            ChildNodeProperty fourChildNodeProperty = addBatchNodeForm.getFourChildNodeProperty();
                            if (fourChildNodeProperty.getChildNodeCount() > 0) {
                                for (int four = 1; four <= fourChildNodeProperty.getChildNodeCount(); four++) {
                                    String fourNodeName;
                                    Integer extendsHigherFourNode = fourChildNodeProperty.getExtendsHigherNode();
                                    if (fourChildNodeProperty.getNodeType() == ChildNodeSerialType.LETTER_TYPE.getSerialType()) {
                                        fourNodeName = convert(four - 1);
                                    } else if (ChildNodeSerialType.TEXT_TYPE.getSerialType() == fourChildNodeProperty.getNodeType()) {
                                        fourNodeName = "节点" + four;
                                    } else {
                                        //节点小于10时 需要在前面加0
                                        if (four < 10) {
                                            fourNodeName = NumberUtils.INTEGER_ZERO + "" + four;
                                        } else {
                                            fourNodeName = four + "";
                                        }
                                    }

                                    if (extendsHigherFourNode.equals(NumberUtils.INTEGER_ONE)) {
                                        fourNodeName = threeNodeName + fourNodeName;
                                    }
                                    //每添加一层子节点 需要把上级节点 修改为 不是最后一层
                                    spaceNodeDao.updateNodeName(SpaceNodeInfo.builder().id(threeNodeId).isLast(NumberUtils.INTEGER_ONE).build());
                                    SpaceNodeInfo fourNodeInfo = SpaceNodeInfo.builder()
                                            .name(fourNodeName + fourChildNodeProperty.getNodeSuffix())
                                            .eid(addBatchNodeForm.getEid())
                                            .spaceSetId(spaceId)
                                            .level(NodeLevelEnum.FOUR.getLevel())
                                            .orderNum(four)
                                            .isLast(NumberUtils.INTEGER_ZERO)
                                            .parentId(threeNodeId)
                                            .build();
                                    if (fourNodeInfo.getIsLast().equals(NumberUtils.INTEGER_ZERO)) {
                                        String lastName = getLastNameByNodeId(fourNodeInfo, "");
                                        fourNodeInfo.setLocationName(lastName);
                                    }
                                    Long fourNodeId = spaceNodeDao.add(fourNodeInfo);
                                    if (fourNodeId > NumberUtils.LONG_ZERO) {
                                        addByNodeLevel(SpaceNodeInfo.builder()
                                                .id(fourNodeId)
                                                .eid(addBatchNodeForm.getEid())
                                                .spaceSetId(spaceId)
                                                .parentId(fourNodeInfo.getParentId())
                                                .level(fourNodeInfo.getLevel())
                                                .name(fourNodeInfo.getName())
                                                .build());
                                    }
                                }
                            }
                        } else {
                            if (threeNodeId > NumberUtils.LONG_ZERO) {
                                if(addBatchNodeForm.getLabel() == LabelEnum.DORM_LABEL.getLabelCode()){
                                    threeNodeInfo.setLevel(threeNodeInfo.getLevel()+NumberUtils.INTEGER_ONE);
                                }
                                addByNodeLevel(threeNodeInfo);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 将数字转换成字母 数字从0开始
     * 传入的数据将通过char转换成字母
     *
     * @param counter 传入数字
     * @return 返回字母
     */
    public static String convert(int counter) {
        String convertResult = StringUtils.EMPTY;
        if (counter > Constants.LETTER) {
            int num = counter - Constants.LETTER;
            for (int i = 1; i <= num; i++) {
                convertResult = String.valueOf((char) (65)).toUpperCase() + "(" + i + ")";
            }
        } else {
            for (int x = 0; x <= counter; x++) {
                String result = "";
                int quotient, remainder;
                quotient = x;
                while (quotient >= 0) {
                    remainder = quotient % 26;
                    result = (char) (remainder + 65) + result;
                    int i = quotient / 26;
                    int floor = (int) Math.floor(i);
                    quotient = floor - 1;
                }
                if (counter == x) {
                    convertResult = result;
                    return convertResult;
                }
            }
        }
        return convertResult;
    }

    /**
     * 导出空间集下的空间信息
     *
     * @param exportNodeForm 导出空间信息入参
     */
    public void exportNodeInfo(ExportNodeForm exportNodeForm, HttpServletResponse response, HttpServletRequest request) throws Exception {
        FileOutputStream output = new FileOutputStream("d:\\workbook.xls");
        SpaceSetVO spaceSetVO = spaceSetDao.spaceSetDetail(exportNodeForm.getSpaceId());
        if (ObjectUtils.isEmpty(spaceSetVO)) {
            throw new SmartProjectBusinessException("该空间集信息不存在");
        }
        String[] titles;
        if (LabelEnum.APARTMENT_LABEL.getLabelCode() == spaceSetVO.getCode()) {
            titles = new String[]{"一级节点", "二级节点", "三级节点", "四级节点"};
        } else if (LabelEnum.DORM_LABEL.getLabelCode() == spaceSetVO.getCode()) {
            titles = new String[]{"一级节点", "二级节点", "三级节点"};
        } else if (LabelEnum.ACCESS_LABEL.getLabelCode() == spaceSetVO.getCode()) {
            titles = new String[]{"一级节点", "二级节点", "三级节点", "四级节点", "五级节点", "六级节点", "七级节点", "八级节点", "九级节点"};
        } else {
            titles = new String[]{"一级节点", "二级节点", "三级节点", "四级节点", "五级节点", "六级节点", "七级节点", "八级节点", "九级节点"};
        }
        //查询当前空间集下所有节点数据
        List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder()
                .spaceSetId(exportNodeForm.getSpaceId())
                .eid(exportNodeForm.getEid())
                .build());
        log.info("exportNodeInfo list | {}", list.size());
        if (CollectionUtils.isEmpty(list)) {
            throw new SmartProjectBusinessException("导出的空间不能为空");
        }
        // 根据层级进行分组 分组出来的数据 integer大小来判断有几层数据
        Map<Integer, List<SpaceNodeInfo>> allNodeInfos = list.stream().collect(Collectors.groupingBy(SpaceNodeInfo::getLevel));
        //获取最后一层数据
        List<SpaceNodeInfo> childNode = allNodeInfos.get(allNodeInfos.size());
        if (LabelEnum.APARTMENT_LABEL.getLabelCode() != spaceSetVO.getCode() ||
                LabelEnum.DORM_LABEL.getLabelCode() != spaceSetVO.getCode()) {
            /**allNodeInfos中存储的是数据对应的节点层级 因此需要从1开始 又因为在之前就已经取出最后一层的节点
             所以在这里循环只需要取出前面的层级数据 遍历判断是否是最后一个节点
             是最后一个节点就加入到 childNode数组中进行导出*/
            for (int i = 1; i <= allNodeInfos.size() - 1; i++) {
                List<SpaceNodeInfo> spaceNodeInfos = allNodeInfos.get(i);
                for (SpaceNodeInfo spaceNodeInfo : spaceNodeInfos) {
                    if (NumberUtils.INTEGER_ZERO.equals(spaceNodeInfo.getIsLast())) {
                        childNode.add(spaceNodeInfo);
                    }
                }
            }
        }
        childNode = childNode.stream().sorted(Comparator.comparing(SpaceNodeInfo::getParentId)
                .thenComparing(SpaceNodeInfo::getOrderNum)).collect(Collectors.toList());
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook();
        XSSFSheet hssfSheet = hssfWorkbook.createSheet(spaceSetVO.getName());
        XSSFRow row = hssfSheet.createRow(0);

        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
            for (int j = 0; j < childNode.size(); j++) {
                SpaceNodeInfo spaceNodeInfo = childNode.get(j);
                XSSFRow otherRow = hssfSheet.createRow(j + 1);
                hssfWorkbook = exportInfo(hssfWorkbook, spaceNodeInfo, otherRow, allNodeInfos);
            }
        }
        hssfWorkbook.write(output);
        response.setHeader("Content-Disposition", "attachment;filename="+ System.currentTimeMillis() +".xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputStream = response.getOutputStream();
        hssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }

    /**
     * 传入最后一个节点 根据最后一个节点进行 递归查询父级节点
     * 依次往表格中 插入数据
     *
     * @param hssfWorkbook  表格信息
     * @param spaceNodeInfo 节点信息
     * @param otherRow      行
     * @return 表格
     */
    public XSSFWorkbook exportInfo(XSSFWorkbook hssfWorkbook, SpaceNodeInfo spaceNodeInfo,
                                   XSSFRow otherRow, Map<Integer, List<SpaceNodeInfo>> map) {
        XSSFCell cellInfo = otherRow.createCell(spaceNodeInfo.getLevel() - 1);
        cellInfo.setCellValue(spaceNodeInfo.getName());
        if (NumberUtils.LONG_ZERO.equals(spaceNodeInfo.getParentId())) {
            XSSFCell cell = otherRow.createCell(spaceNodeInfo.getLevel() - 1);
            cell.setCellValue(spaceNodeInfo.getName());
            return hssfWorkbook;
        } else {
            //传入的是子节点的层级 需要获取父级的数据
            List<SpaceNodeInfo> spaceNodeInfos = map.get(spaceNodeInfo.getLevel() - 1);
            if (!CollectionUtils.isEmpty(spaceNodeInfos)) {
                //根据当前子节点的父级节点id 匹配父级节点的id
                SpaceNodeInfo parentNodeParentInfo = spaceNodeInfos.stream().filter(info -> spaceNodeInfo.getParentId().equals(info.getId())).findAny().get();
                log.info("exportInfo parentNodeParentInfo | {}", parentNodeParentInfo);
                exportInfo(hssfWorkbook, parentNodeParentInfo, otherRow, map);
            }
        }
        return hssfWorkbook;
    }

    /**
     * 导入空间信息
     *
     * @param importNodeForm excl文件信息
     */
    public void importNode(ImportNodeForm importNodeForm) throws Exception {
        log.info("importNode importNodeForm |{}", importNodeForm);
        long startTime = System.currentTimeMillis();

        MultipartFile file = importNodeForm.getFile();

        InputStream inputStream = file.getInputStream();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            log.error("SpaceNodeService importNode error | {} ", e.getMessage(), e);
            throw new SmartProjectBusinessException("导入的空间表格内容错误");
        }
        if (ObjectUtils.isEmpty(workbook)) {
            throw new SmartProjectBusinessException("导入的空间表格内容为空");
        }
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<SpaceNodeInfo> arrayList = new ArrayList<SpaceNodeInfo>(sheet.getLastRowNum());
        System.out.println("arrayList | {}"+arrayList.size());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i + 1);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell1 = row.getCell(j);
                String name = cell1.getCellType().name();
                SpaceNodeInfo build = SpaceNodeInfo.builder()
                        .name(name+j)
                        .parentId(NumberUtils.LONG_ZERO)
                        .eid(2340L)
                        .spaceSetId(12L)
                        .orderNum(j)
                        .isLast(NumberUtils.INTEGER_ZERO)
                        .locationName("value")
                        .level(j + 1)
                        .build();
                arrayList.add(build);
                //spaceNodeDao.add(build);
            }
        }
        long endTime = System.currentTimeMillis();
        long castTime = endTime - startTime;
        log.info("arrayList size | {}",arrayList.size());
        List<List<SpaceNodeInfo>> partition = Lists.partition(arrayList, 1000);
        //CountDownLatch latch = new CountDownLatch(partition.size()/5);
        ThreadFactory springThreadFactory = new CustomizableThreadFactory("registrationThread-pool-");
        //ExecutorService threadPool = Executors.newFixedThreadPool(20, springThreadFactory);
        ThreadExcutorPoolConfig threadExcutorPoolConfig = new ThreadExcutorPoolConfig();
        Executor executor = threadExcutorPoolConfig.asyncServiceExecutor();

        //利用多线程去添加数据
        for(int i=0;i<partition.size();i++){
            List<SpaceNodeInfo> spaceNodeInfos = partition.get(i);
            ThreadPoolServiceRunable threadPoolServiceRunable = new ThreadPoolServiceRunable(spaceNodeDao,spaceNodeInfos);
            //threadPool.execute(threadPoolServiceRunable);
            try {
                executor.execute(threadPoolServiceRunable);
            }catch (Exception e){
                log.error("error | {},{}",e.getMessage(),e);
            }
        }
        
        log.info("castTime | {}",castTime);
    }


    public void addShopNode(SpaceNodeInfo spaceNodeInfo) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        try {
            //将一级节点转换成 shop 对象发送MQ消息
            messageContentDto.setData(JSONObject.toJSONString(spaceNodeInfo));
            messageContentDto.setMethod("add");
            messageContentDto.setType("shop");
            log.info("SpaceNodeService addShopNode shop messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("SpaceNodeService addShopNode add error message|{} | {}", e, e.getMessage());
        }
    }

    public void addHouseNode(SpaceNodeInfo spaceNodeInfo) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        SpaceSetVO spaceSetVO = spaceSetDao.spaceSetDetail(spaceNodeInfo.getSpaceSetId());
        try {
            //将二级节点转换成house对象发送MQ消息
            House house = House.builder()
                    .id(spaceNodeInfo.getId())
                    .eid(spaceNodeInfo.getEid())
                    .houseName(spaceNodeInfo.getName())
                    .houseAddress(spaceNodeInfo.getName())
                    .address(spaceSetVO.getAddress())
                    .types(NumberUtils.INTEGER_ZERO)
                    .province(NumberUtils.INTEGER_ZERO)
                    .city(NumberUtils.INTEGER_ZERO)
                    .area(NumberUtils.INTEGER_ZERO)
                    .shopId(spaceNodeInfo.getParentId())
                    .storeId(spaceNodeInfo.getParentId())
                    .build();
            messageContentDto.setData(JSONObject.toJSONString(house));
            messageContentDto.setMethod("add");
            messageContentDto.setType("house");
            log.info("SpaceNodeService addHouseNode house messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("SpaceNodeService addHouseNode error message|{} | {}", e, e.getMessage());
        }
    }

    public void addSpaceNode(SpaceNodeInfo spaceNodeInfo) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        try {
            SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(spaceNodeInfo.getParentId());
            Room room = Room.builder()
                    .id(spaceNodeInfo.getId())
                    .houseId(spaceNodeInfo.getParentId())
                    .floorNum(spaceNodeVO.getName() + "")
                    .type(NumberUtils.INTEGER_ZERO)
                    .fid(spaceNodeInfo.getParentId())
                    .address(spaceNodeInfo.getName())
                    .sort(NumberUtils.LONG_ZERO)
                    .homeNo(spaceNodeInfo.getName())
                    .build();
            messageContentDto.setData(JSONObject.toJSONString(room));
            messageContentDto.setMethod("add");
            messageContentDto.setType("room");
            log.info("SpaceNodeService addSpaceNode room messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("space add error message|{} | {}", e, e.getMessage());
        }
    }

    public void addFloorAndSpaceNode(SpaceNodeInfo spaceNodeInfo) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(spaceNodeInfo.getParentId());
        try {
            Room space = Room.builder()
                    .id(spaceNodeInfo.getId())
                    .houseId(spaceNodeVO.getParentId())
                    .floorNum(spaceNodeVO.getName() + "")
                    .type(NumberUtils.INTEGER_ZERO)
                    .fid(spaceNodeInfo.getParentId())
                    .address(spaceNodeVO.getName() + spaceNodeInfo.getName())
                    .sort(NumberUtils.LONG_ZERO)
                    .homeNo(spaceNodeInfo.getName())
                    .build();
            messageContentDto.setData(JSONObject.toJSONString(space));
            messageContentDto.setMethod("add");
            messageContentDto.setType("room");
            log.info("SpaceNodeService addFloorAndSpaceNode space messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("addFloorAndSpaceNode add error message|{} | {}", e, e.getMessage());
        }
    }

    public void addByNodeLevel(SpaceNodeInfo spaceNodeInfo) {
        log.info("SpaceNodeService addByNodeLevel request | {}", spaceNodeInfo);
        SpaceSetVO spaceSetVO = spaceSetDao.spaceSetDetail(spaceNodeInfo.getSpaceSetId());
        switch (spaceNodeInfo.getLevel()) {
            case 1:
                addShopNode(spaceNodeInfo);
                break;
            case 2:
                addHouseNode(spaceNodeInfo);
                break;
            case 3:
                if(LabelEnum.DORM_LABEL.getLabelCode() == spaceSetVO.getCode()){
                    break;
                }
                addSpaceNode(spaceNodeInfo);
                break;
            case 4:
                addFloorAndSpaceNode(spaceNodeInfo);
                break;
            default:
                if(NumberUtils.INTEGER_ZERO.equals(spaceNodeInfo.getIsLast())){
                    addRoomNode(spaceNodeInfo);
                }
                break;
        }
    }

    /**
     * 针对超出公寓和宿舍层级的 智慧通行和其他标签
     * @param spaceNodeInfo 空间节点进行
     */
    private void addRoomNode(SpaceNodeInfo spaceNodeInfo) {
        log.info("SpaceNodeService addRoomNode spaceNodeInfo | {}",spaceNodeInfo);
        if(!NumberUtils.INTEGER_ZERO.equals(spaceNodeInfo.getIsLast())){
            return;
        }
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(spaceNodeInfo.getParentId());
        String lastNameByNodeId = getLastNameByNodeId(spaceNodeInfo, "");
        lastNameByNodeId = lastNameByNodeId.replaceAll("/","");
        try {
            Room space = Room.builder()
                    .id(spaceNodeInfo.getId())
                    .houseId(ObjectUtils.isEmpty(spaceNodeVO) ? spaceNodeInfo.getParentId() : spaceNodeVO.getParentId())
                    .floorNum(ObjectUtils.isEmpty(spaceNodeVO) ? spaceNodeInfo.getParentId()  + "" : spaceNodeVO.getName() + "")
                    .type(NumberUtils.INTEGER_ZERO)
                    .fid(spaceNodeInfo.getParentId())
                    .address(spaceNodeInfo.getLocationName())
                    .sort(NumberUtils.LONG_ZERO)
                    .homeNo(lastNameByNodeId)
                    .build();
            messageContentDto.setData(JSONObject.toJSONString(space));
            messageContentDto.setMethod("add");
            messageContentDto.setType("room");
            log.info("SpaceNodeService addRoomNode room messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("SpaceNodeService addRoomNode add error message|{} | {}", e, e.getMessage());
        }
    }




    private void deleteByNodeLevel(SpaceNodeInfo spaceNodeInfo) {
        log.info("SpaceNodeService deleteByNodeLevel request param | {}", spaceNodeInfo);
        switch (spaceNodeInfo.getLevel()) {
            case 1:
                deleteShop(spaceNodeInfo.getId());
                break;
            case 2:
                deleteHouse(spaceNodeInfo.getId());
                break;
            case 3:
            case 4:
                deleteSpace(spaceNodeInfo.getId());
                break;
            default:
                if(NumberUtils.INTEGER_ZERO.equals(spaceNodeInfo.getIsLast())){
                    deleteSpace(spaceNodeInfo.getId());
                }
                break;
        }
    }

    private void deleteShop(Long id) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        try {
            messageContentDto.setData(JSONObject.toJSONString(id));
            messageContentDto.setMethod("delete");
            messageContentDto.setType("shop");
            log.info("SpaceNodeService deleteShop shop messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("SpaceNodeService deleteShop shop messageContentDto error message|{} | {}", e, e.getMessage());
        }
    }

    private void deleteHouse(Long id) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        try {
            messageContentDto.setData(JSONObject.toJSONString(id));
            messageContentDto.setMethod("delete");
            messageContentDto.setType("house");
            log.info("SpaceNodeService deleteHouse house messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("SpaceNodeService deleteHouse house messageContentDto error message|{} | {}", e, e.getMessage());
        }
    }

    private void deleteSpace(Long id) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        try {
            messageContentDto.setData(JSONObject.toJSONString(id));
            messageContentDto.setMethod("delete");
            messageContentDto.setType("room");
            log.info("SpaceNodeService deleteSpace space messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("SpaceNodeService deleteSpace space messageContentDto error message|{} | {}", e, e.getMessage());
        }
    }

    private void updateNodeByLevel(SpaceNodeInfo spaceNodeInfo) {
        log.info("SpaceNodeService updateNodeByLevel request param | {}", spaceNodeInfo);
        Long spaceSetId = spaceNodeInfo.getSpaceSetId();
        SpaceSetVO spaceSetVO = spaceSetDao.spaceSetDetail(spaceSetId);
        if(LabelEnum.DORM_LABEL.getLabelCode() ==  spaceSetVO.getCode()){
            spaceNodeInfo.setLevel(spaceNodeInfo.getLevel()+NumberUtils.INTEGER_ONE);
        }
        if(LabelEnum.ACCESS_LABEL.getLabelCode() ==  spaceSetVO.getCode() ||
                LabelEnum.OTHER_LABEL.getLabelCode() == spaceSetVO.getCode()){
            //判断是否是最后一层的节点 如果是则直接发送MQ消息修改
            if(NumberUtils.INTEGER_ZERO.equals(spaceNodeInfo.getIsLast())){
                String lastNameByNodeId = getLastNameByNodeId(spaceNodeInfo, "");
                String replaceAllNodeName = lastNameByNodeId.replaceAll("/", "");
                spaceNodeInfo.setName(replaceAllNodeName);
                updateSpaceNode(spaceNodeInfo);
            }else {
                //如果不是最后一层 则证明是修改的中间某一层的节点。 获取当前层级下的所有的子节点
                //循环遍历查询的子节点 筛选出是最后一层的节点id并将最新的locationName设置到name中
                List<SpaceNodeInfo> spaceNodeInfos = childNodeByParentId(new ArrayList<>(), spaceNodeInfo.getId());
                spaceNodeInfos.forEach(spaceNodeInfo1 -> {
                    if(NumberUtils.INTEGER_ZERO.equals(spaceNodeInfo1.getIsLast())){
                        String replaceAllNodeName = spaceNodeInfo1.getLocationName().replaceAll("/", "");
                        spaceNodeInfo1.setName(replaceAllNodeName);
                        updateSpaceNode(spaceNodeInfo1);
                    }
                });
            }
        }else {
            switch (spaceNodeInfo.getLevel()) {
                case 1:
                    updateShopNode(spaceNodeInfo);
                    break;
                case 2:
                    updateHouseNode(spaceNodeInfo);
                    break;
                case 3:
                    updateFloorSpaceNode(spaceNodeInfo,spaceSetVO.getCode());
                    break;
                case 4:
                    updateSpaceNode(spaceNodeInfo);
                    break;
                default:
                    break;
            }
        }
    }

    public void updateShopNode(SpaceNodeInfo spaceNodeInfo) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        try {
            //将一级节点转换成 shop 对象发送MQ消息
            messageContentDto.setData(JSONObject.toJSONString(spaceNodeInfo));
            messageContentDto.setMethod("update");
            messageContentDto.setType("shop");
            log.info("SpaceNodeService updateShopNode shop messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("SpaceNodeService updateShopNode add error message|{} | {}", e, e.getMessage());
        }
    }

    public void updateHouseNode(SpaceNodeInfo spaceNodeInfo) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        try {
            //将二级节点转换成house对象发送MQ消息
            House house = House.builder()
                    .id(spaceNodeInfo.getId())
                    .eid(spaceNodeInfo.getEid())
                    .houseName(spaceNodeInfo.getName())
                    .houseAddress(spaceNodeInfo.getName())
                    .shopId(spaceNodeInfo.getParentId())
                    .storeId(spaceNodeInfo.getParentId())
                    .build();
            messageContentDto.setData(JSONObject.toJSONString(house));
            messageContentDto.setMethod("update");
            messageContentDto.setType("house");
            log.info("SpaceNodeService updateHouseNode house messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("updateHouseNode error message|{} | {}", e, e.getMessage());
        }
    }


    public void updateFloorSpaceNode(SpaceNodeInfo spaceNodeInfo,Integer code) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        try {
            Room room = Room.builder()
                    .fid(spaceNodeInfo.getId())
                    .id(spaceNodeInfo.getId())
                    .floorNum(spaceNodeInfo.getName() + "")
                    .type(NumberUtils.INTEGER_ZERO)
                    .build();
                messageContentDto.setData(JSONObject.toJSONString(room));

                messageContentDto.setMethod("updateFloor");
                messageContentDto.setType("room");

            log.info("SpaceNodeService updateFloorSpaceNode room messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("SpaceNodeService updateFloorSpaceNode room error message|{} | {}", e, e.getMessage());
        }
    }


    public void updateSpaceNode(SpaceNodeInfo spaceNodeInfo) {
        ProjectSpaceSyncDto messageContentDto = new ProjectSpaceSyncDto();
        try {
            Room room = Room.builder()
                    .id(spaceNodeInfo.getId())
                    .address(spaceNodeInfo.getName())
                    .homeNo(spaceNodeInfo.getName())
                    .build();
            messageContentDto.setData(JSONObject.toJSONString(room));
            messageContentDto.setMethod("update");
            messageContentDto.setType("room");
            log.info("SpaceNodeService updateSpaceNode room messageContentDto | {}", messageContentDto);
            //rocketMQProducer.sendMessage(Constants.rocketmq_project_space_sync_topic, JSONObject.toJSONString(messageContentDto).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("SpaceNodeService updateSpaceNode room error message|{} | {}", e, e.getMessage());
        }
    }
}
