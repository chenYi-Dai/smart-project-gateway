package com.example.clock.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : ex_wen.fei
 * @ClassName : ProjectEngineerMapping
 * @Description : 项目施工人员关系表dto
 * @Date: 2022年9月8日
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEngineerMapping {
    /**
     * id
     */
    private Long id;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 施工人员id
     */
    private Long engineerId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
