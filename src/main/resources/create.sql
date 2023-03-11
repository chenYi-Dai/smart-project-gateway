/*
 Navicat Premium Data Transfer

 Source Server         : lunix
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : 192.168.204.129:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 11/03/2023 17:48:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                             `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                             `departmentId` int(255) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `createdUser` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `modifiedUser` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `email` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `createTime` datetime(0) NULL DEFAULT NULL,
                                `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_location
-- ----------------------------
DROP TABLE IF EXISTS `tb_location`;
CREATE TABLE `tb_location`  (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `projectId` bigint(20) NOT NULL DEFAULT 0 COMMENT '项目id',
                                `name` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '空间名称',
                                `deviceId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '设备id',
                                `spaceSetId` bigint(20) NOT NULL DEFAULT 0 COMMENT '空间集id',
                                `nodeId` bigint(20) NOT NULL DEFAULT 0 COMMENT '空间节点id',
                                `categoryCode` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '品类',
                                `productKey` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '产品',
                                `mac` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '设备识别码',
                                `alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '设备名称',
                                `networkType` tinyint(4) NOT NULL DEFAULT 0 COMMENT '网络类型 0-无线 1-有线',
                                `correspond` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '通讯方式, 0为zigbee，1为485, 2为mbus 3为NB 4为以太网',
                                `syncStatus` tinyint(4) NOT NULL DEFAULT 0 COMMENT '(工程)同步状态 0 未同步 1已同步',
                                `registerStatus` tinyint(4) NOT NULL DEFAULT 0 COMMENT '注册状态 0 未注册 1已注册',
                                `bindUser` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '绑定人',
                                `bindTime` datetime(0) NULL DEFAULT NULL COMMENT '绑定时间',
                                `registTime` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
                                `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '绑定空间设备信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_location_topology
-- ----------------------------
DROP TABLE IF EXISTS `tb_location_topology`;
CREATE TABLE `tb_location_topology`  (
                                         `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                         `parentId` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级设备id 0:一级设备',
                                         `locationId` bigint(20) NOT NULL DEFAULT 0 COMMENT '设备信息id',
                                         `level` tinyint(4) NOT NULL DEFAULT 0 COMMENT '当前层级',
                                         `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                         `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '拓扑关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_project
-- ----------------------------
DROP TABLE IF EXISTS `tb_project`;
CREATE TABLE `tb_project`  (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目id',
                               `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '项目名称',
                               `eid` bigint(20) NOT NULL DEFAULT 0 COMMENT '所属企业id',
                               `serviceId` bigint(20) NOT NULL DEFAULT 0 COMMENT '服务商id',
                               `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '项目所在地',
                               `remark` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
                               `createUser` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人',
                               `createUserPhone` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人手机号',
                               `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                               `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '项目信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_project_engineer_mapping
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_engineer_mapping`;
CREATE TABLE `tb_project_engineer_mapping`  (
                                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                                `projectId` bigint(20) NOT NULL DEFAULT 0 COMMENT '项目id',
                                                `engineerId` bigint(20) NOT NULL DEFAULT 0 COMMENT '工程师id',
                                                `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                                `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '项目施工人员关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_space_node_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_space_node_info`;
CREATE TABLE `tb_space_node_info`  (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '空间节点id',
                                       `parentId` bigint(20) NULL DEFAULT 0 COMMENT '父级节点id 0代表一级节点',
                                       `spaceSetId` bigint(20) NOT NULL DEFAULT 0 COMMENT '所属空间集id',
                                       `eid` bigint(20) NOT NULL COMMENT '企业id',
                                       `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '空间名称',
                                       `level` int(11) NOT NULL DEFAULT 0 COMMENT '所属等级',
                                       `orderNum` int(11) NULL DEFAULT NULL COMMENT '上下方节点排序',
                                       `isLast` int(11) NULL DEFAULT 0 COMMENT '是否是最后节点 0是 1不是',
                                       `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                       `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                                       `locationName` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
                                       PRIMARY KEY (`id`) USING BTREE,
                                       INDEX `roomIdIndex`(`spaceSetId`) USING BTREE,
                                       INDEX `eidIndex`(`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '空间信息节点表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_space_node_info_back01
-- ----------------------------
DROP TABLE IF EXISTS `tb_space_node_info_back01`;
CREATE TABLE `tb_space_node_info_back01`  (
                                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '空间节点id',
                                              `parentId` bigint(20) NULL DEFAULT 0 COMMENT '父级节点id 0代表一级节点',
                                              `spaceSetId` bigint(20) NOT NULL DEFAULT 0 COMMENT '所属空间集id',
                                              `eid` bigint(20) NOT NULL COMMENT '企业id',
                                              `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '空间名称',
                                              `level` int(11) NOT NULL DEFAULT 0 COMMENT '所属等级',
                                              `orderNum` int(11) NULL DEFAULT NULL COMMENT '上下方节点排序',
                                              `isLast` int(11) NULL DEFAULT 0 COMMENT '是否是最后节点 0是 1不是',
                                              `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                              `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                                              `locationName` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
                                              PRIMARY KEY (`id`) USING BTREE,
                                              INDEX `roomIdIndex`(`spaceSetId`) USING BTREE,
                                              INDEX `eidIndex`(`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '空间信息节点表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_space_node_info_back_copy1
-- ----------------------------
DROP TABLE IF EXISTS `tb_space_node_info_back_copy1`;
CREATE TABLE `tb_space_node_info_back_copy1`  (
                                                  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '空间节点id',
                                                  `parentId` bigint(20) NULL DEFAULT 0 COMMENT '父级节点id 0代表一级节点',
                                                  `spaceSetId` bigint(20) NOT NULL DEFAULT 0 COMMENT '所属空间集id',
                                                  `eid` bigint(20) NOT NULL COMMENT '企业id',
                                                  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '空间名称',
                                                  `level` int(11) NOT NULL DEFAULT 0 COMMENT '所属等级',
                                                  `orderNum` int(11) NULL DEFAULT NULL COMMENT '上下方节点排序',
                                                  `isLast` int(11) NULL DEFAULT 0 COMMENT '是否是最后节点 0是 1不是',
                                                  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                                  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                                                  `locationName` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
                                                  PRIMARY KEY (`id`) USING BTREE,
                                                  INDEX `roomIdIndex`(`spaceSetId`) USING BTREE,
                                                  INDEX `eidIndex`(`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '空间信息节点表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_space_set
-- ----------------------------
DROP TABLE IF EXISTS `tb_space_set`;
CREATE TABLE `tb_space_set`  (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '空间id',
                                 `eid` bigint(20) NOT NULL COMMENT '所属企业id',
                                 `serviceId` bigint(20) NULL DEFAULT NULL,
                                 `eidName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '企业名称',
                                 `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '施工空间名',
                                 `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地址',
                                 `createUser` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建人',
                                 `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '手机号码',
                                 `label` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '业务标签',
                                 `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                 `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '空间集信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_spaceset_location_summary
-- ----------------------------
DROP TABLE IF EXISTS `tb_spaceset_location_summary`;
CREATE TABLE `tb_spaceset_location_summary`  (
                                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                                 `projectId` bigint(20) NOT NULL DEFAULT 0 COMMENT '项目id',
                                                 `spaceSetId` bigint(20) NOT NULL DEFAULT 0 COMMENT '空间集id',
                                                 `categoryCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '品类',
                                                 `categoryName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '品类名称',
                                                 `productKey` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '产品key',
                                                 `productName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '产品名称',
                                                 `correspond` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '通讯方式, 0为zigbee，1为485, 2为mbus 3为NB 4为以太网',
                                                 `planQuantity` bigint(20) NOT NULL DEFAULT 0 COMMENT '预期安装设备数量',
                                                 `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                                 `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '项目概要信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for testinfo
-- ----------------------------
DROP TABLE IF EXISTS `testinfo`;
CREATE TABLE `testinfo`  (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '站点名称',
                             `url` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                             `alexa` int(11) NOT NULL DEFAULT 0 COMMENT 'Alexa 排名',
                             `sal` double NULL DEFAULT NULL COMMENT '广告收入',
                             `country` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '国家',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
