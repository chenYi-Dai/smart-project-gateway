package com.ruoyi.common.prarm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : NodeListForm
 * @Description : 获取节点列表入参
 * @Date: 2022-07-14 16:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "获取节点列表入参", description = "获取节点列表入参")
public class NodeListForm {

    @ApiModelProperty("企业id")
    private Long eid;

    @ApiModelProperty("所属空间集id")
    @NotNull(message = "所属空间集id不能为空")
    private Long spaceSetId;

    @ApiModelProperty("父级节点id")
    private Long parentId;

    @ApiModelProperty("空间节点层级")
    private Integer level;

    @ApiModelProperty("添加节点类型 0添加子节点 1添加上级节点 2添加下级节点")
    private Integer type;

    @ApiModelProperty("业务标签")
    private String label;
}
