/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : local_test

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 05/01/2023 14:33:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_customer_complaint_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_customer_complaint_info`;
CREATE TABLE `biz_customer_complaint_info`  (
  `customer_complaint_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客诉id',
  `lading_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '提单id',
  `lading_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '提单号',
  `customer_complaint_node_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前节点id',
  `group_company_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运输代理（公司主体id）',
  `correspondent_unit_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户（往来单位id）',
  `customer_complaint_status` int(1) NOT NULL DEFAULT 0 COMMENT '客诉状态 默认0 （0待核实，1待审核，2已审核，3待确认，4已确认，5已处理）',
  `pay_customers` tinyint(1) NOT NULL DEFAULT 0 COMMENT '赔付客户 默认0（0未赔付 1已赔付）',
  `proxy_pay` tinyint(1) NOT NULL DEFAULT 0 COMMENT '代理赔付 默认0 （0无需赔付 1已赔付 2未赔付）',
  `customer_complaint_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客诉类型',
  `customer_complaint_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客诉原因',
  `customer_complaint_time` datetime(0) NULL DEFAULT NULL COMMENT '投诉时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enclosure` json NULL COMMENT '附件',
  `airline_company` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '航空公司',
  `flight_no` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '航班号',
  `eta_time` datetime(0) NULL DEFAULT NULL COMMENT 'eta时间',
  `etd_time` datetime(0) NULL DEFAULT NULL COMMENT 'etd时间',
  `ata_time` datetime(0) NULL DEFAULT NULL COMMENT 'ata时间',
  `atd_time` datetime(0) NULL DEFAULT NULL COMMENT 'atd时间',
  `departure_port` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '起运港',
  `destination_port` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '目的港',
  `container_num` int(11) NULL DEFAULT 0 COMMENT '预报数',
  `air_actual_container_num` int(11) NULL DEFAULT 0 COMMENT '起飞数(航空实际数)',
  `arrive_num` int(11) NULL DEFAULT 0 COMMENT '落地数',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人id',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除状态',
  `version` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`customer_complaint_info_id`) USING BTREE,
  INDEX `customer_complaint_node_id_index`(`customer_complaint_node_id`) USING BTREE COMMENT '客诉节点索引',
  INDEX `group_company_id_index`(`group_company_id`) USING BTREE,
  INDEX `correspondent_unit_id_index`(`correspondent_unit_id`) USING BTREE,
  UNIQUE INDEX `lading_id_uindex`(`lading_id`) USING BTREE COMMENT '提单索引',
  UNIQUE INDEX `lading_number_uindex`(`lading_number`) USING BTREE,
  INDEX `customer_complaint_time_index`(`customer_complaint_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客诉信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for biz_customer_complaint_node
-- ----------------------------
DROP TABLE IF EXISTS `biz_customer_complaint_node`;
CREATE TABLE `biz_customer_complaint_node`  (
  `customer_complaint_node_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客诉节点id',
  `customer_complaint_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客诉详情id',
  `customer_complaint_node_status` int(1) NOT NULL DEFAULT 0 COMMENT '客诉节点状态（0 已核实，1 转交审核，2 已审核(待出方案)，3 转交方案，4 待确认方案，5 确认方案，6 赔付客户，7 代理赔付，8 已办结）',
  `node_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点内容',
  `node_content_remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点内容备注、描述',
  `customer_complaint_node_pay_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '赔付类型（赔付客户 payCustomers，代理赔付 proxyPay ）',
  `pay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '赔付金额',
  `currency_code` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '货币代码',
  `current_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前处理人',
  `transfer_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '转交处理人',
  `processor_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理者（审核人，方案处理人）',
  `enclosure` json NULL COMMENT '附件',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人id',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除状态',
  `version` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`customer_complaint_node_id`) USING BTREE,
  INDEX `customer_complaint_info_id_index`(`customer_complaint_info_id`) USING BTREE COMMENT '客诉详情索引',
  INDEX `current_user_id_index`(`current_user_id`) USING BTREE,
  INDEX `transfer_user_id_index`(`transfer_user_id`) USING BTREE,
  INDEX `processor_user_id_index`(`processor_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客诉信息节点表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for biz_customer_complaint_type
-- ----------------------------
DROP TABLE IF EXISTS `biz_customer_complaint_type`;
CREATE TABLE `biz_customer_complaint_type`  (
  `customer_complaint_type_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客诉id',
  `customer_complaint_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客诉类型',
  `customer_complaint_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客诉原因',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人名称',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '更新人名称',
  `update_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人id',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除状态',
  `version` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`customer_complaint_type_id`) USING BTREE,
  INDEX `customer_complaint_type_index`(`customer_complaint_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客诉类型表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
