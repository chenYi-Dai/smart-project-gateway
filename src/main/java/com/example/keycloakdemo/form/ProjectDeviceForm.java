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
 * @ClassName : ProjectDeviceForm
 * @Description : 添加项目设备信息
 * @Date: 2022-08-30 10:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectDeviceForm", description = "添加项目设备信息")
public class ProjectDeviceForm {

    @NotNull(message = "设备品类不能为空")
    @ApiModelProperty(value = "设备品类")
    private String category;

    @NotNull(message = "设备产品不能为空")
    @ApiModelProperty(value = "设备产品")
    private String productKey;

    @NotNull(message = "设备总数不能为空")
    @ApiModelProperty(value = "设备总数")
    private Integer count;
}
