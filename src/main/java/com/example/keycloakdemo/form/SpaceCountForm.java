package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : SpaceCountForm
 * @Description : app空间总数查询入参
 * @Date: 2022-09-21 15:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpaceCountForm", description = "app空间总数查询入参")
public class SpaceCountForm {

    @ApiModelProperty(value = "空间集id")
    private List<Long> spaceSetId;

    @ApiModelProperty(value = "空间节点id")
    private List<Long> nodeId;
}
