/*
  血糖监测系统数据库脚本
  数据库名：glucose_monitor
  包含：用户表、患者表、血糖记录表、诊疗表、随访表
*/

-- 创建数据库
CREATE DATABASE IF NOT EXISTS glucose_monitor DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE glucose_monitor;

-- ----------------------------
-- 1. 用户表（登录用）
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
                          id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                          username VARCHAR(50) NOT NULL UNIQUE COMMENT '账号',
                          password VARCHAR(50) NOT NULL COMMENT '密码',
                          role VARCHAR(20) NOT NULL COMMENT '角色：admin/doctor/nurse/patient',
                          name VARCHAR(50) COMMENT '姓名',
                          create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

INSERT INTO sys_user (username,password,role,name) VALUES
                                                       ('admin','123456','admin','系统管理员'),
                                                       ('doctor','123456','doctor','王医生'),
                                                       ('nurse','123456','nurse','李护士'),
                                                       ('patient','123456','patient','张患者');

-- ----------------------------
-- 2. 患者信息表
-- ----------------------------
DROP TABLE IF EXISTS patient;
CREATE TABLE patient (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         patient_id VARCHAR(20) UNIQUE NOT NULL COMMENT '患者编号',
                         name VARCHAR(20) NOT NULL COMMENT '姓名',
                         gender VARCHAR(4) COMMENT '性别',
                         age INT COMMENT '年龄',
                         phone VARCHAR(20) COMMENT '电话',
                         id_card VARCHAR(30) COMMENT '身份证',
                         dept VARCHAR(30) DEFAULT '内分泌科' COMMENT '科室',
                         status VARCHAR(10) DEFAULT '在院' COMMENT '状态',
                         diabetes_history TEXT COMMENT '糖尿病史',
                         create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='患者信息';

INSERT INTO patient VALUES
                        (null,'HZ001','张三','男',45,'13800138000','110101198001011234','内分泌科','在院','2型糖尿病5年'),
                        (null,'HZ002','李丽','女',32,'13900139000','310101199002022345','内分泌科','在院','糖尿病1年');

-- ----------------------------
-- 3. 血糖数据表
-- ----------------------------
DROP TABLE IF EXISTS blood_sugar;
CREATE TABLE blood_sugar (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             patient_id VARCHAR(20) NOT NULL,
                             patient_name VARCHAR(20),
                             collect_time DATETIME,
                             time_type VARCHAR(10) COMMENT '空腹/餐后2h',
                             sugar_val DECIMAL(10,2) COMMENT '血糖值',
                             heart_rate INT COMMENT '心率',
                             blood_pressure VARCHAR(20) COMMENT '血压',
                             symptom TEXT COMMENT '症状',
                             create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='血糖记录';

-- ----------------------------
-- 4. 诊疗记录表
-- ----------------------------
DROP TABLE IF EXISTS treatment;
CREATE TABLE treatment (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           patient_id VARCHAR(20),
                           patient_name VARCHAR(20),
                           sugar_info VARCHAR(50),
                           diagnose VARCHAR(100),
                           plan TEXT,
                           treat_time DATE,
                           create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='诊疗记录';

-- ----------------------------
-- 5. 随访计划表
-- ----------------------------
DROP TABLE IF EXISTS follow_plan;
CREATE TABLE follow_plan (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             patient_id VARCHAR(20),
                             patient_name VARCHAR(20),
                             follow_type VARCHAR(20),
                             cycle VARCHAR(10),
                             content TEXT,
                             start_date DATE,
                             create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='随访计划';