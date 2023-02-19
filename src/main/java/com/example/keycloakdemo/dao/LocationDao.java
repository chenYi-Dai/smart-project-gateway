package com.example.keycloakdemo.dao;


import com.example.keycloakdemo.dao.mapper.LocationMapper;
import com.example.keycloakdemo.dao.model.Location;
import com.example.keycloakdemo.form.AppDeviceListForm;
import com.example.keycloakdemo.form.DeviceBySpaceSetAndCategoryForm;
import com.example.keycloakdemo.form.DeviceListForm;
import com.example.keycloakdemo.form.ProjectNodeListForm;
import com.example.keycloakdemo.vo.DeviceVO;
import com.example.keycloakdemo.vo.NodeInfoDeviceVO;
import com.example.keycloakdemo.vo.ProjectDeviceVO;
import com.example.keycloakdemo.vo.SpaceNodeVO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ex_wen.fei
 * @ClassName : LocationDao
 * @Description : 空间设备信息表Dao
 * @Date: 2022-09-08 14:12
 */
@Repository
public class LocationDao {


    @Resource
    private LocationMapper locationMapper;

    /**
     * 根据识别码查询总数
     *
     * @param mac 识别码
     * @return 识别码总数
     */
    public Location locationByMac(String mac) {
        return locationMapper.locationByMac(mac);
    }

    /**
     * 根据识别码查询总数
     *
     * @param mac 识别码
     * @return 识别码总数
     */
    public List<Location> locationListByMac(List<String> mac) {
        return locationMapper.locationListByMac(mac);
    }

    /**
     * 根据项目id查询已绑定数量
     *
     * @param location 项目id
     * @return 已绑定数量总数量
     */
    public Integer bindCount(Location location) {
        return locationMapper.bindCount(location);
    }

    /**
     * 根据项目id查询已同步总数
     *
     * @param location 项目id
     * @return 设备已同步总数
     */
    public Integer syncCount(Location location) {
        return locationMapper.syncCount(location);
    }


    /**
     * 获取设备列表总数
     *
     * @param deviceListForm 设备列表入参
     * @return 统计总数
     */
    public int deviceListCount(DeviceListForm deviceListForm) {
        return locationMapper.deviceListCount(deviceListForm);
    }

    /**
     * 获取设备列表
     *
     * @param deviceListForm 设备列表入参
     * @return 设备列表出参
     */
    public List<Location> deviceList(DeviceListForm deviceListForm) {
        return locationMapper.deviceList(deviceListForm);
    }

    /**
     * 获取设备列表总数
     *
     * @param appDeviceListForm 设备列表入参
     * @return 统计总数
     */
    public int deviceManageListCount(AppDeviceListForm appDeviceListForm) {
        return locationMapper.deviceManageListCount(appDeviceListForm);
    }

    public int deviceManageInnerListCount(AppDeviceListForm appDeviceListForm) {
        return locationMapper.deviceManageInnerListCount(appDeviceListForm);
    }

    /**
     * 设备列表入参
     *
     * @param appDeviceListForm 设备列表入参
     * @param start             开始页
     * @param size              每页展示数据
     * @return 设备列表
     */
    public List<NodeInfoDeviceVO> deviceManageList(AppDeviceListForm appDeviceListForm, Integer start, Integer size) {
        return locationMapper.deviceManageList(appDeviceListForm, start, size);
    }

    /**
     * 项目空间-条件查询
     *
     * @param appDeviceListForm 设备列表入参
     * @param start             开始页
     * @param size              每页展示数据
     * @return 空间列表
     */
    public List<NodeInfoDeviceVO> deviceManageInnerList(AppDeviceListForm appDeviceListForm, Integer start, Integer size) {
        return locationMapper.deviceManageInnerList(appDeviceListForm, start, size);
    }

    /**
     * 获取项目空间列表
     *
     * @param projectNodeListForm 项目id
     * @return 空间节点相关信息出参
     */
    public List<SpaceNodeVO> projectNodeList(ProjectNodeListForm projectNodeListForm) {
        return locationMapper.projectNodeList(projectNodeListForm);
    }

    public int projectNodeListCount(ProjectNodeListForm projectNodeListForm) {
        return locationMapper.projectNodeListCount(projectNodeListForm);
    }

    public Long addDevice(Location location) {
        locationMapper.addDevice(location);
        return location.getId();
    }

    /**
     * 根据id获取设备详情
     *
     * @param deviceInfoId 设备id
     * @return
     */
    public ProjectDeviceVO deviceDetail(Long deviceInfoId) {
        Location location = locationMapper.detail(deviceInfoId);
        return ProjectDeviceVO.builder()
                .projectId(location.getProjectId())
                .id(location.getId())
                .deviceName(location.getDeviceName())
                .mac(location.getMac())
                .categoryCode(location.getCategoryCode())
                .productKey(location.getProductKey())
                .nodeId(location.getNodeId())
                .spaceSetId(location.getSpaceSetId())
                .nodeName(location.getNodeName().replaceAll(";", ""))
                .projectState(location.getSyncStatus())
                .registerStatus(location.getRegisterStatus())
                .deviceId(location.getDeviceId())
                .bindUser(location.getBindUser())
                .bindTime(location.getBindTime())
                .registTime(location.getRegistTime())
                .build();
    }

    public Integer updateDevice(Location location) {
        locationMapper.updateDevice(location);
        return NumberUtils.INTEGER_ONE;
    }


    /**
     * 根据空间节点查询空间节点下绑定的设备信息
     *
     * @param nodeId 空间节点
     * @return 返回设备信息
     */
    public List<Location> locationByNode(Long nodeId) {
        return locationMapper.locationByNodeId(nodeId);
    }

    /**
     * 根据空间节点查询空间节点下绑定的设备信息
     *
     * @param nodeId    空间节点
     * @param projectId 项目id
     * @return 返回设备信息
     */
    public List<Location> locationByNodeIdAndProjectId(Long nodeId, Long projectId, List<String> productKey, List<String> category) {
        return locationMapper.locationByNodeIdAndProjectId(nodeId, projectId, productKey, category);
    }

    /**
     * 根据空间集id联合查询 该空间集下是否存在绑定的设备
     *
     * @param spaceSetId 空间集id
     * @return 返回绑定的设备信息
     */
    public List<Location> locationBySpaceSetId(Long spaceSetId) {
        return locationMapper.locationBySpaceSetId(spaceSetId);
    }

    /**
     * 解除绑定 批量解绑
     *
     * @param ids 空间设备信息id
     * @return 空间设备信息 列表
     */
    public List<Location> LocationListByIds(List<Long> ids) {
        return locationMapper.LocationListByIds(ids);
    }

    /**
     * 删除绑定信息
     *
     * @param ids 空间设备信息id
     * @return
     */
    public Integer deleteByIds(List<Long> ids) {
        return locationMapper.deleteByIds(ids);
    }

    /**
     * 设备详情
     *
     * @param id 设备信息id
     * @return 设备详情
     */
    public Location getLocationById(Long id) {
        return locationMapper.getLocationById(id);
    }


    /**
     * 根据空间集查询水电表总数
     *
     * @param deviceListForm 设备入参
     * @return 设备总数
     */
    public int countBySpaceSetAndCategory(DeviceBySpaceSetAndCategoryForm deviceListForm) {
        return locationMapper.countBySpaceSetAndCategory(deviceListForm);
    }

    /**
     * 根据空间集id 查询水电表设备
     *
     * @param deviceListForm 水电表入参
     * @param start          开始页
     * @param size           分页大小
     * @return 设备列表
     */
    public List<DeviceVO> deviceListBySpaceSetAndCategory(DeviceBySpaceSetAndCategoryForm deviceListForm, int start, int size) {
        return locationMapper.deviceListBySpaceSetAndCategory(deviceListForm, start, size);
    }


}
