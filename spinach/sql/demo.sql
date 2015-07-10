/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-07-10 23:29:16
*/
CREATE DATABASE demo;
use demo;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('scheduler', '12', '1', '0 0/5 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('scheduler', '12', '1', null, 'com.yang.spinach.frame.utils.ScheduleJobTask', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000B7363686564756C654A6F627372002F636F6D2E79616E672E7370696E6163682E7363686564756C654A6F622E656E746974792E5363686564756C654A6F6200000000000000010200064C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000B6465736372697074696F6E71007E00094C000567726F757071007E00094C00046E616D6571007E00094C000673746174757371007E0009787074002C636F6D2E79616E672E7370696E6163682E6672616D652E7574696C732E5363686564756C654A6F625461736B74000D3020302F35202A202A202A203F740000740001317400023132740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('scheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('scheduler', 'asus1436456205236', '1436456235924', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('scheduler', '12', '1', '12', '1', null, '1436372400000', '1436372181771', '5', 'PAUSED', 'CRON', '1436371523000', '0', null, '0', '');

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` mediumint(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号码',
  `password` varchar(50) NOT NULL,
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL,
  `user_type` int(2) NOT NULL DEFAULT '0' COMMENT '用户类型 0:普通用户,1:会员用户,2:管理员用户',
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `disabled` int(2) NOT NULL DEFAULT '0' COMMENT '是否可用(0:可用 1:不可用)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('1', 'demo', 'demo', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', 'demo@demo.com', '2', '2015-05-31 16:30:38', '0');
INSERT INTO `t_account` VALUES ('2', 'user', 'user1', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', '10001@qq.com', '0', '2015-06-04 21:47:49', '1');
INSERT INTO `t_account` VALUES ('3', 'test', 'test', 'MD5:e10adc3949ba59abbe56e057f20f883e', '13000000000', '111@qq.com', '0', '2015-05-31 21:14:47', '0');
INSERT INTO `t_account` VALUES ('5', 'test1', '1111', 'MD5:e10adc3949ba59abbe56e057f20f883e', '13000000000', '11@qq.com', '0', '2015-05-31 21:19:20', '0');
INSERT INTO `t_account` VALUES ('6', 'abcd', 'qwer', 'MD5:e10adc3949ba59abbe56e057f20f883e', '13000000001', 'qq@qq.com', '0', '2015-06-04 21:48:45', '0');
INSERT INTO `t_account` VALUES ('8', '111111', '111111', 'MD5:e10adc3949ba59abbe56e057f20f883e', '13000000000', '1@1.com', '0', '2015-06-28 00:29:34', '0');
INSERT INTO `t_account` VALUES ('9', '1231', '1233', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', '1@1.cm', '0', '2015-06-28 00:34:33', '0');

-- ----------------------------
-- Table structure for t_account_role
-- ----------------------------
DROP TABLE IF EXISTS `t_account_role`;
CREATE TABLE `t_account_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_ROL_REFERENCE_ROLE` (`role_id`) USING BTREE,
  KEY `FK_USER_ROL_REFERENCE_USERS` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表';

-- ----------------------------
-- Records of t_account_role
-- ----------------------------
INSERT INTO `t_account_role` VALUES ('1', '1', '1');
INSERT INTO `t_account_role` VALUES ('37', '2', '1');
INSERT INTO `t_account_role` VALUES ('38', '2', '2');
INSERT INTO `t_account_role` VALUES ('39', '2', '4');
INSERT INTO `t_account_role` VALUES ('40', '9', '5');
INSERT INTO `t_account_role` VALUES ('41', '9', '6');

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(200) NOT NULL DEFAULT '' COMMENT '显示内容',
  `value` int(10) NOT NULL DEFAULT '0' COMMENT '值',
  `target_column` varchar(10) DEFAULT '' COMMENT '对应字段',
  `descrption` varchar(100) DEFAULT '' COMMENT '描述',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `disabled` int(2) NOT NULL DEFAULT '0' COMMENT '是否启用:0启用,1不启用',
  PRIMARY KEY (`id`),
  KEY `target_column` (`target_column`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', '普通用户', '0', 'user_type', '用户类型1', '1', 's', '0');
INSERT INTO `t_dict` VALUES ('2', '会员用户', '1', 'user_type', '用户类型', '2', ' ', '0');
INSERT INTO `t_dict` VALUES ('3', '管理员用户', '2', 'user_type', '用户类型', '3', '', '0');
INSERT INTO `t_dict` VALUES ('4', 's', '1', 's', 's', '1', 's', '0');

-- ----------------------------
-- Table structure for t_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_resources`;
CREATE TABLE `t_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '父节点id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `type` int(2) DEFAULT '0' COMMENT '类型:0:菜单,1:按钮',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `url` varchar(200) DEFAULT ' ',
  `permission` varchar(50) NOT NULL DEFAULT ' ' COMMENT '菜单编码',
  `icon` varchar(100) DEFAULT ' ',
  `disabled` tinyint(10) NOT NULL DEFAULT '0',
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of t_resources
-- ----------------------------
INSERT INTO `t_resources` VALUES ('1', '0', '系统管理', '0', '1', '', 'sys:manager', 'icon-cogs', '0', '');
INSERT INTO `t_resources` VALUES ('2', '1', '角色管理', '0', '3', '/role/list', 'sys:role:list', ' icon-user', '0', '');
INSERT INTO `t_resources` VALUES ('3', '1', '用户管理', '0', '2', '/user/list', 'sys:user:list', 'icon-hamburg-user', '0', '');
INSERT INTO `t_resources` VALUES ('4', '2', '添加', '1', null, '', 'sys:role:add', '', '0', '角色添加1');
INSERT INTO `t_resources` VALUES ('5', '2', '删除', '1', null, '', 'sys:role:delete', '', '0', '角色删除');
INSERT INTO `t_resources` VALUES ('6', '2', '修改', '1', null, '', 'sys:role:update', '', '0', '角色修改');
INSERT INTO `t_resources` VALUES ('7', '3', '添加', '1', null, '', 'sys:user:add', '', '0', '用户添加');
INSERT INTO `t_resources` VALUES ('8', '3', '删除', '1', null, '', 'sys:user:delete', '', '0', '用户删除');
INSERT INTO `t_resources` VALUES ('10', '11', '数据源监控', '0', '6', '/monitoring', 'monitoring:data', 'icon-hamburg-database', '0', '');
INSERT INTO `t_resources` VALUES ('11', '0', '系统监控', '0', '5', '', 'monitoring:system', 'icon-bar-chart', '0', '');
INSERT INTO `t_resources` VALUES ('12', '3', '修改', '1', null, '', 'sys:user:update', '', '0', '用户修改');
INSERT INTO `t_resources` VALUES ('13', '24', '添加', '1', null, '', 'sys:perm:add', '', '0', '菜单添加');
INSERT INTO `t_resources` VALUES ('14', '24', '修改', '1', null, '', 'sys:perm:update', '', '0', '菜单修改');
INSERT INTO `t_resources` VALUES ('15', '24', '删除', '1', null, '', 'sys:perm:delete', '', '0', '菜单删除');
INSERT INTO `t_resources` VALUES ('16', '2', '查看', '1', null, '', 'sys:role:view', '', '0', '角色查看');
INSERT INTO `t_resources` VALUES ('17', '3', '查看', '1', null, '', 'sys:user:view', '', '0', '用户查看');
INSERT INTO `t_resources` VALUES ('19', '3', '查看用户角色', '1', null, '', 'sys:user:roleView', '', '0', '查看用户角色');
INSERT INTO `t_resources` VALUES ('22', '2', '查看角色权限', '1', null, '', 'sys:role:permView', '', '0', '查看角色拥有的权限');
INSERT INTO `t_resources` VALUES ('23', '11', '定时任务管理', '0', '9', '/scheduleJob/list', 'sys:scheduleJob:list', 'icon-hamburg-full-time', '0', '定时任务管理，支持集群');
INSERT INTO `t_resources` VALUES ('24', '1', '菜单管理', '0', '4', '/resources/list', 'sys:perm:manager', 'icon-hamburg-old-versions', '0', '');
INSERT INTO `t_resources` VALUES ('25', '1', '字典管理', '0', '6', '/dict/list', 'sys:dictionaries', 'icon-hamburg-address', '0', '数据字典管理');
INSERT INTO `t_resources` VALUES ('28', '1', 'a', '0', null, '', '11', '', '1', '');
INSERT INTO `t_resources` VALUES ('29', '25', '添加数据字典', '1', '2', '', 'sys:dict:add', '', '0', '添加数据字典');
INSERT INTO `t_resources` VALUES ('30', '25', '数据字典修改', '1', '3', '', 'sys:dict:update', '', '0', '数据字典修改');
INSERT INTO `t_resources` VALUES ('31', '23', '添加定时任务', '1', '1', ' ', 'sys:scheduleJob:add', ' ', '0', '添加定时任务');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `role_code` varchar(20) NOT NULL,
  `description` text,
  `sort` int(6) DEFAULT '99',
  `disabled` int(2) NOT NULL DEFAULT '0' COMMENT '是否启用:0启用,1弃用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', 'admin', 'admin', '2', '0');
INSERT INTO `t_role` VALUES ('2', 'guest', 'guest', 'guest', '3', '0');
INSERT INTO `t_role` VALUES ('3', 'superadmin', 'superadmin', '超级管理员', '1', '0');
INSERT INTO `t_role` VALUES ('4', 'test', 'test', '11', '4', '0');
INSERT INTO `t_role` VALUES ('5', 'sdfs', 'sss', 'sssss', '5', '0');
INSERT INTO `t_role` VALUES ('6', '1', '1', '1', '99', '0');

-- ----------------------------
-- Table structure for t_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resources`;
CREATE TABLE `t_role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resources_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ROLE_PER_REFERENCE_PERMISSI` (`resources_id`) USING BTREE,
  KEY `FK_ROLE_PER_REFERENCE_ROLE` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='资源-角色关联表';

-- ----------------------------
-- Records of t_role_resources
-- ----------------------------
INSERT INTO `t_role_resources` VALUES ('1', '1', '1');
INSERT INTO `t_role_resources` VALUES ('2', '1', '2');
INSERT INTO `t_role_resources` VALUES ('3', '1', '3');
INSERT INTO `t_role_resources` VALUES ('4', '1', '4');
INSERT INTO `t_role_resources` VALUES ('5', '1', '5');
INSERT INTO `t_role_resources` VALUES ('6', '1', '6');
INSERT INTO `t_role_resources` VALUES ('7', '1', '7');
INSERT INTO `t_role_resources` VALUES ('8', '1', '8');
INSERT INTO `t_role_resources` VALUES ('9', '1', '9');
INSERT INTO `t_role_resources` VALUES ('10', '1', '10');
INSERT INTO `t_role_resources` VALUES ('11', '1', '11');
INSERT INTO `t_role_resources` VALUES ('12', '1', '12');
INSERT INTO `t_role_resources` VALUES ('13', '1', '13');
INSERT INTO `t_role_resources` VALUES ('14', '1', '14');
INSERT INTO `t_role_resources` VALUES ('15', '1', '15');
INSERT INTO `t_role_resources` VALUES ('16', '1', '16');
INSERT INTO `t_role_resources` VALUES ('17', '1', '17');
INSERT INTO `t_role_resources` VALUES ('18', '1', '18');
INSERT INTO `t_role_resources` VALUES ('19', '1', '19');
INSERT INTO `t_role_resources` VALUES ('20', '1', '20');
INSERT INTO `t_role_resources` VALUES ('21', '1', '21');
INSERT INTO `t_role_resources` VALUES ('22', '1', '22');
INSERT INTO `t_role_resources` VALUES ('23', '1', '23');
INSERT INTO `t_role_resources` VALUES ('24', '1', '24');
INSERT INTO `t_role_resources` VALUES ('25', '1', '25');
INSERT INTO `t_role_resources` VALUES ('26', '1', '26');
INSERT INTO `t_role_resources` VALUES ('27', '1', '27');
INSERT INTO `t_role_resources` VALUES ('28', '1', '28');
INSERT INTO `t_role_resources` VALUES ('29', '1', '29');
INSERT INTO `t_role_resources` VALUES ('30', '1', '30');
INSERT INTO `t_role_resources` VALUES ('31', '1', '31');
INSERT INTO `t_role_resources` VALUES ('32', '1', '32');
INSERT INTO `t_role_resources` VALUES ('33', '1', '33');
INSERT INTO `t_role_resources` VALUES ('34', '1', '34');
INSERT INTO `t_role_resources` VALUES ('35', '1', '35');
INSERT INTO `t_role_resources` VALUES ('36', '1', '36');
INSERT INTO `t_role_resources` VALUES ('37', '1', '37');
INSERT INTO `t_role_resources` VALUES ('38', '2', '1');
INSERT INTO `t_role_resources` VALUES ('39', '2', '2');
INSERT INTO `t_role_resources` VALUES ('40', '2', '4');
INSERT INTO `t_role_resources` VALUES ('41', '2', '1');
INSERT INTO `t_role_resources` VALUES ('42', '2', '2');
INSERT INTO `t_role_resources` VALUES ('43', '2', '4');
