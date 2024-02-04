package com.example.clock.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_yi.chen
 * @ClassName : AllNodeListForm
 * @Description : 获取所有节点入参
 * @Date: 2022-09-06 10:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AllNodeListForm", description = "获取所有节点入参")
public class AllNodeListForm {

    @ApiModelProperty(value = "企业id")
    private Long eid;

    @ApiModelProperty(value = "空间集id")
    private Long spaceSetId;
}
