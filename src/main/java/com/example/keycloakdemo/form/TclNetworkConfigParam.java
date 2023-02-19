package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_yi.chen
 * @ClassName : TclNetworkConfigParam
 * @Description : 鸿鹄设备获取设备秘钥入参
 * @Date: 2022-10-11 11:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TclNetworkConfigParam", description = "鸿鹄设备获取设备秘钥入参")
public class TclNetworkConfigParam {

    @ApiModelProperty(value = "设备mac", required = true)
    private String mac;

    @ApiModelProperty(value = "ssid", required = true)
    private String ssid;

    @ApiModelProperty(value = "shortMac", required = true)
    private String shortMac;
}
