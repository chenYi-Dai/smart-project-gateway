package com.example.keycloakdemo.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author : ex_yi.chen
 * @ClassName : NodeInfo
 * @Description : 空间节点
 * @Date: 2022-07-19 17:16
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SpaceNodeInfo {
    @ApiModelProperty("节点id")
    private Long id;

    @ApiModelProperty("父级节点id 0代表一级节点")
    private Long parentId;

    @ApiModelProperty("所属空间集id")
    private Long spaceSetId;

    @ApiModelProperty("企业id")
    private Long eid;

    @ApiModelProperty("空间名称")
    private String name;

    @ApiModelProperty("所属等级")
    private Integer level;

    @ApiModelProperty("是否最后一个节点 0是 1不是")
    private Integer isLast;

    @ApiModelProperty("节点上下方排序")
    private Integer orderNum;

    @ApiModelProperty("最后节点名称")
    private String locationName;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
