package com.example.clock.dao;


import com.example.clock.dao.mapper.SpaceNodeMapper;
import com.example.clock.dao.model.SpaceNodeInfo;
import com.example.clock.vo.SpaceNodeVO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : SpaceNodeDao
 * @Description : 空间节点信息
 * @Date: 2022-07-19 17:56
 */
@Repository
public class SpaceNodeDao {

    @Resource
    private SpaceNodeMapper spaceNodeMapper;

    /**
     * 添加单个空间节点信息
     * @param spaceNodeInfo 空间节点对象
     */
    public Long add(SpaceNodeInfo spaceNodeInfo){
        spaceNodeMapper.add(spaceNodeInfo);
        return spaceNodeInfo.getId();
    }

    /**
     * 修改节点名称
     * @param spaceNodeInfo 节点信息
     */
    public void updateNodeName(SpaceNodeInfo spaceNodeInfo){
        spaceNodeMapper.update(spaceNodeInfo);
    }

    /**
     * 修改节点排序数值
     * @param spaceNodeInfo 节点信息
     */
    public void updateOrderNum(SpaceNodeInfo spaceNodeInfo){
        spaceNodeMapper.updateOrderNum(spaceNodeInfo);
    }

    /**
     * 获取节点总数
     * @param spaceNodeInfo 空间节点信息
     * @return 返回所有空间节点总数
     */
    public int count(SpaceNodeInfo spaceNodeInfo){
        return spaceNodeMapper.count(spaceNodeInfo);
    }

    /**
     * 获取节点总数
     * @param spaceNodeInfo 空间节点信息
     * @return 返回所有空间节点总数
     */
    public int findCount(SpaceNodeInfo spaceNodeInfo){
        return spaceNodeMapper.count(spaceNodeInfo);
    }

    /**
     * 获取所有节点总数
     * @param spaceNodeInfo 空间节点信息
     * @return 返回所有空间节点总数
     */
    public int allChildCount(SpaceNodeInfo spaceNodeInfo){
        return spaceNodeMapper.allChildCount(spaceNodeInfo);
    }


    /**
     * 空间节点列表
     * @param spaceNodeInfo 空间节点信息
     * @return 返回空间 节点列表
     */
    public List<SpaceNodeInfo> list(SpaceNodeInfo spaceNodeInfo){
        return spaceNodeMapper.list(spaceNodeInfo);
    }

    /**
     * 删除空间节点信息
     * @param id 节点id
     */
    public void deleteNode(Long id) {
        spaceNodeMapper.delete(id);
    }

    /**
     * 根据空间集id删除节点信息
     * @param spaceId 空间集id
     */
    public void deleteBySpaceSetId(Long spaceId) {
        spaceNodeMapper.deleteBySpaceSetId(spaceId);
    }

    /**
     * 获取单个空间节点信息
     * @param id 节点id
     */
    public SpaceNodeVO nodeInfoById(Long id) {
        SpaceNodeInfo spaceNodeInfo = spaceNodeMapper.detail(id);
        if(ObjectUtils.isEmpty(spaceNodeInfo)){
            return null;
        }
        return SpaceNodeVO.builder()
                .nodeId(spaceNodeInfo.getId())
                .parentId(spaceNodeInfo.getParentId())
                .eid(spaceNodeInfo.getEid())
                .name(spaceNodeInfo.getName())
                .spaceSetId(spaceNodeInfo.getSpaceSetId())
                .isLast(spaceNodeInfo.getIsLast())
                .orderNum(spaceNodeInfo.getOrderNum())
                .level(spaceNodeInfo.getLevel())
                .locationName(spaceNodeInfo.getLocationName())
                .build();
    }

    /**
     * 根据主键id获取空间节点信息
     * @param id
     * @param parentId
     * @return 空间节点对象
     */
    public SpaceNodeInfo detailBySpaceSetIdAndParentId(Long id,Long parentId) {
        return spaceNodeMapper.detailBySpaceSetIdAndParentId(id,parentId);
    }
}
