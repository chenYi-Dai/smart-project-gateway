package com.example.clock.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : DeleteNodeForm
 * @Description : 删除空间节点入参
 * @Date: 2022-07-14 18:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除空间节点入参", description = "删除空间节点入参")
public class DeleteNodeForm {

    @ApiModelProperty("空间节点id")
    @NotNull(message = "空间节点id不能为空")
    private Long id;
}
