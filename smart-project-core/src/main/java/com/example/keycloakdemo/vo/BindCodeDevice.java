package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wy
 * @date 2020/7/17 0017
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("通过bindCode获取的设备")
public class BindCodeDevice {
    private String deviceId;
    private String productKey;
    private String deviceName;
}
