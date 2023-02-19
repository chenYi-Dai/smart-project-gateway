package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : DeviceInfoIdForm
 * @Description : 设备详情id入参
 * @Date: 2022-09-13 11:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DeviceInfoIdForm", description = "设备详情id入参")
public class DeviceInfoIdForm {

    @NotNull(message = "设备信息id不能为空")
    @ApiModelProperty("设备信息id")
    private Long deviceInfoId;
}
