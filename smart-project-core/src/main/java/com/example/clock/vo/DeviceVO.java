package com.example.clock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_yi.chen
 * @ClassName : DeviceVO
 * @Description : 返回设备对象
 * @Date: 2022-09-06 11:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DeviceVO", description = "返回设备对象")
public class DeviceVO {

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "空间id")
    private Long nodeId;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "设备名称")
    private String nodeName;

    @ApiModelProperty(value = "品类名称")
    private String categoryName;

    @ApiModelProperty(value = "品类")
    private String category;

    @ApiModelProperty(value = "产品类型")
    private String productKey;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "同步状态")
    private Integer syncStatus;

    @ApiModelProperty(value = "注册状态")
    private Integer registerStatus;

    @ApiModelProperty(value = "建档完成状态 0:建档中 1:建档完成")
    private Integer archiveStatus;

    @ApiModelProperty(value = "建档状态 0:建档失败 1:建档成功")
    private Integer status;

    @ApiModelProperty(value = "设备识别码")
    private String mac;

    @ApiModelProperty(value = "设备数量")
    private Integer count;
}
