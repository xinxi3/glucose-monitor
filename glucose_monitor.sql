/*
 Navicat Premium Dump SQL

 Source Server         : mysql数据库
 Source Server Type    : MySQL
 Source Server Version : 80042 (8.0.42)
 Source Host           : localhost:3306
 Source Schema         : glucose_monitor

 Target Server Type    : MySQL
 Target Server Version : 80042 (8.0.42)
 File Encoding         : 65001

 Date: 21/05/2026 15:22:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blood_sugar
-- ----------------------------
DROP TABLE IF EXISTS `blood_sugar`;
CREATE TABLE `blood_sugar`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `patient_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者编号',
  `patient_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者姓名',
  `collect_time` datetime NOT NULL COMMENT '采集时间',
  `time_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '测量时段：空腹 / 餐后2h',
  `sugar_val` decimal(5, 2) NOT NULL COMMENT '血糖值 mmol/L',
  `heart_rate` int NULL DEFAULT NULL COMMENT '心率',
  `blood_pressure` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '血压',
  `symptom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '症状',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `patient_id`(`patient_id` ASC) USING BTREE,
  CONSTRAINT `blood_sugar_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '血糖采集记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blood_sugar
-- ----------------------------
INSERT INTO `blood_sugar` VALUES (1, 'HZ005', '宋七', '2026-05-19 22:38:00', '餐后2h', 10.10, 100, '90', '血糖偏高', '2026-05-19 14:38:54');
INSERT INTO `blood_sugar` VALUES (2, 'HZ002', '李丽', '2026-05-19 22:55:00', '餐后2h', 20.50, 90, '110', '血糖偏高', '2026-05-19 14:56:30');
INSERT INTO `blood_sugar` VALUES (3, 'HZ002', '李丽', '2026-05-19 22:55:00', '餐后2h', 10.50, 90, '110', '血糖偏高', '2026-05-18 14:56:30');
INSERT INTO `blood_sugar` VALUES (4, 'HZ002', '李丽', '2026-05-19 22:55:00', '餐后2h', 7.50, 90, '110', '血糖偏高', '2026-05-18 14:56:30');
INSERT INTO `blood_sugar` VALUES (5, 'HZ002', '李丽', '2026-05-20 17:10:00', '空腹', 15.00, 110, '120', '无', '2026-05-20 09:11:49');
INSERT INTO `blood_sugar` VALUES (6, 'HZ002', '李丽', '2026-05-21 19:53:00', '餐后2h', 10.50, NULL, NULL, '状态良好', '2026-05-21 11:53:19');

-- ----------------------------
-- Table structure for follow_execute
-- ----------------------------
DROP TABLE IF EXISTS `follow_execute`;
CREATE TABLE `follow_execute`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `follow_id` bigint NOT NULL COMMENT '关联随访计划ID（follow_up.id）',
  `patient_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者ID',
  `patient_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者姓名',
  `follow_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '随访类型',
  `health_event` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '本次健康事件记录',
  `execute_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '执行时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2056994674052198402 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '随访执行记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of follow_execute
-- ----------------------------
INSERT INTO `follow_execute` VALUES (2056938672388947970, 6, 'HZ005', '宋七', '用药指导随访', '血糖正常，体温正常', '2026-05-20 11:22:50');
INSERT INTO `follow_execute` VALUES (2056943051368161282, 6, 'HZ005', '宋七', '用药指导随访', '血糖正常，体温正常', '2026-05-20 11:40:14');
INSERT INTO `follow_execute` VALUES (2056991895954944001, 3, 'HZ001', '张三', '复查提醒随访', '复查提醒，体温正常', '2026-05-20 14:54:19');
INSERT INTO `follow_execute` VALUES (2056994674052198401, 5, 'HZ001', '张三', '饮食指导随访', '体温正常，饮食正常', '2026-05-20 15:05:22');

-- ----------------------------
-- Table structure for follow_up
-- ----------------------------
DROP TABLE IF EXISTS `follow_up`;
CREATE TABLE `follow_up`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '随访ID',
  `patient_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者ID',
  `patient_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者姓名',
  `follow_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '随访类型',
  `follow_date` date NULL DEFAULT NULL COMMENT '随访日期',
  `follow_cycle` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '随访周期',
  `follow_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '随访内容',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `is_execute` tinyint NULL DEFAULT 0 COMMENT '是否执行：0=待执行，1=已执行',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '随访计划表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of follow_up
-- ----------------------------
INSERT INTO `follow_up` VALUES (1, 'HZ002', '李丽', '用药指导', '2026-05-19', '每周', '指导如何用药', '2026-05-19 15:30:23', 0);
INSERT INTO `follow_up` VALUES (2, 'HZ005', '宋七', '饮食指导随访', '2026-05-19', '每月', '少喝含糖饮料', '2026-05-19 21:25:02', 0);
INSERT INTO `follow_up` VALUES (3, 'HZ001', '张三', '复查提醒随访', '2026-05-21', '每季度', '提醒复查时间', '2026-05-19 21:36:37', 1);
INSERT INTO `follow_up` VALUES (4, 'HZ004', '赵四', '血糖监测随访', '2026-05-24', '每日', '血糖检测', '2026-05-19 21:42:24', 0);
INSERT INTO `follow_up` VALUES (5, 'HZ001', '张三', '饮食指导随访', '2026-05-22', '每日', '少吃面食', '2026-05-19 21:47:53', 1);

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `dept` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '在院',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `diabetes_history` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `patient_id`(`patient_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (1, 'HZ001', '张三', '男', 45, '内分泌科', '在院', '13800138000', '1101011980xxxxxx', '2型糖尿病5年');
INSERT INTO `patient` VALUES (2, 'HZ002', '李丽', '女', 32, '内分泌科', '在院', '13900139000', '3101011990xxxxxx', '糖尿病1年');
INSERT INTO `patient` VALUES (3, 'HZ003', '王五', '男', 50, '内分泌科', '在院', '13900139001', '3101011991xxxxxx', '糖尿病2年');
INSERT INTO `patient` VALUES (4, 'HZ004', '赵四', '男', 36, '内分泌科', '在院', '13613851228', '12345678XXXXXXXX', '无');
INSERT INTO `patient` VALUES (5, 'HZ005', '宋七', '男', 23, '内分泌科', '在院', '16638336612', '16638336612xxxx', '无');

-- ----------------------------
-- Table structure for sugar_plan
-- ----------------------------
DROP TABLE IF EXISTS `sugar_plan`;
CREATE TABLE `sugar_plan`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `patient_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `plan_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '控糖方案内容',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sugar_plan
-- ----------------------------
INSERT INTO `sugar_plan` VALUES (2, 'HZ002', '李丽', '少喝含糖饮料', '2026-05-19 20:57:03', '2026-05-19 20:57:03');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '123456', 'admin', '系统管理员');
INSERT INTO `sys_user` VALUES (2, 'doctor', '123456', 'doctor', '张医生');
INSERT INTO `sys_user` VALUES (3, 'nurse', '123456', 'nurse', '李护士');
INSERT INTO `sys_user` VALUES (4, 'patient', '123456', 'patient', '王五');
INSERT INTO `sys_user` VALUES (5, '周思雨', '123456', 'patient', '周思雨');
INSERT INTO `sys_user` VALUES (6, 'HZ002', '123456', 'patient', '李丽');

-- ----------------------------
-- Table structure for treat_record
-- ----------------------------
DROP TABLE IF EXISTS `treat_record`;
CREATE TABLE `treat_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '诊疗ID',
  `patient_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者ID',
  `patient_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者姓名',
  `blood_sugar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '血糖情况',
  `diagnosis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '诊断结果',
  `treat_plan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '治疗方案',
  `treat_time` date NULL DEFAULT NULL COMMENT '诊疗时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `monitor_freq` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '监测频率',
  `doctor_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主治医生',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '诊疗记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of treat_record
-- ----------------------------
INSERT INTO `treat_record` VALUES (2, 'HZ002', '李丽', NULL, '糖耐量异常', '饮食+运动', '2026-05-21', '2026-05-21 10:29:23', '每日空腹+餐后', '张医生');

SET FOREIGN_KEY_CHECKS = 1;
