package com.example.keycloakdemo.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : ex_wen.fei
 * @ClassName : Location
 * @Description : 空间设备信息表
 * @Date: 2022年9月8日
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    /**
     * id
     */
    private Long id;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 空间名称
     */
    private String nodeName;
    /**
     * 设备别名
     */
    private String deviceName;
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 空间集id
     */
    private Long spaceSetId;
    /**
     * 空间节点id
     */
    private Long nodeId;
    /**
     * 品类
     */
    private String categoryCode;
    /**
     * 产品
     */
    private String productKey;
    /**
     * 设备识别码
     */
    private String mac;
    /**
     * (工程)同步状态 0 未同步 1已同步
     */
    private Integer syncStatus;
    /**
     * 注册状态 0 未注册 1已注册
     */
    private Integer registerStatus;
    /**
     * 绑定人
     */
    private String bindUser;
    /**
     * 通讯方式, 0为zigbee，1为485, 2为mbus 3为NB 4为以太网
     */
    private String correspond;
    /**
     * 网络类型 0-无线 1-有线 zigbee nb-iot wifi 这三种通讯方式都是无线，其余的是有线
     */
    private Integer networkType;
    /**
     * 绑定时间
     */
    private Date bindTime;
    /**
     * 注册时间
     */
    private Date registTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
