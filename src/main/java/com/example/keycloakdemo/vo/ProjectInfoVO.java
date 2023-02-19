package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_yi.chen
 * @ClassName : AppProjectVO
 * @Description : 项目信息
 * @Date: 2022-08-30 19:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectInfoVO", description = "app项目信息返回对象")
public class ProjectInfoVO {

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "空间总数")
    private Integer nodeCount;

    @ApiModelProperty(value = "应绑定设备总数")
    private Integer hasBindCount;

    @ApiModelProperty(value = "设备已绑定总数")
    private Integer bindCount;

    @ApiModelProperty(value = "设备已同步总数")
    private Integer syncCount;

    @ApiModelProperty(value = "设备未同步总数")
    private Integer notSyncCount;

}
