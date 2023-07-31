package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : DeviceAddForm
 * @Description : 添加设备信息入参
 * @Date: 2022-08-30 14:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DeviceAddForm", description = "添加设备信息入参")
public class DeviceAddForm {

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "中控设备id")
    private Long centralId;

    @NotNull(message = "空间节点id不能为空")
    @ApiModelProperty(value = "空间节点id")
    private Long nodeId;

    @ApiModelProperty(value = "空间名称")
    private String nodeName;

    @NotNull(message = "设备产品不能为空")
    @ApiModelProperty(value = "设备产品")
    private String productKey;

    @NotNull(message = "设备识别码不能为空")
    @Length(max = 30, message = "最长不能超过30")
    @ApiModelProperty(value = "设备识别码")
    private String mac;
}
