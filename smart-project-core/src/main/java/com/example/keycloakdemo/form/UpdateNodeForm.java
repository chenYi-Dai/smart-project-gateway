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
 * @ClassName : UpdateNodeForm
 * @Description : 修改空间节点信息
 * @Date: 2022-07-14 17:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "修改空间节点信息", description = "修改空间节点信息")
public class UpdateNodeForm {

    @ApiModelProperty("企业id")
    @NotNull(message = "企业id不能为空")
    private Long eid;

    @ApiModelProperty("节点id")
    @NotNull(message = "节点id不能为空")
    private Long id;

    @ApiModelProperty("空间集id")
    private Long spaceId;

    @ApiModelProperty("空间节点父级id")
    private Long parentId;

    @ApiModelProperty("所属等级")
    @NotNull(message = "所属等级不能为空")
    private Integer level;

    @ApiModelProperty("修改的节点名称")
    @NotNull(message = "节点名称不能为空")
    @Length(max = 20,message = "节点名称不能超过20")
    private String newName;
}
