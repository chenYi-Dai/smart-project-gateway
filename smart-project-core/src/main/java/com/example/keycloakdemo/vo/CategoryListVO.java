package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ex_wen.fei
 * @date 2022/11/8
 * @description:
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CategoryListVO", description = "根据项目id获取品类产品")
public class CategoryListVO {

    @ApiModelProperty(value = "品类")
    private String categoryCode;

    @ApiModelProperty(value = "品类名称")
    private String categoryName;

    @ApiModelProperty(value = "产品")
    private List<ProductListVO> productList;

}
