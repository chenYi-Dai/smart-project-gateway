package com.example.keycloakdemo.service.imple;

import com.example.keycloakdemo.form.DeleteSpaceListForm;
import com.example.keycloakdemo.vo.PageParam;
import com.example.keycloakdemo.vo.PageResult;
import com.example.keycloakdemo.config.SmartProjectBusinessException;
import com.example.keycloakdemo.dao.LocationDao;
import com.example.keycloakdemo.dao.ProjectDao;
import com.example.keycloakdemo.dao.SpaceNodeDao;
import com.example.keycloakdemo.dao.SpaceSetDao;
import com.example.keycloakdemo.dao.model.Location;
import com.example.keycloakdemo.dao.model.Project;
import com.example.keycloakdemo.dao.model.SpaceNodeInfo;
import com.example.keycloakdemo.dao.model.SpaceSet;
import com.example.keycloakdemo.form.SpaceListForm;
import com.example.keycloakdemo.form.UpdateNodeForm;
import com.example.keycloakdemo.form.UpdateSpaceSetForm;
import com.example.keycloakdemo.vo.SpaceSetVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : ex_yi.chen
 * @ClassName : SpaceAnthologyService
 * @Description : 空间集业务处理类
 * @Date: 2022-07-15 15:02
 */
@Slf4j
@Service
public class SpaceSetService {

    private final SpaceSetDao spaceSetDao;
    private final SpaceNodeDao spaceNodeDao;
    private final LocationDao locationDao;
    private final ProjectDao projectDao;
    private final SpaceNodeService spaceNodeService;


    public SpaceSetService(SpaceSetDao spaceSetDao,
                           SpaceNodeDao spaceNodeDao, LocationDao locationDao,
                           ProjectDao projectDao,
                           SpaceNodeService spaceNodeService
                          ) {
        this.spaceSetDao = spaceSetDao;
        this.spaceNodeDao = spaceNodeDao;
        this.locationDao = locationDao;
        this.projectDao = projectDao;
        this.spaceNodeService = spaceNodeService;
    }


    /**
     * 修改空间集信息
     *
     * @param updateSpaceSetForm 空间集修改前端入参
     */
    public void updateSpaceSet(UpdateSpaceSetForm updateSpaceSetForm) {
        log.info("updateSpaceSet updateSpaceForm | {}", updateSpaceSetForm);
        SpaceSetVO spaceSetVO = spaceSetDao.spaceSetDetail(updateSpaceSetForm.getId());
        Assert.notNull(spaceSetVO, "修改的空间集节点不存在。");
        if (!spaceSetVO.getName().equals(updateSpaceSetForm.getName())) {
            List<SpaceSet> list = spaceSetDao.listByName(SpaceListForm.builder()
                    .eid(updateSpaceSetForm.getEid())
                    .keywords(updateSpaceSetForm.getName())
                    .build());
            if (list.size() > NumberUtils.INTEGER_ZERO) {
                throw new SmartProjectBusinessException("该企业下已存在当前空间集名称!");
            }
        }
        SpaceSet spaceSet = SpaceSet.builder()
                .id(updateSpaceSetForm.getId())
                .address(updateSpaceSetForm.getAddress())
                .name(updateSpaceSetForm.getName())
                .build();
        log.info("updateSpaceCollect spaceCollect | {} ", spaceSet);
        spaceSetDao.updateSpaceSet(spaceSet);
        List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder()
                .eid(spaceSetVO.getEid())
                .level(NumberUtils.INTEGER_ONE)
                .spaceSetId(spaceSet.getId())
                .build());
        if (!CollectionUtils.isEmpty(list)) {
            Optional<SpaceNodeInfo> first = list.stream().findFirst();
            if (first.isPresent()) {
                SpaceNodeInfo nodeInfo = first.get();
                log.info("nodeInfo |{}", nodeInfo);
                nodeInfo.setName(updateSpaceSetForm.getName());
                UpdateNodeForm build = UpdateNodeForm.builder()
                        .eid(nodeInfo.getEid())
                        .id(nodeInfo.getId())
                        .level(nodeInfo.getLevel())
                        .newName(updateSpaceSetForm.getName())
                        .spaceId(nodeInfo.getSpaceSetId())
                        .parentId(nodeInfo.getParentId())
                        .build();
                //修改空间名称。并同步到公寓系统
                spaceNodeService.updateNodeName(build);
            }
        }
    }

    /**
     * 根据主键id删除空间集信息
     *
     * @param id 主键id
     */
    public void deleteSpaceSet(Long id) {
        log.info("deleteSpaceSet param | {}", id);
        List<Project> project = projectDao.projectBySpaceSetId(id);
        log.info("SpaceSetService deleteSpaceSet project |{}", project.size());
        if (!CollectionUtils.isEmpty(project)) {
            throw new SmartProjectBusinessException("该空间集下的空间存在项目,请解绑再试。");
        }
        List<Location> locations = locationDao.locationBySpaceSetId(id);
        log.info("SpaceSetService deleteSpaceSet locations |{}", locations.size());
        if (!CollectionUtils.isEmpty(locations)) {
            throw new SmartProjectBusinessException("该空间集下的空间存在绑定设备。请解绑再试。");
        }
        SpaceSetVO spaceSetVO = spaceSetDao.spaceSetDetail(id);

        List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder()
                .spaceSetId(spaceSetVO.getId())
                .parentId(NumberUtils.LONG_ZERO)
                .build());
        //判断查询的空间节点是否为空 如果不为空则删除然后同步到公寓
        if (!CollectionUtils.isEmpty(list)) {
            //获取空间节点中为顶层节点的id
            list = list.stream().filter(spaceNodeInfo ->  NumberUtils.LONG_ZERO.equals(spaceNodeInfo.getParentId()))
                    .collect(Collectors.toList());
            SpaceNodeInfo spaceNodeInfo = list.get(NumberUtils.INTEGER_ZERO);
            spaceNodeService.deleteNode(spaceNodeInfo.getId());
        }
        spaceSetDao.deleteSpaceSet(id);

    }

    public void addSpaceSet(UpdateSpaceSetForm updateSpaceSetForm){
        spaceSetDao.addSpaceSet(SpaceSet.builder()
                .eid(updateSpaceSetForm.getEid())
                .eidName(updateSpaceSetForm.getName())
                .label(NumberUtils.INTEGER_ONE)
                .name(updateSpaceSetForm.getName())
                .phone(updateSpaceSetForm.getName())
                .build());
    }

    /**
     * 获取空间集列表
     *
     * @param pageParam 空间集列表入参
     * @return 空间集列表
     */
    public PageResult<SpaceSetVO> list(PageParam<SpaceListForm> pageParam) {
        log.info("list pageParam | {}", pageParam);
        PageResult<SpaceSetVO> pageResult = new PageResult<>();
        SpaceListForm condition = pageParam.getCondition();
        condition.setStart(pageParam.getStart());
        condition.setSize(pageParam.getSize());
        int count = spaceSetDao.count(condition);
        if (NumberUtils.INTEGER_ZERO == count) {
            pageResult.setTotal(count);
            pageResult.setData(new ArrayList<>());
        }
        List<SpaceSet> spaceSets = spaceSetDao.list(condition);
        List<SpaceSetVO> collect = spaceSets.stream().map(spaceCollect -> {

            int allChildCount = spaceNodeDao.allChildCount(SpaceNodeInfo.builder()
                    .spaceSetId(spaceCollect.getId())
                    .eid(spaceCollect.getEid())
                    .isLast(NumberUtils.INTEGER_ZERO)
                    .build());
            return SpaceSetVO.builder()
                    .id(spaceCollect.getId())
                    .eid(spaceCollect.getEid())
                    .name(spaceCollect.getName())
                    .eidName(spaceCollect.getEidName())
                    .code(spaceCollect.getLabel())
                    .createUser(spaceCollect.getCreateUser())
                    .phone(spaceCollect.getPhone())
                    .address(spaceCollect.getAddress())
                    .createTime(spaceCollect.getCreateTime())
                    .spaceCount(allChildCount)
                    .build();
        }).collect(Collectors.toList());
        pageResult.setTotal(count);
        pageResult.setData(collect);
        return pageResult;
    }

    /**
     * 根据空间集id获取空间集信息
     *
     * @param deleteSpaceListForm 空间集入参
     * @return 返回空间集信息
     */
    public SpaceSetVO spaceSetDetail(DeleteSpaceListForm deleteSpaceListForm) {
        int allChildCount = spaceNodeDao.allChildCount(SpaceNodeInfo.builder()
                .spaceSetId(deleteSpaceListForm.getId())
                .isLast(NumberUtils.INTEGER_ZERO)
                .build());
        SpaceSetVO spaceSetVO = spaceSetDao.spaceSetDetail(deleteSpaceListForm.getId());

        spaceSetVO.setSpaceCount(allChildCount);
        return spaceSetVO;
    }


}
