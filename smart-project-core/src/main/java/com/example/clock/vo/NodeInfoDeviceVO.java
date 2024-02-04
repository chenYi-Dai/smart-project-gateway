package com.example.clock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_yi.chen
 * @ClassName : AppNodeDeviceVO
 * @Description : app项目详情空间节点返回对象
 * @Date: 2022-08-31 10:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "NodeInfoDeviceVO", description = "app项目详情空间节点返回对象")
public class NodeInfoDeviceVO extends ChildNodeInfoVO {

    @ApiModelProperty(value = "空间节点id")
    private Long nodeId;

    @ApiModelProperty(value = "空间节点名称")
    private String nodeName;

}
