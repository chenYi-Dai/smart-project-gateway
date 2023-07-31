package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : ex_yi.chen
 * @ClassName : RecordInfoVO
 * @Description : 建档列表对象
 * @Date: 2022-10-24 09:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RecordInfoVO", description = "建档列表对象")
public class ArchivesInfoVO implements Serializable {

    @ApiModelProperty(value = "空间节点id")
    private Long nodeId;

    @ApiModelProperty(value = "空间节点名称")
    private String nodeName;

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备识别码")
    private String mac;

    @ApiModelProperty(value = "建档完成状态 0:建档中 1:建档完成")
    private int archiveStatus;

    @ApiModelProperty(value = "建档成功失败状态 0失败 1成功")
    private int status;

    @ApiModelProperty(value = "注册状态 0失败 1成功")
    private int registerStatus;

    @ApiModelProperty(value = "超时时间")
    private Long overTime;

}
