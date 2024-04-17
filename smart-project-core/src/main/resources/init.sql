DROP
DROP TABLE IF EXISTS `t_customer_info`;
CREATE TABLE `t_customer_info`
(
    `Fgid`         bigint(0) NOT NULL COMMENT 'gid',
    `Fuid`         bigint(0) NOT NULL,
    `Fhist_seq`    int(0) NOT NULL DEFAULT 0,
    `Fgsm_code`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '国际区号',
    `Fmobile`      varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机',
    `Femail`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
    `Fcust_no`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '核心客户号',
    `Fname`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    `Fen_name`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    `Fsex`         tinyint(0) NULL DEFAULT NULL,
    `Fcus_status`  tinyint(0) NULL DEFAULT NULL COMMENT '1正常2破产3死亡',
    `Fopen_time`   datetime(0) NULL DEFAULT NULL COMMENT '开户时间',
    `Fclose_time`  datetime(0) NULL DEFAULT NULL COMMENT '销户时间',
    `Fspecial`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '0' COMMENT '0-普通用户 1-审核特别账户',
    `Fstatus`      tinyint(0) NOT NULL COMMENT '1.正常，2.禁用',
    `Fsign`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `Fcreate_time` datetime(0) NOT NULL COMMENT '创建时间',
    `Fmodify_time` datetime(0) NOT NULL COMMENT '修改时间',
    `Fremarks`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    INDEX          `idx_Fmodify_time`(`Fmodify_time`) USING BTREE,
    INDEX          `idx_Fgid`(`Fgid`) USING BTREE,
    INDEX          `idx_Fcust_no`(`Fcust_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `t_hist_audit`;
CREATE TABLE `t_hist_audit`(
    `Fpm_key_value`   bigint(20) NOT NULL,
    `Fuid`            bigint(20) NULL DEFAULT NULL,
    `Fhist_seq`       int(10) NOT NULL,
    `Faudit_seq`      int(10) NOT NULL,
    `Faudit_ext_tags` json NULL,
    `Faudit_tag`      varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    `Fstatus`         tinyint(4) NOT NULL COMMENT '状态1.正常 2 禁用',
    `Fsign`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '签名',
    `Fcreate_time`    datetime(0) NOT NULL COMMENT '创建时间',
    `Fmodify_time`    datetime(0) NOT NULL COMMENT '修改时间',
    `Foperator`       varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'xboss操作员',
    `Fapprover`       varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'xboss审批员',
    `Foperate_time`   datetime(0) NULL DEFAULT NULL COMMENT 'xboss操作时间',
    `Fapprove_time`   datetime(0) NULL DEFAULT NULL COMMENT 'xboss审批时间',
    PRIMARY KEY (`Fpm_key_value`, `Fhist_seq`, `Faudit_seq`) USING BTREE,
    UNIQUE INDEX `idx_pm`(`Fuid`, `Fhist_seq`, `Faudit_seq`) USING BTREE,
    INDEX             `idx_odb1`(`Fuid`, `Fhist_seq`, `Faudit_tag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '修改记录流水表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF NOT EXISTS `t_xboss_batch_update_job_info` (
                                                               `job_id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '任务ID',
    `update_type` tinyint(4) NOT NULL COMMENT '更新类型: 1-税务信息, 其它类型待定',
    `operator` varchar(41) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'xboss操作员',
    `approver` varchar(41) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'xboss审批员',
    `operate_time` datetime DEFAULT NULL COMMENT 'xboss操作时间',
    `approve_time` datetime DEFAULT NULL COMMENT 'xboss审批时间',
    `upd_cust_no` int NOT NULL COMMENT '更新客户数量',
    `upd_fail_cust_no` int  COMMENT '更新客户失败数量',
    `origin_file_path` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原始文件路径',
    `result_file_path` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '结果文件路径',
    `job_status` tinyint(4) NOT NULL COMMENT '任务状态: 1-已完成(成功), 2-进行中, 3-失败',
    `abort_reason` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '中止原因',
    `rstatus` tinyint(4) NOT NULL COMMENT '状态 1-正常 2-非正常',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `modify_time` datetime NOT NULL COMMENT '修改时间',
    key create_type_status_idx(create_time, update_type, rstatus)
    ) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='XBOSS批量更新客户信息记录表';
