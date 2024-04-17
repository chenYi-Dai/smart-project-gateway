DROP database if exists test_sharding;
create database test_sharding;
use test_sharding;
DROP TABLE IF EXISTS `t_customer_info`;
CREATE TABLE `t_customer_info` (
   `Fgid` bigint(20) NOT NULL COMMENT 'gid',
   `Fuid` bigint(20) NOT NULL,
   `Fhist_seq` int(10) NOT NULL DEFAULT '0',
   `Fgsm_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '国际区号',
   `Fmobile` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
   `Femail` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
   `Femail_verify` tinyint(4) NOT NULL DEFAULT '0' COMMENT '邮箱是否验证，0-否、1-是',
   `Femail_ci` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱不区分大小写',
   `Fparty_num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '反洗钱唯一标志',
   `Fcust_no` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '核心客户号',
   `Fname` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Fen_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Ffirst_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Fmiddle_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Flast_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Fsex` tinyint(4) DEFAULT NULL,
   `Fbirthday` date DEFAULT NULL,
   `Fbirth_country` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Fnation` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Fresident_country` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Feducation_type` tinyint(4) DEFAULT NULL COMMENT '学历 1-小学及以下；2-初中；3-大专及以上',
   `Fcus_status` tinyint(3) DEFAULT NULL COMMENT '1正常2破产3死亡',
   `Fhkvb_employee_ind` tinyint(4) NOT NULL DEFAULT '2' COMMENT 'HKVB员工标志',
   `Fopen_time` datetime DEFAULT NULL COMMENT '开户时间',
   `Fclose_time` datetime DEFAULT NULL COMMENT '销户时间',
   `Fspecial` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '0-普通用户 1-审核特别账户',
   `Fstatus` tinyint(20) NOT NULL COMMENT '1.正常，2.禁用',
   `Fsign` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
   `Fcreate_time` datetime NOT NULL COMMENT '创建时间',
   `Fmodify_time` datetime NOT NULL COMMENT '修改时间',
   `Fremarks` json DEFAULT NULL,
   `Fekyc_qa_remarks` json DEFAULT NULL,
   `Fverified_en_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经校验英文地址',
   `FfinancialContract` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第一位：理财，0-否、1-是，第2~16位：扩展位',
   `Fposition_info` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Fpurpose` varchar(1536) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `Fchannel_source` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '开户渠道 APP, H5',
   `Fchannel` tinyint(4) DEFAULT NULL COMMENT '开户渠道授权方式 1密码 2邮箱，3手机，4微信，5fb 7apple',
   PRIMARY KEY (`Fuid`),
   KEY `idx_Fmodify_time` (`Fmodify_time`) USING BTREE,
   KEY `idx_Fgid` (`Fgid`) USING BTREE,
   KEY `idx_Fcust_no` (`Fcust_no`) USING BTREE,
   KEY `idx_Fhkvb_employee_ind` (`Fhkvb_employee_ind`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息';

DROP TABLE IF EXISTS `t_call_system_info`;
CREATE TABLE `t_call_system_info` (
      `Fcall_system_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用系统ID',
      `Fcall_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用系统名称',
      `Fauth_interface_flag` tinyint NOT NULL COMMENT '接口授权开关 0-关闭 1-开启',
      `Fprivate_key_encrypt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '私钥（解密）',
      `Fpublic_key_encrypt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公钥（加密）',
      `Fkey_ver_encrypt` int NOT NULL COMMENT '算法版本（加解密）',
      `Fkey_seq_encrypt` int NOT NULL COMMENT '序号（加解密）',
      `Fprivate_key_sign` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '私钥（解签）',
      `Fpublic_key_sign` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公钥（加签）',
      `Fkey_ver_sign` int NOT NULL COMMENT '算法版本（加解签）',
      `Fkey_seq_sign` int NOT NULL COMMENT '序号（加解签）',
      `Fsalt_ID` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐',
      `Fstatus` tinyint NOT NULL COMMENT '状态1.正常 2 禁用',
      `Fcreate_time` datetime NOT NULL COMMENT '创建时间',
      `Fmodify_time` datetime NOT NULL COMMENT '修改时间',
      `Fsdk_version` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
      PRIMARY KEY (`Fcall_system_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='接入系统授权表';

DROP TABLE IF EXISTS `t_hist_audit`;
CREATE TABLE `t_hist_audit` (
        `Fpm_key_value` bigint(20) NOT NULL,
        `Fuid` bigint(20) DEFAULT NULL,
        `Fhist_seq` int(10) NOT NULL,
        `Faudit_seq` int(10) NOT NULL,
        `Faudit_ext_tags` json DEFAULT NULL,
        `Faudit_tag` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
        `Fstatus` tinyint(4) NOT NULL COMMENT '状态1.正常 2 禁用',
        `Fsign` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '签名',
        `Fcreate_time` datetime NOT NULL COMMENT '创建时间',
        `Fmodify_time` datetime NOT NULL COMMENT '修改时间',
        `Foperator` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'xboss操作员',
        `Fapprover` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'xboss审批员',
        `Foperate_time` datetime DEFAULT NULL COMMENT 'xboss操作时间',
        `Fapprove_time` datetime DEFAULT NULL COMMENT 'xboss审批时间',
        `Fsource` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改来源：APP/XBOSS',
        `Fbiz_seq_no` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
        `Frecord_no` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '记录编号',
        PRIMARY KEY (`Fpm_key_value`,`Fhist_seq`,`Faudit_seq`) USING BTREE,
        UNIQUE KEY `idx_pm` (`Fuid`,`Fhist_seq`,`Faudit_seq`),
        UNIQUE KEY `uniq_record_no` (`Frecord_no`),
        KEY `idx_odb1` (`Fuid`,`Fhist_seq`,`Faudit_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='修改记录流水表';

DROP TABLE IF EXISTS `t_xboss_batch_update_job_info`;
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


