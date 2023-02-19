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
 * @ClassName : RegisterTclDeviceForm
 * @Description : 鸿鹄设备注册入参
 * @Date: 2022-10-18 16:55
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RegisterTclDeviceForm", description = "鸿鹄设备注册入参")
public class RegisterTclDeviceForm {

    @NotNull(message = "设备产品不能为空")
    @ApiModelProperty(value = "设备产品")
    private String productKey;

    @NotNull(message = "设备识别码不能为空")
    @Length(max = 30, message = "最长不能超过30")
    @ApiModelProperty(value = "设备识别码")
    private String mac;

    @NotNull(message = "设备识别码不能为空")
    @Length(max = 30, message = "最长不能超过30")
    @ApiModelProperty(value = "设备识别码")
    private String thirdId;
}
