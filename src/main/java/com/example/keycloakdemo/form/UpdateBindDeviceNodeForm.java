package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : UpdateBindDeviceNodeForm
 * @Description : 修改设备绑定空间
 * @Date: 2022-09-08 18:18
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBindDeviceNodeForm {

    @NotNull(message = "设备信息id不能为空")
    @ApiModelProperty("设备信息id")
    private Long deviceInfoId;

    @ApiModelProperty("修改后空间节点名称")
    private String newNodeName;

    @NotNull(message = "新绑定的空间节点id不能为空")
    @ApiModelProperty("新绑定的空间节点id")
    private Long newNodeId;
}
