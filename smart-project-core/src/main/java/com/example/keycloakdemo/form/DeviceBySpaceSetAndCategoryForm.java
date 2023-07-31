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
 * @ClassName : DeviceBySpaceSetAndCategoryForm
 * @Description : 根据空间集查询所有的水电表设备入参
 * @Date: 2022-10-21 15:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DeviceBySpaceSetAndCategoryForm", description = "根据空间集查询所有的水电表设备入参")
public class DeviceBySpaceSetAndCategoryForm {


    @NotNull(message = "空间集id不能为空")
    @ApiModelProperty(value = "空间集id",required = true)
    private Long spaceSetId;

    @NotNull(message = "项目id不能为空")
    @ApiModelProperty(value = "项目id",required = true)
    private Long projectId;

    @ApiModelProperty(value = "品类列表")
    private List<String> categoryList;

    @ApiModelProperty(value = "空间id")
    private List<Long> nodeIdList;

    @ApiModelProperty(value = "同步状态 0未同步 1已同步")
    private Integer registerStatus;

    private String correspond;
}
