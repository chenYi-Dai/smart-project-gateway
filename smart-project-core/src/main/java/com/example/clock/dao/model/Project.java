package com.example.clock.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : ex_wen.fei
 * @ClassName : Project
 * @Description : 项目dto
 * @Date: 2022年9月8日
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    /**
     * 项目id
     */
    private Long id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 所属企业id
     */
    private Long eid;
    /**
     * 服务商Id
     */
    private Long serviceId;
    /**
     * 项目所在地
     */
    private String address;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建人手机号
     */
    private String createUserPhone;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
