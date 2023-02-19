package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : AppDeviceListForm
 * @Description : app项目列表接口入参
 * @Date: 2022-08-30 10:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AppDeviceListForm", description = "app项目列表接口入参")
public class AppDeviceListForm {

    @NotNull(message = "项目id不能为空")
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "空间集id")
    private List<Long> spaceSetId;

    @ApiModelProperty(value = "产品")
    private List<String> productKey;

    @ApiModelProperty(value = "品类")
    private List<String> category;

    @ApiModelProperty(value = "空间节点id")
    private List<Long> nodeId;

    @ApiModelProperty(value = "同步状态 0未同步 1已同步")
    private Integer notRegisterStatus;

    @ApiModelProperty(value = "搜索关键字")
    private String keywords;
}
