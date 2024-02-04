package com.example.clock.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : ex_wen.fei
 * @ClassName : LocationTopology
 * @Description : 设备拓扑关系表dto
 * @Date: 2022年9月8日
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationTopology {
    /**
     * id
     */
    private Long id;
    /**
     * 父级设备id 0:一级设备
     */
    private Long parentId;
    /**
     * 设备信息id
     */
    private Long locationId;
    /**
     * 当前层级 0为顶层
     */
    private Integer level;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
