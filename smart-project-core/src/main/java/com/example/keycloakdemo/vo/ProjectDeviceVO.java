package com.example.keycloakdemo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : ex_yi.chen
 * @ClassName : ProjectDeviceVO
 * @Description : 项目设备列表信息返回对象
 * @Date: 2022-08-31 11:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectDeviceVO", description = "项目设备列表信息返回对象")
public class ProjectDeviceVO {

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "设备信息id")
    private Long id;

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备识别码")
    private String mac;

    @ApiModelProperty(value = "设备品类")
    private String categoryCode;

    @ApiModelProperty(value = "设备品类名称")
    private String categoryName;

    @ApiModelProperty(value = "设备产品型号")
    private String productKey;

    @ApiModelProperty(value = "设备产品型号")
    private String productName;

    @ApiModelProperty(value = "空间节点id")
    private Long nodeId;

    @ApiModelProperty(value = "空间集id")
    private Long spaceSetId;

    @ApiModelProperty(value = "空间节点名称")
    private String nodeName;

    /**
     * 通讯方式, 0为zigbee，1为485, 2为mbus 3为NB 4为以太网
     */
    @ApiModelProperty(value = "通讯方式, 0为zigbee，1为485, 2为mbus 3为NB 4为以太网")
    private String correspond;
    /**
     * 网络类型 0-无线 1-有线 zigbee nb-iot wifi 这三种通讯方式都是无线，其余的是有线
     */
    @ApiModelProperty(value = "网络类型 0-无线 1-有线 zigbee nb-iot wifi 这三种通讯方式都是无线，其余的是有线")
    private Integer networkType;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "工程状态 0 未同步 1已同步")
    private Integer projectState;

    @ApiModelProperty(value = "注册状态 0 未注册 1已注册")
    private Integer registerStatus;

    @ApiModelProperty(value = "设备id")
    private String deviceId;

    @ApiModelProperty(value = "在离线状态 1在线 0离线")
    private Integer onlineState;

    @ApiModelProperty(value = "设备绑定人")
    private String bindUser;

    @ApiModelProperty(value = "拓扑关系状态 0:不存在 1:存在")
    private Integer topologyStatus;

    @ApiModelProperty(value = "设备绑定时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date bindTime;

    @ApiModelProperty(value = "设备注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registTime;

}
