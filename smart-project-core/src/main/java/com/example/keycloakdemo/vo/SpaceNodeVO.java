package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : SpaceNodeVO
 * @Description : 空间节点对象
 * @Date: 2022-07-14 16:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "空间节点对象", description = "空间节点对象")
public class SpaceNodeVO {

    @ApiModelProperty("空间节点id")
    private Long nodeId;

    @ApiModelProperty("所属空间集id")
    private Long spaceSetId;

    @ApiModelProperty("企业id")
    private Long eid;

    @ApiModelProperty("空间节点名称")
    private String name;

    @ApiModelProperty("空间最后的拼接节点名称")
    private String locationName;

    @ApiModelProperty("空间节点层级")
    private Integer level;

    @ApiModelProperty("父级节点id 父级节点id为0 是一级节点")
    private Long parentId;

    @ApiModelProperty("排序id")
    private Integer orderNum;

    @ApiModelProperty("是否是最后节点 0是 1不是")
    private Integer isLast;

    @ApiModelProperty("设备信息")
    private List<DeviceVO> devices;
}
