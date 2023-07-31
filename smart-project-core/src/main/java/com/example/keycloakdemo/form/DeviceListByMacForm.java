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
 * @ClassName : DeviceListByMacForm
 * @Description : mac获取空间名称信息入参
 * @Date: 2022-10-10 10:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DeviceListByMacForm", description = "mac获取空间名称信息入参")
public class DeviceListByMacForm {

    @ApiModelProperty(value = "mac列表", required = true)
    @Size(min = 1, message = "列表最少为1")
    private List<String> macList;
}
