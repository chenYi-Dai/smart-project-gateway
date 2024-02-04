package com.example.clock.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 登录信息dto
 *
 * @author qingyan on 2018-05-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoForm {

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 密码
     */
    @NotNull
    @ApiModelProperty("密码")
    private String password;
}
