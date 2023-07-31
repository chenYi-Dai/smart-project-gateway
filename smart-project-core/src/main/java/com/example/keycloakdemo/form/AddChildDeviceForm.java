package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : AddChildDeviceForm
 * @Description : 添加子设备信息入参
 * @Date: 2022-08-30 14:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AddChildDeviceForm", description = "添加子设备信息入参")
public class AddChildDeviceForm {

    @ApiModelProperty(value = "项目id", required = true)
    private Long projectId;

    @ApiModelProperty(value = "中控设备id", required = true)
    private Long centralId;

    @Valid
    @Size(min = 1, message = "最小为1")
    @ApiModelProperty(value = "子设备信息", required = true)
    private List<ChildDeviceInfoVO> childDeviceInfos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel("子设备信息")
    public static class ChildDeviceInfoVO {

        @NotNull(message = "空间节点id不能为空")
        @ApiModelProperty(value = "空间节点id")
        private Long nodeId;

        @ApiModelProperty(value = "空间名称")
        private String nodeName;

        @NotNull(message = "设备产品不能为空")
        private String productKey;

        @NotNull(message = "设备识别码不能为空")
        @Length(max = 30, message = "最长不能超过30")
        @ApiModelProperty(value = "设备识别码")
        private String mac;
    }

}
