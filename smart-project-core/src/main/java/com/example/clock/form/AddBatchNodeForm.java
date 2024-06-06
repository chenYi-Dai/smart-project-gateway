package com.example.clock.form;

import com.example.clock.annotation.TestAnnotation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : AddBatchNodeForm
 * @Description : 批量添加节点信息
 * @Date: 2022-07-15 09:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "批量添加节点信息", description = "批量添加节点信息")
public class AddBatchNodeForm {

    @ApiModelProperty("企业id")
    private Long eid;

    @ApiModelProperty("业务标签 1-公寓,2-宿舍,3-智慧通行,4-其他")
    @NotNull(message = "业务标签不能为空")
    private int label;

    @ApiModelProperty("空间集id")
    @NotNull(message = "空间集id不能为空")
    private Long spaceSetId;

    @ApiModelProperty("一级节点名称")
    @NotNull(message = "一级节点名称不能为空")
    private String name;

    @Valid
    @ApiModelProperty("二级节点属性")
    private ChildNodeProperty twoChildNodeProperty;

    @Valid
    @ApiModelProperty("三级节点属性")
    private ChildNodeProperty threeChildNodeProperty;

    @Valid
    @ApiModelProperty("四级节点属性")
    private ChildNodeProperty fourChildNodeProperty;
}
