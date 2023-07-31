package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author : ex_yi.chen
 * @ClassName : GetDeviceByBindCodeForm
 * @Description : 获取绑定code
 * @Date: 2022-10-28 16:04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDeviceByBindCodeForm {

    @ApiModelProperty(value = "code")
    @NotNull(message = "code不能为空")
    private String bindCode;
}
