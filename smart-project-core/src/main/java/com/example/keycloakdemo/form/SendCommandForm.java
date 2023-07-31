package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author：SHI HENG
 * CREATE_TIME: 2020-08-10 11:04
 * QQ: 374696376
 * DESCRIPTION:
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SendCommandForm {
    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "设备mac")
    private String mac;

    @NotNull(message = "属性")
    @ApiModelProperty(value = "属性")
    private Map<String, Object> args;
}
