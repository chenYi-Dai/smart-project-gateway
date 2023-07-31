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
 * @ClassName : SpaceListForm
 * @Description : 空间集列表入参
 * @Date: 2022-07-13 17:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "空间集列表入参", description = "空间集列表入参")
public class SpaceListForm {

    @ApiModelProperty("登录服务商id")
    @NotNull(message = "登录服务商id不能为空")
    private Long serviceId;

    @ApiModelProperty("所属企业id")
    private Long eid;

    @ApiModelProperty(value = "业务标签")
    private String code;

    @ApiModelProperty(value = "搜索空间集关键字")
    private String keywords;

    @ApiModelProperty(value = "搜索空间集全名(不做模糊搜索)")
    private String name;

    private int start;

    private int size;
}
