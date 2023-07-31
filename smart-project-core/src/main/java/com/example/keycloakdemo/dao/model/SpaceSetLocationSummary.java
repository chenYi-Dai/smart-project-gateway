package com.example.keycloakdemo.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : ex_wen.fei
 * @ClassName : SpaceSetLocationSummary
 * @Description : 项目概要信息dto
 * @Date: 2022年9月8日
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpaceSetLocationSummary {
    /**
     * id
     */
    private Long id;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 空间集id
     */
    private Long spaceSetId;
    /**
     * 品类
     */
    private String categoryCode;
    /**
     * 品类名称
     */
    private String categoryName;
    /**
     * 产品key
     */
    private String productKey;
    /**
     * 产品名称
     */
    private String productName;

    /**
     * 通讯方式, 0为zigbee，1为485, 2为mbus 3为NB 4为以太网
     */
    private String correspond;
    /**
     * 预期安装设备数量
     */
    private Integer planQuantity;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


}
