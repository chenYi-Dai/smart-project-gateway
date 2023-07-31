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
 * @ClassName : AddSpaceSetForm
 * @Description : 新增空间集信息入参
 * @Date: 2022-07-14 15:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "新增空间集信息入参", description = "新增空间集信息入参")
public class AddSpaceSetForm {
    @ApiModelProperty("登录服务商id")
    @NotNull(message = "登录服务商id不能为空")
    private Long serviceId;

    @ApiModelProperty("所属企业ID")
    @NotNull(message = "所属企业id不能为空")
    private Long eid;

    @ApiModelProperty("空间集名称")
    @NotNull(message = "空间集名称不能为空")
    @Length(max = 20, message = "空间集名称最长不能超过20")
    private String name;

//    @ApiModelProperty("所属企业Id")
//    private Long ownerEid;

    @ApiModelProperty("所属企业")
    @NotNull(message = "所属企业不能为空")
    private String eidName;

    @ApiModelProperty("空间集所在地址")
    @NotNull(message = "空间集所在地址不能为空")
    @Length(max = 50, message = "空间集所在地址最长不能超过50")
    private String address;

    @ApiModelProperty("业务标签 1-公寓,2-宿舍,3-智慧通行,4-其他")
    @NotNull(message = "业务标签不能为空")
    private Integer label;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建人手机号")
    private String phone;
}
