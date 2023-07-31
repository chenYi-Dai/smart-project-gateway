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
 * @ClassName : PersonnelListForm
 * @Description : 施工人员列表入参
 * @Date: 2022-08-30 11:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "EngineerListForm", description = "施工人员列表入参")
public class EngineerListForm {
    /**
     * 获取当前已存在的施工人员 项目id必穿
     */
    @NotNull(message = "项目id不能为空")
    @ApiModelProperty(value = "项目id")
    private Long projectId;
    /**
     * 获取企业下可用施工人员 企业id必穿
     */
    @ApiModelProperty(value = "服务商Id")
    private Long serviceId;

}
