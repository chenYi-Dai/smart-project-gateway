package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_yi.chen
 * @ClassName : AppProjectSpaceSetVO
 * @Description : app返回空间集对象
 * @Date: 2022-09-09 16:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AppProjectSpaceSetVO", description = "app返回空间集对象")
public class AppProjectSpaceSetVO {

    @ApiModelProperty(value = "空间集id")
    private Long spaceSetId;

    @ApiModelProperty(value = "空间集名称")
    private String spaceSetName;

}
