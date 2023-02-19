package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : CreateRecordForm
 * @Description : 建档生成列表入参
 * @Date: 2022-10-21 14:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CreateRecordForm", description = "建档生成列表入参")
public class CreateArchivesForm {

    @NotNull(message = "设备信息id不能为空")
    @ApiModelProperty(value = "设备信息id",required = true)
    private Long deviceInfoId;

    @NotEmpty(message = "水电表mac不能为空")
    @ApiModelProperty(value = "水电表mac",required = true)
    private List<String> macList;

    @ApiModelProperty(value = "建档类型 0:建档 1:重试")
    private Integer type;
}
