package com.example.project.form;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "GetUserInfoForm", description = "根据用户名获取用户信息")
public class GetUserInfoForm {
    private String name;
}
