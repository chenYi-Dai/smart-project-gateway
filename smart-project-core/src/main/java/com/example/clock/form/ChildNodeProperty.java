package com.example.clock.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author : ex_yi.chen
 * @ClassName : ChildNodeProperty
 * @Description : 子级节点入参属性
 * @Date: 2022-07-20 17:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "子级节点入参属性", description = "子级节点入参属性")
public class ChildNodeProperty {

    @ApiModelProperty("子节点数量")
    private Integer childNodeCount;

    @ApiModelProperty("节点编号类型 0-数字 1-字母 3-文字")
    private int nodeType;

    @ApiModelProperty("节点编号后缀")
    @Length(max = 20,message = "节点编号后缀长度不能超过20")
    private String nodeSuffix;

    @ApiModelProperty("是否继承上级节点 0否 1是")
    private Integer extendsHigherNode;
}
