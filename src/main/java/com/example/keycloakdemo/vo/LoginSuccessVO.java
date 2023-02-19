package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author : ex_yi.chen
 * @ClassName : LoginSuccessVO
 * @Description : 用户登录返回对象
 * @Date: 2022-07-14 15:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户登录返回对象")
public class LoginSuccessVO {
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long id;

    /**
     * token
     */
    @ApiModelProperty("token")
    private String token;

    /**
     * 手机号
     */
    @ApiModelProperty("账号")
    private String phone;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

}
