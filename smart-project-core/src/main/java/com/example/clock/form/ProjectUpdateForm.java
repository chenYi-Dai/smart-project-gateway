package com.example.clock.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_wen.fei
 * @ClassName : ProjectEditForm
 * @Description : 修改项目接口入参
 * @Date: 2022-09-07
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectUpdateForm", description = "修改项目接口入参")
public class ProjectUpdateForm {

    @NotNull(message = "项目id不能为空")
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @Length(min = 1, max = 50, message = "项目名称需要在1和50之间")
    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目地址")
    @Length(min = 1, max = 50, message = "项目地址需要在1和50之间")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;
}
