package com.example.clock.dao.mapper;



import com.example.clock.dao.model.Location;
import com.example.clock.form.AppDeviceListForm;
import com.example.clock.form.DeviceBySpaceSetAndCategoryForm;
import com.example.clock.form.DeviceListForm;
import com.example.clock.form.ProjectNodeListForm;
import com.example.clock.vo.DeviceVO;
import com.example.clock.vo.NodeInfoDeviceVO;
import com.example.clock.vo.SpaceNodeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : ex_wen.fei
 * @ClassName : LocationMapper
 * @Description : 空间设备信息表mapper
 * @Date: 2022年9月8日
 */
public interface LocationMapper {

    /**
     * 根据识别码查询总数
     * @param mac 识别码
     * @return 识别码总数
     */
    Location locationByMac(@Param("mac") String mac);

    /**
     * 根据识别码查询总数
     * @param mac 识别码
     * @return 识别码总数
     */
    List<Location> locationListByMac(@Param("mac") List<String> mac);

    /**
     *  根据项目id查询已绑定数量
     * @param location 项目id
     * @return 已绑定数量总数量
     */
    Integer bindCount(Location location);

    /**
     * 根据项目id查询已同步总数
     *
     * @param location
     * @return 设备已同步总数
     */
    Integer syncCount(Location location);

    /**
     * 获取设备列表总数
     *
     * @param deviceListForm 设备列表入参
     * @return 统计总数
     */

    int deviceListCount(DeviceListForm deviceListForm);
    /**
     * 获取设备列表
     *
     * @param deviceListForm 设备列表入参
     * @return 设备列表出参
     */
    List<Location> deviceList(DeviceListForm deviceListForm);

    /**
     * 获取设备列表总数
     *
     * @param appDeviceListForm 设备列表入参
     * @return 统计总数
     */
    int deviceManageListCount(AppDeviceListForm appDeviceListForm);

    int deviceManageInnerListCount(AppDeviceListForm appDeviceListForm);


    /**
     * 设备列表入参
     * @param appDeviceListForm 设备列表入参
     * @param start 开始页
     * @param size 每页展示数据
     * @return 设备列表
     */
    List<NodeInfoDeviceVO> deviceManageList(@Param("appDeviceListForm") AppDeviceListForm appDeviceListForm,
                                            @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 项目空间-条件查询
     * @param appDeviceListForm 设备列表入参
     * @param start 开始页
     * @param size 每页展示数据
     * @return 空间列表
     */
    List<NodeInfoDeviceVO> deviceManageInnerList(@Param("appDeviceListForm") AppDeviceListForm appDeviceListForm,
                                                 @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 获取项目空间列表
     * @param projectNodeListForm 项目id
     * @return 空间节点相关信息出参
     */
    List<SpaceNodeVO> projectNodeList(ProjectNodeListForm projectNodeListForm);


    int projectNodeListCount(ProjectNodeListForm projectNodeListForm);
    /**
     * 添加设备
     * @param location 添加设备入参对象
     * @return 添加设备返回的主键id
     */
    Integer addDevice(Location location);

    /**
     * 修改绑定设备信息
     * @param location 设备信息
     */
   void updateDevice(Location location);

    /**
     * 设备详情
     * @param deviceInfoId 设备信息id
     * @return 设备详情
     */
    Location detail(@Param("deviceInfoId") Long deviceInfoId);

    /**
     * 根据空间节点查询空间节点下绑定的设备信息
     * @param nodeId 空间节点
     * @return 返回设备信息
     */
    List<Location> locationByNodeId(@Param("nodeId") Long nodeId);

    /**
     * 根据空间节点查询空间节点下绑定的设备信息
     * @param nodeId 空间节点
     * @param projectId 项目id
     * @return 返回设备信息
     */
    List<Location> locationByNodeIdAndProjectId(@Param("nodeId") Long nodeId,@Param("projectId") Long projectId,
                                                @Param("productKey") List<String> productKey,@Param("category") List<String> category);


    /**
     * 根据空间集id联合查询 该空间集下是否存在绑定的设备
     * @param spaceSetId 空间集id
     * @return 返回绑定的设备信息
     */
    List<Location> locationBySpaceSetId(@Param("spaceSetId") Long spaceSetId);
    /**
     * 解除绑定 批量解绑
     * @param deviceInfoIds 设备id
     * @return 空间设备信息 列表
     */
    List<Location> LocationListByIds(@Param("deviceInfoIds") List<Long> deviceInfoIds);
    /**
     * 删除绑定信息
     * @param deviceInfoIds 空间设备信息id
     * @return count
     */
    Integer deleteByIds(@Param("deviceInfoIds") List<Long> deviceInfoIds);
    /**
     * 设备详情
     * @param id 设备信息id
     * @return 设备详情
     */
    Location getLocationById(Long id);
    /**
     * 修改同步状态
     * @param id
     * @return
     */
    Integer updateBySyncStatus(Long id);


    /**
     * 根据空间集查询水电表总数
     * @param deviceListForm 设备入参
     * @return 设备总数
     */
    int countBySpaceSetAndCategory(DeviceBySpaceSetAndCategoryForm deviceListForm);

    /**
     * 根据空间集id 查询水电表设备
     * @param deviceListForm 水电表入参
     * @param start 开始页
     * @param size 分页大小
     * @return 设备列表
     */
    List<DeviceVO> deviceListBySpaceSetAndCategory(@Param("deviceListForm") DeviceBySpaceSetAndCategoryForm deviceListForm,
                                                   @Param("start") Integer start, @Param("size") Integer size);
}
