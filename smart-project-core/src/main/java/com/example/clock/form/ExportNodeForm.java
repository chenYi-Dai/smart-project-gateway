package com.example.clock.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : ExportNodeForm
 * @Description : 导出空间节点信息入参
 * @Date: 2022-07-21 15:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExportNodeForm {

    @ApiModelProperty("企业id")
    private Long eid;

    @ApiModelProperty("空间集id")
    @NotNull(message = "空间集id不能 为空")
    private Long spaceId;

}
