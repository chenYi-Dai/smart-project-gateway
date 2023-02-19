package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 发送更换新手机验证码表单
 *
 * @author sjg
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("SendChangeMobileCodeForm")
public class SendChangeMobileCodeForm {

    @NotNull(message = "手机号码不能为空")
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号码不正确")
    @ApiModelProperty("手机号")
    private String mobile;

    @NotNull(message = "验证标识码不能为空")
    @ApiModelProperty("验证标识码")
    private String mark;

}
