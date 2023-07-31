package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : ProjectSpaceSetVO
 * @Description : 项目空间集返回对象
 * @Date: 2022-08-31 10:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectSpaceSetVO", description = "项目空间集返回对象")
public class ProjectSpaceSetVO {

    @ApiModelProperty(value = "空间集id")
    private Long spaceSetId;

    @ApiModelProperty(value = "空间集名称")
    private String name;

    @ApiModelProperty(value = "设备信息")
    private List<ProjectDeviceDetailVO> deviceList;
}
