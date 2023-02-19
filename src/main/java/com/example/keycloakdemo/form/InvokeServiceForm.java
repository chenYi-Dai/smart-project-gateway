package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author: 石恒
 * @DateTime: 2020-05-26 10:38
 * @qq: 374696376
 * @Description:
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvokeServiceForm {

    @ApiModelProperty(value = "设备信息id")
    private Long deviceInfoId;

    @ApiModelProperty(value = "设备mac")
    private String mac;

    @ApiModelProperty(value = "服务名")
    private String service;

    @NotNull(message = "属性")
    @ApiModelProperty(value = "属性")
    private Map<String, Object> args;
}
