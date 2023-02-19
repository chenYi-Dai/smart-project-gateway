package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_wen.fei
 * @ClassName : DeviceTotalVO
 * @Description : 统计
 * @Date: 2022年9月6日 14点41分
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DeviceTotalVO", description = "统计设备总数列表信息返回对象")
public class DeviceTotalVO {

    @ApiModelProperty(value = "空间名称")
    private String name;

    private Long spaceSetId;

    @ApiModelProperty(value = "空间总数")
    private Integer nodeTotal;

    @ApiModelProperty(value = "应绑数")
    private Integer hasBindTotal;

    @ApiModelProperty(value = "已绑数")
    private Integer bindTotal;

    @ApiModelProperty(value = "设备已同步总数")
    private Integer syncTotal;

    @ApiModelProperty(value = "设备未同步总数")
    private Integer notSyncTotal;
}
