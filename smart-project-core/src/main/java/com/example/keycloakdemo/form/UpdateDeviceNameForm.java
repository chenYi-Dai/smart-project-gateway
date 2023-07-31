package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : UpdateDeviceNameForm
 * @Description : app修改设备名称入参
 * @Date: 2022-09-08 18:26
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeviceNameForm {

    @NotNull(message = "设备信息id不能为空")
    @ApiModelProperty("设备信息id")
    private Long deviceInfoId;

    @NotNull(message = "设备名称不能为空")
    @ApiModelProperty("设备名称")
    private String name;

    @ApiModelProperty("修改后的设备名称")
    private String newName;
}
