package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : UnBindDeviceForm
 * @Description : 解除绑定设备入参
 * @Date: 2022-09-27 17:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UnBindDeviceForm", description = "解除绑定设备入参")
public class UnBindDeviceForm {

    @ApiModelProperty(value = "设备id", required = true)
    @Size(min = 1, message = "最小值不能小于1")
    private List<Long> ids;
}
