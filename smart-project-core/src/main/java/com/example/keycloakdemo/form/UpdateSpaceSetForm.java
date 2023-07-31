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
 * @ClassName : UpdateSpaceForm
 * @Description : 修改空间集信息入参
 * @Date: 2022-07-14 15:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "修改空间集信息入参", description = "修改空间集信息入参")
public class UpdateSpaceSetForm {

    @ApiModelProperty("空间集主键id")
    @NotNull(message = "空间集主键id不能为空")
    private Long id;

    @ApiModelProperty("企业Id")
    private Long eid;

    @ApiModelProperty("空间集名称")
    @NotNull(message = "空间集名称不能为空")
    @Length(max = 20, message = "空间集名称最长不能超过20")
    private String name;

    @ApiModelProperty("空间集所在地址")
    @NotNull(message = "空间集所在地址不能为空")
    @Length(max = 50, message = "空间集所在地址最长不能超过50")
    private String address;

}
