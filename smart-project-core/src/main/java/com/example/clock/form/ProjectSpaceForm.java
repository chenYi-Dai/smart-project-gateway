package com.example.clock.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : ProjectSpaceForm
 * @Description : 添加项目空间集入参
 * @Date: 2022-08-30 10:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectSpaceForm", description = "添加项目空间集入参")
public class ProjectSpaceForm {

    @NotNull(message = "空间集id不能为空")
    @ApiModelProperty(value = "空间集id")
    private Long spaceSetId;

    @ApiModelProperty(value = "空间集名称")
    private String spaceSetName;

    @Valid
    @Size(min = 1, message = "设备信息不能少于1")
    @ApiModelProperty(value = "设备信息")
    private List<ProjectDeviceForm> deviceList;
}
