package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : ProjectAddForm
 * @Description : 新增项目接口入参
 * @Date: 2022-08-30 10:12
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectAddForm", description = "新增项目接口入参")
public class ProjectAddForm {

    @NotNull(message = "服务商id不能为空")
    @ApiModelProperty(value = "服务商id")
    private Long serviceId;

    @NotNull(message = "企业id不能为空")
    @ApiModelProperty(value = "企业id")
    private Long eid;

    @NotNull(message = "项目名称不能为空")
//    @Pattern(regexp="^[\u4e00-\u9fa5_a-zA-Z0-9]+$",message="项目名称不能存在特殊字符")
    @Size( max = 50, message = "项目名称需要在50字符内")
    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目地址")
    @NotNull(message = "项目地址不能为空")
//    @Pattern(regexp="^[\u4e00-\u9fa5_a-zA-Z0-9]+$",message="项目地址不能存在特殊字符")
    @Size(  max = 50, message = "项目地址需要在50字符内")
    private String address;


    @ApiModelProperty(value = "项目所属企业名称")
    private String enterpriseName;

    @Valid
    @Size(min = 1, message = "空间集对象不能少于1")
    @ApiModelProperty(value = "空间集对象")
    private List<ProjectSpaceForm> spaceSetList;

    @Length(max = 100, message = "备注需要在100字符内")
    @ApiModelProperty(value = "备注")
    private String remark;
}
