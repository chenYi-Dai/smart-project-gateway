package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : AddSpaceNodeForm
 * @Description : 添加空间节点入参
 * @Date: 2022-07-14 16:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "添加空间节点入参", description = "添加空间节点入参")
public class AddSpaceNodeForm {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("企业id")
    @NotNull(message = "企业id不能为空")
    private Long eid;

    @ApiModelProperty("所属空间集id")
    @NotNull(message = "所属空间集id不能为空")
    private Long spaceSetId;

    @ApiModelProperty("空间节点层级 1代表一层节点")
    @NotNull(message = "空间节点层级不能为空")
    private Integer level;

    @ApiModelProperty("空间节点父级节点 0代表一层节点")
    @NotNull(message = "空间节点父级节点不能为空")
    private Long parentId;

    @ApiModelProperty("空间节点名称")
    @Length(max = 20,message = "空间节点名称不能超过20个字符")
    private String name;

    @ApiModelProperty("添加节点类型 0添加子节点 1添加上级节点 2添加下级节点")
    private Integer type;

    @ApiModelProperty("业务标签")
    private Integer label;
}
