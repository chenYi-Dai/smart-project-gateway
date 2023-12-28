package com.example.keycloakdemo.dao.model;


import com.example.keycloakdemo.annotation.SensitiveFields;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {
    private Long gid;

    private Long uid;

    private Integer histSeq;

    private String gsmCode;

    @SensitiveFields
    private String mobile;

    @SensitiveFields
    private String email;

    private String custNo;

    @SensitiveFields
    private String name;

    private String enName;

    private Integer sex;

    private Integer cusStatus;

    private Date openTime;

    private Date closeTime;

    private String special;

    private Integer status;

    private String sign;

    private Date createTime;

    private Date modifyTime;

    private String remarks;
}
