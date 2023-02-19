package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : ChildNodeInfoVO
 * @Description : 查询所有子节点的节点信息
 * @Date: 2022-08-31 10:08
 */
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ChildNodeInfoVO", description = "查询所有子节点的节点信息")
public class ChildNodeInfoVO {

    @ApiModelProperty(value = "空间节点下的设备信息")
    private List<DeviceVO> deviceLists;


}
