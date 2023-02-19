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
 * @ClassName : AllowForm
 * @Description : 开启允许入网入参
 * @Date: 2022-09-20 14:55
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AllowForm", description = "开启允许入网入参")
public class AllowForm {

    @ApiModelProperty("设备识别码")
    @NotNull(message = "设备识别码不能为空")
    private String mac;

    @ApiModelProperty("开启时长")
    private Integer time;
}
