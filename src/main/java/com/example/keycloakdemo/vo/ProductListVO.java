package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ex_wen.fei
 * @date 2022/11/8
 * @description:
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProductListVO", description = "根据项目id获取产品")
public class ProductListVO {

    @ApiModelProperty(value = "产品")
    private String productKey;

    @ApiModelProperty(value = "产品名称")
    private String productName;

}
