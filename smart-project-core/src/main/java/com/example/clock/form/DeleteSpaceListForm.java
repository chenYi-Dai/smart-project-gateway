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
 * @ClassName : DeleteSpaceListForm
 * @Description : 空间集删除入参
 * @Date: 2022-07-14 15:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "空间集删除入参", description = "空间集删除入参")
public class DeleteSpaceListForm {

    @ApiModelProperty("空间集主键id")
    @NotNull(message = "主键id不能为空")
    private Long id;

    @ApiModelProperty("企业id")
    private Long eid;
}
