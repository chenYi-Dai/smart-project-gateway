package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : NodeCountForm
 * @Description : app获取空间节点总数入参
 * @Date: 2022-09-08 16:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeCountForm {

    @ApiModelProperty(value = "空间集id")
    private Long spaceSetId;

    @ApiModelProperty(value = "空间节点id")
    private List<Long> nodeId;
}
