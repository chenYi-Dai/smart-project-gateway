package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author : ex_yi.chen
 * @ClassName : ProjectCategoryVO
 * @Description : 项目品类产品信息返回对象
 * @Date: 2022-09-08 15:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("项目品类产品信息返回对象")
public class ProjectProductVO {

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "产品code")
    private String productKey;

    @ApiModelProperty(value = "品类名称")
    private String categoryCode;

    @ApiModelProperty(value = "品类")
    private String categoryName;

    @ApiModelProperty(value = "配置中心参数")
    private Map<String,Object> configParam;

}
