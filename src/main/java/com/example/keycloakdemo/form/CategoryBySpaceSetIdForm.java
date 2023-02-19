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
 * @ClassName : CategoryBySpaceSetIdForm
 * @Description : 项目所属空间集的产品列表入参
 * @Date: 2022-09-19 10:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CategoryBySpaceSetIdForm", description = "项目所属空间集的产品列表入参")
public class CategoryBySpaceSetIdForm {

    @ApiModelProperty("空间集id")
    @NotNull(message = "空间集id不能为空")
    private Long spaceSetId;

    @ApiModelProperty("项目id")
    @NotNull(message = "项目id不能为空")
    private Long projectId;
}
