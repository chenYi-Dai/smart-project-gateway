package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : RemoveChildDeviceForm
 * @Description : 移除子设备入参
 * @Date: 2022-09-08 15:10
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RemoveDeviceForm", description = "移除子设备入参")
public class RemoveDeviceForm {

    @NotNull(message = "设备mac不能为空")
    @ApiModelProperty(value = "设备mac",required = true)
    private String mac;

    @ApiModelProperty(value = "类型 0:当前设备以及子设备 1:删除全部子设备")
    private Integer type;

    @ApiModelProperty(value = "设备信息id",required = true)
    @Size(min = 1,message = "最小为1")
    private List<String> macList;

}
