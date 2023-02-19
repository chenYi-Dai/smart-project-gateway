package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_yi.chen
 * @ClassName : EnterpriseListForm
 * @Description : 企业列表信息入参
 * @Date: 2022-07-14 15:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "企业列表信息入参", description = "企业列表信息入参")
public class EnterpriseListForm {

    @ApiModelProperty(value = "企业id")
    private Long eid;

    @ApiModelProperty(value = "搜索关键字")
    private String keywords;
}
