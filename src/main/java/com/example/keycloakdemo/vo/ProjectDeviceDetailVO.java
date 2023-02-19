package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author : ex_yi.chen
 * @ClassName : ProjectDeviceDetail
 * @Description : 项目设备详情
 * @Date: 2022-09-14 11:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDeviceDetailVO {
    @ApiModelProperty(value = "品类")
    private String categoryCode;

    @ApiModelProperty(value = "品类名称")
    private String categoryName;

    @ApiModelProperty(value = "产品")
    private String productKey;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "设备总数")
    private Integer count;

    @ApiModelProperty(value = "配置中心参数")
    private Map<String,Object> configParam;
}
