package com.example.clock.dao.mapper;

import com.example.clock.dao.model.SpaceNodeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : SpaceNodeMapper
 * @Description : 空间信息mapper
 * @Date: 2022-07-15 14:14
 */
public interface SpaceNodeMapper {

    /**
     * 添加空间节点信息
     * @param spaceNodeInfo 空间节点对象
     * @return int
     */
    int add(SpaceNodeInfo spaceNodeInfo);

    /**
     * 修改空间节点排序
     * @param spaceNodeInfo 修改空间节点排序
     */
     void updateOrderNum(SpaceNodeInfo spaceNodeInfo);

    /**
     * 修改空间节点信息
     * @param spaceNodeInfo 修改空间节点对象
     */
    void update(SpaceNodeInfo spaceNodeInfo);

    /**
     * 根据id删除空间集信息
     * @param id 空间节点主键id
     */
    void delete(@Param("id") Long id);

    /**
     * 根据空间集id删除节点信息
     * @param spaceId 空间集id
     */
    void deleteBySpaceSetId(Long spaceId);


    /**
     * 空间节点列表信息
     * @param spaceNodeInfo 空间节点对象
     * @return 空间节点列表数组
     */
    List<SpaceNodeInfo> list(SpaceNodeInfo spaceNodeInfo);

    /**
     * 空间节点合总数
     * @param spaceNodeInfo 空间节点合入参
     * @return 返回查询的空间节点总数
     */
    int count(SpaceNodeInfo spaceNodeInfo);

    /**
     * 根据主键id获取空间节点信息
     * @param id 主键id
     * @return 空间节点对象
     */
    SpaceNodeInfo detail(Long id);

    /**
     * 根据主键id获取空间节点信息
     * @param id
     * @param parentId
     * @return 空间节点对象
     */
    SpaceNodeInfo detailBySpaceSetIdAndParentId(@Param("spaceSetId") Long spaceSetId,@Param("parentId") Long parentId);

    /**
     * 查询子节点总数
     * @param spaceNodeInfo 节点入参
     * @return 子节点总数
     */
    int allChildCount(SpaceNodeInfo spaceNodeInfo);
}
