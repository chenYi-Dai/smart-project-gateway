package com.example.clock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : DeviceToPoVO
 * @Description :
 * @Date: 2022-10-18 14:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AppDeviceToPoVO", description = "app返回拓扑关系对象")
public class DeviceToPoVO{

    @ApiModelProperty(value = "设备信息id")
    private Long id;

    @ApiModelProperty(value = "空间节点id")
    private Long nodeId;

    @ApiModelProperty(value = "空间集id")
    private Long spaceSetId;

    @ApiModelProperty(value = "空间节点名称")
    private String nodeName;

    @ApiModelProperty(value = "在离线状态 1在线 0离线")
    private Integer onlineState;

    @ApiModelProperty(value = "同步状态(工程状态)  0 未同步 1已同步")
    private Integer projectState;

    @ApiModelProperty(value = "注册状态 0 未注册 1已注册")
    private Integer registerStatus;

    @ApiModelProperty(value = "设备id")
    private String deviceId;

    @ApiModelProperty(value = "设备mac")
    private String mac;

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "品类")
    private String category;

    @ApiModelProperty(value = "产品")
    private String productKey;

    @ApiModelProperty(value = "产品名称")
    private String productName;


    @ApiModelProperty(value = "子设备列表")
    private List<DeviceToPoVO> children;

    public static DeviceToPoVO of(String deviceId, String mac, String deviceName,Integer onlineState,String productKey,String productName) {
        return DeviceToPoVO.builder()
                .deviceId(deviceId)
                .deviceName(deviceName)
                .onlineState(onlineState)
                .mac(mac)
                .productKey(productKey)
                .productName(productName)
                .build();
    }

}
