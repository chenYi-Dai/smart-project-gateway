package com.example.keycloakdemo.vo;

import lombok.*;

import java.util.Date;

/**
 * Author hlq 2018/8/3
 * 房间信息
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {


    /**
     * 房间id主键
     */
    private Long id;

    /**
     * 房间对应的房源类型：房源类型:0为集中式,1为分散式
     */
    private Integer types;

    /**
     * 第三方ID
     */
    private String thirdId;

    /**
     * 房源id
     */
    private Long houseId;

    /**
     * 房间号
     */
    private String homeNo;

    /**
     * 房间类型:0-单间 1-公共空间 2-虚拟空间 3-套间
     */
    private Integer type;

    /**
     * 楼层
     */
    private String floorNum;


    /**
     * 详细地址加房间号
     */
    private String address;

    private Long fid;

    private Long sort;

    private Integer del;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private String rName;


}
