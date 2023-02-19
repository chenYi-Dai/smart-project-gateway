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
 * @ClassName : AppProjectDataVO
 * @Description : app项目详情中的空间集总数返回对象
 * @Date: 2022-08-31 10:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AppProjectDataVO", description = "app项目详情中的空间集总数返回对象")
public class ProjectInfoDataVO {

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "空间集对象")
    private List<SpaceSetCount> spaceSetCounts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpaceSetCount {

        @ApiModelProperty(value = "空间集id")
        private Long spaceSetId;

        @ApiModelProperty(value = "空间集名称")
        private String spaceSetName;

        @ApiModelProperty(value = "空间总数")
        private Integer nodeCount;

        @ApiModelProperty(value = "应绑定设备总数")
        private Integer hasBindCount;

        @ApiModelProperty(value = "设备已绑定总数")
        private Integer bindCount;

        @ApiModelProperty(value = "设备未同步总数")
        private Integer notSyncCount;

        @ApiModelProperty(value = "设备已同步总数")
        private Integer syncCount;

    }
}
