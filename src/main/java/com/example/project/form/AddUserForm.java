package com.example.project.form;

import cn.hutool.core.date.DateTime;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AddUserForm", description = "添加用户名")
public class AddUserForm {

    private Long id;

    private String userId;

    private String name;

    private String password;

    private String createUser;

    private String modifiedUser;
    private String email;
}
