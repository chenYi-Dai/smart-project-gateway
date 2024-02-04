package com.example.clock.vo;

import lombok.*;

import java.util.Date;

/**
 * @Author hlq
 * @Date 2018/8/3
 * @Description 房源信息
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House {


    /**
     * 房源id主键
     */

    private Long id;
    /**
     * 门店ID
     */
    private Long shopId;

    /**
     * 门店ID
     */
    private Long storeId;


    /**
     * 企业Id
     */
    private Long eid;
    /**
     * 房源类型:0为集中式,1为分散式
     */
    private Integer types;
    /**
     * 市
     */
    private Integer province;

    /**
     * 省
     */
    private Integer city;

    /**
     * 区
     */
    private Integer area;

    /**
     * 房源详细地址
     */
    private String houseAddress;

    /**
     * 栋名称
     */
    private String houseName;


    /**
     * 房源地址 省+市+区+homeSourceName+houseName
     */
    private String address;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否被冻结
     */
    private Integer freeze;
}
