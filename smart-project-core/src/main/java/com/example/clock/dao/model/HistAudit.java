package com.example.clock.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistAudit {

    private Long id;

    private Long uid;

    private Integer histSeq;

    private Integer auditSeq;

    private String auditExtTags;

    private String auditTag;

    private Integer status;

    private String sign;

    private Date createTime;

    private Date modifyTime;

    private String operator;

    private String appRover;

    private Date operateTime;

    private Date appRoveTime;

}
