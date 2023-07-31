package com.example.keycloakdemo.dao;

import com.example.keycloakdemo.dao.mapper.SpaceSetLocationSummaryMapper;
import com.example.keycloakdemo.dao.model.SpaceSetLocationSummary;
import com.example.keycloakdemo.form.DeviceListForm;
import com.example.keycloakdemo.vo.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ex_wen.fei
 * @ClassName : ProjectDao
 * @Description : 项目概要信息Dao
 * @Date: 2022-09-08
 */
@Repository
public class SpaceSetLocationSummaryDao {

    @Resource
    private SpaceSetLocationSummaryMapper spaceSetLocationSummaryMapper;

    /**
     * 获取项目预安装总数
     *
     * @param summary 
     * @return 预安装总数量
     */
    public Integer hasBindCount(SpaceSetLocationSummary summary) {
        return spaceSetLocationSummaryMapper.hasBindCount(summary);
    }

    /**
     * 通过项目Id查询空间节点总数
     *
     * @param summary
     * @return 空间节点最后一层总数量
     */
    public Integer nodeCount(SpaceSetLocationSummary summary) {
        return spaceSetLocationSummaryMapper.nodeCount(summary);
    }

    /**
     * 添加 项目概要信息
     *
     * @param spaceSetLocationSummary 项目概要信息dto
     */
    public void add(SpaceSetLocationSummary spaceSetLocationSummary) {
        spaceSetLocationSummaryMapper.add(spaceSetLocationSummary);
    }
    /**
     * 根据项目id删除目概要信息
     * @param projectId 项目Id
     */
    public void deleteByProjectId(Long projectId) {
        spaceSetLocationSummaryMapper.deleteByProjectId(projectId);
    }

    /**
     * 统计设备总数列表
     *
     * @param deviceListForm 项目设备列表入参
     * @return 项目设备列表出参
     */
    public List<DeviceTotalVO> listDeviceTotal(DeviceListForm deviceListForm) {
        return  spaceSetLocationSummaryMapper.listDeviceTotal(deviceListForm);
    }

    /**
     * 根据空间节点获取设备信息
     *
     * @param nodeId    空间节点
     * @param projectId
     * @return 节点设备信息列表
     */
    public List<DeviceVO> nodeDetail(Long nodeId, Long projectId) {
        return  spaceSetLocationSummaryMapper.nodeDetail(nodeId,projectId);
    }

    /**
     * 根据项目id获取项目下空间集基本信息
     * @param projectId 项目id
     * @return
     */
    public List<ProjectSpaceSetVO> projectSpaceSetList(Long projectId) {
        return  spaceSetLocationSummaryMapper.projectSpaceSetList(projectId);
    }

    /**
     * 根据空间集id统计设备信息
     *
     * @param spaceSetId 空间集id
     * @param projectId 空间集id
     * @return
     */
    public List<ProjectDeviceDetailVO> projectDeviceDetail(Long spaceSetId, Long projectId) {
        return  spaceSetLocationSummaryMapper.projectDeviceDetail(spaceSetId,projectId);
    }

    /**
     * 根据项目id和产品id查询该产品是否属于当前项目下
     * @param spaceSetLocationSummary 项目概要信息
     * @return 返回项目概要信息
     */
    public List<ProjectDeviceDetailVO> listBySpaceSetIdAndProductKey(SpaceSetLocationSummary spaceSetLocationSummary) {
        return  spaceSetLocationSummaryMapper.listBySpaceSetIdAndProductKey(spaceSetLocationSummary);
    }

    public List<CategoryListVO> projectCategoryList(Long projectId) {
        return  spaceSetLocationSummaryMapper.projectCategoryList(projectId);
    }

    public List<ProductListVO> projectProductList(String categoryCode, Long projectId) {
        return  spaceSetLocationSummaryMapper.projectProductList(categoryCode,projectId);
    }
}
