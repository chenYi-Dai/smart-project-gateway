package com.example.keycloakdemo.dao.mapper;


import com.example.keycloakdemo.dao.model.SpaceSetLocationSummary;
import com.example.keycloakdemo.form.DeviceListForm;
import com.example.keycloakdemo.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : ex_wen.fei
 * @ClassName : SpaceSetLocationSummaryMapper
 * @Description : 项目概要信息mapper
 * @Date: 2022年9月8日 09点59分
 */
public interface SpaceSetLocationSummaryMapper {


    /**
     * 获取项目预安装总数
     *
     * @param summary
     * @return 预安装总数量
     */
    Integer hasBindCount(SpaceSetLocationSummary summary);

    /**
     * 通过项目Id查询空间节点总数
     *
     * @param summary 项目id
     * @return 空间节点最后一层总数量
     */
    Integer nodeCount(SpaceSetLocationSummary summary);

    /**
     * 添加 项目概要信息
     *
     * @param spaceSetLocationSummary 项目概要信息dto
     */
    void add(SpaceSetLocationSummary spaceSetLocationSummary);
    /**
     * 根据项目id删除目概要信息
     * @param projectId 项目Id
     */
    void deleteByProjectId(@Param("projectId") Long projectId);
    /**
     * 统计设备总数列表
     *
     * @param deviceListForm 项目设备列表入参
     * @return 项目设备列表出参
     */
    List<DeviceTotalVO> listDeviceTotal(DeviceListForm deviceListForm);
    /**
     * 根据空间节点获取设备信息
     *
     * @param nodeId    空间节点
     * @param projectId
     * @return 节点设备信息列表
     */
    List<DeviceVO> nodeDetail(@Param("nodeId") Long nodeId, @Param("projectId") Long projectId);

    /**
     * 根据项目id获取项目下空间集基本信息
     * @param projectId 项目id
     * @return
     */
    List<ProjectSpaceSetVO> projectSpaceSetList(@Param("projectId") Long projectId);
    /**
     * 根据空间集id统计设备信息
     *
     * @param spaceSetId 空间集id
     * @param projectId
     * @return
     */
    List<ProjectDeviceDetailVO> projectDeviceDetail(@Param("spaceSetId") Long spaceSetId, @Param("projectId") Long projectId);

    /**
     * 根据项目id和产品id查询该产品是否属于当前项目下
     * @param spaceSetLocationSummary 项目概要信息
     * @return 返回项目概要信息
     */
    List<ProjectDeviceDetailVO> listBySpaceSetIdAndProductKey(SpaceSetLocationSummary spaceSetLocationSummary);

    List<CategoryListVO> projectCategoryList(Long projectId);

    List<ProductListVO> projectProductList(@Param("categoryCode") String categoryCode, @Param("projectId") Long projectId);
}
