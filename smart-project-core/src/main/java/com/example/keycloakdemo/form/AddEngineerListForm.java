package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : PersonnelAllocationForm
 * @Description : 项目施工人员分配
 * @Date: 2022-08-30 11:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AddEngineerListForm", description = "项目施工人员分配")
public class AddEngineerListForm {

    @NotNull(message = "项目id不能为空")
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private List<Long> userIds;

}
