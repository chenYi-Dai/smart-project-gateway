package com.example.clock.form;

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
 * @ClassName : DeviceListForm
 * @Description : 设备列表入参
 * @Date: 2022-08-30 14:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DeviceListForm", description = "设备列表入参")
public class DeviceListForm {

    @ApiModelProperty(value = "空间节点id，最后一个节点")
    private Long nodeId;

    @NotNull(message = "项目id不能为空")
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "采集器id")
    private Long collecteId;

    @ApiModelProperty(value = "设备品类")
    private List<String> category;

    @ApiModelProperty(value = "设备产品")
    private String product;

    @ApiModelProperty(value = "搜索字段")
    private String keywords;

    @ApiModelProperty(value = "工程状态(同步状态) 0 未同步 1已同步")
    private Integer syncStatus;

    @ApiModelProperty(value = "设备注册状态")
    private Integer registerStatus;
    /**
     * 用于统计设备总数列表接口
     */
    @ApiModelProperty(value = "所属页面：0项目设备主页，1项目设备统计总数页")
    private Integer pageType;

    private int start;

    private int size;

    private List<Long> parentIds;
}
