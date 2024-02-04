package com.example.clock.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Entry;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entry(objectClasses = { "inetOrgPerson", "top"},base = "ou=user")
public class User {


    @ApiModelProperty(value = "姓")
    private String firstName;

    @ApiModelProperty(value = "名")
    private String secondName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户密码")
    private String password;

}
