-- --------------------------------------------------------
-- 主机:                           10.0.12.246
-- 服务器版本:                        5.1.73 - Source distribution
-- 服务器操作系统:                      redhat-linux-gnu
-- HeidiSQL 版本:                  9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 demo 的数据库结构
CREATE DATABASE IF NOT EXISTS `demo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `demo`;


-- 导出  表 demo.qrtz_blob_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  demo.qrtz_blob_triggers 的数据：~0 rows (大约)
DELETE FROM `qrtz_blob_triggers`;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;


-- 导出  表 demo.qrtz_calendars 结构
CREATE TABLE IF NOT EXISTS `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  demo.qrtz_calendars 的数据：~0 rows (大约)
DELETE FROM `qrtz_calendars`;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;


-- 导出  表 demo.qrtz_cron_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  demo.qrtz_cron_triggers 的数据：~1 rows (大约)
DELETE FROM `qrtz_cron_triggers`;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_cron_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES
	('scheduler', '12', '1', '0 0/5 * * * ?', 'Asia/Shanghai');
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;


-- 导出  表 demo.qrtz_fired_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_fired_triggers` (
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

-- 正在导出表  demo.qrtz_fired_triggers 的数据：~0 rows (大约)
DELETE FROM `qrtz_fired_triggers`;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;


-- 导出  表 demo.qrtz_job_details 结构
CREATE TABLE IF NOT EXISTS `qrtz_job_details` (
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

-- 正在导出表  demo.qrtz_job_details 的数据：~1 rows (大约)
DELETE FROM `qrtz_job_details`;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
INSERT INTO `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES
	('scheduler', '12', '1', NULL, 'com.yang.spinach.frame.utils.ScheduleJobTask', '0', '1', '0', '0', _binary 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61703FE8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D6170133F3F760A3F00025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC13F603F000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000B7363686564756C654A6F627372002F636F6D2E79616E672E7370696E6163682E7363686564756C654A6F622E656E746974792E5363686564756C654A6F6200000000000000010200064C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000B6465736372697074696F6E71007E00094C000567726F757071007E00094C00046E616D6571007E00094C000673746174757371007E0009787074002C636F6D2E79616E672E7370696E6163682E6672616D652E7574696C732E5363686564756C654A6F625461736B74000D3020302F35202A202A202A203F740000740001317400023132740001317800);
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;


-- 导出  表 demo.qrtz_locks 结构
CREATE TABLE IF NOT EXISTS `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  demo.qrtz_locks 的数据：~2 rows (大约)
DELETE FROM `qrtz_locks`;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` (`SCHED_NAME`, `LOCK_NAME`) VALUES
	('scheduler', 'STATE_ACCESS'),
	('scheduler', 'TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;


-- 导出  表 demo.qrtz_paused_trigger_grps 结构
CREATE TABLE IF NOT EXISTS `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  demo.qrtz_paused_trigger_grps 的数据：~0 rows (大约)
DELETE FROM `qrtz_paused_trigger_grps`;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;


-- 导出  表 demo.qrtz_scheduler_state 结构
CREATE TABLE IF NOT EXISTS `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  demo.qrtz_scheduler_state 的数据：~1 rows (大约)
DELETE FROM `qrtz_scheduler_state`;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
INSERT INTO `qrtz_scheduler_state` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES
	('scheduler', 'asus1447078130503', 1447083037115, 15000);
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;


-- 导出  表 demo.qrtz_simple_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  demo.qrtz_simple_triggers 的数据：~0 rows (大约)
DELETE FROM `qrtz_simple_triggers`;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;


-- 导出  表 demo.qrtz_simprop_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_simprop_triggers` (
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

-- 正在导出表  demo.qrtz_simprop_triggers 的数据：~0 rows (大约)
DELETE FROM `qrtz_simprop_triggers`;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;


-- 导出  表 demo.qrtz_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_triggers` (
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

-- 正在导出表  demo.qrtz_triggers 的数据：~1 rows (大约)
DELETE FROM `qrtz_triggers`;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES
	('scheduler', '12', '1', '12', '1', NULL, 1436372400000, 1436372181771, 5, 'PAUSED', 'CRON', 1436371523000, 0, NULL, 0, _binary '');
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;


-- 导出  表 demo.t_account 结构
CREATE TABLE IF NOT EXISTS `t_account` (
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  demo.t_account 的数据：~15 rows (大约)
DELETE FROM `t_account`;
/*!40000 ALTER TABLE `t_account` DISABLE KEYS */;
INSERT INTO `t_account` (`id`, `username`, `nick_name`, `password`, `mobile`, `email`, `user_type`, `login_time`, `disabled`) VALUES
	(1, 'demo', '1231', 'e10adc3949ba59abbe56e057f20f883e', '13000000001', 'demo@demo.com', 2, '2016-01-11 16:02:30', 0),
	(2, 'user', 'user1', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', '10001@qq.com', 2, '2015-11-10 15:13:08', 1),
	(3, 'test', 'test', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', '111@qq.com', 0, '2015-07-16 16:03:26', 0),
	(5, 'test1', '1111', 'e10adc3949ba59abbe56e057f20f883e', '13000000001', '11@qq.com', 0, '2015-11-09 14:00:30', 0),
	(6, 'abcd', 'qwera', 'e10adc3949ba59abbe56e057f20f883e', '13000000001', 'qq@qq.com', 2, '2015-11-10 15:17:10', 1),
	(8, '11111123', '111111', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', '1@1.com', 0, '2015-08-31 15:48:22', 0),
	(9, '12', '121', '123456', '13000000000', '1@2.cnn', 2, '2015-11-09 13:39:25', 0),
	(10, '123', '123', '123456', '13000000000', '1@2.3', 2, '2015-11-07 14:02:59', 1),
	(11, '1234', '12', '123456', '13000000033', '1@2.3', 2, '2015-11-07 14:03:03', 1),
	(12, '123456', '123', '123456', '13000000000', '1@2.3', 2, '2015-11-07 14:03:06', 1),
	(13, '12311', '12', '123456', '13000000033', '1@2.3', 2, '2015-11-07 14:03:12', 1),
	(14, '11', '11', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', '1@1.1', 2, NULL, 0),
	(15, '12341', '21', 'e10adc3949ba59abbe56e057f20f883e', '13000000011', '1@1.1', 2, NULL, 0),
	(16, '123415', '21', 'e10adc3949ba59abbe56e057f20f883e', '13000000011', '1@1.1', 2, NULL, 0),
	(17, '11111111', 'qq', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', '11@qq.c', 2, NULL, 0);
/*!40000 ALTER TABLE `t_account` ENABLE KEYS */;


-- 导出  表 demo.t_account_role 结构
CREATE TABLE IF NOT EXISTS `t_account_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_ROL_REFERENCE_ROLE` (`role_id`) USING BTREE,
  KEY `FK_USER_ROL_REFERENCE_USERS` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表';

-- 正在导出表  demo.t_account_role 的数据：~6 rows (大约)
DELETE FROM `t_account_role`;
/*!40000 ALTER TABLE `t_account_role` DISABLE KEYS */;
INSERT INTO `t_account_role` (`id`, `user_id`, `role_id`) VALUES
	(40, 9, 5),
	(41, 9, 6),
	(45, 2, 1),
	(46, 6, 6),
	(48, 14, 4),
	(49, 1, 1);
/*!40000 ALTER TABLE `t_account_role` ENABLE KEYS */;


-- 导出  表 demo.t_dict 结构
CREATE TABLE IF NOT EXISTS `t_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(200) NOT NULL DEFAULT '' COMMENT '显示内容',
  `value` int(10) NOT NULL DEFAULT '0' COMMENT '值',
  `target_column` varchar(30) DEFAULT '' COMMENT '对应字段',
  `descrption` varchar(100) DEFAULT '' COMMENT '描述',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `disabled` int(2) NOT NULL DEFAULT '0' COMMENT '是否启用:0启用,1不启用',
  PRIMARY KEY (`id`),
  KEY `target_column` (`target_column`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- 正在导出表  demo.t_dict 的数据：~7 rows (大约)
DELETE FROM `t_dict`;
/*!40000 ALTER TABLE `t_dict` DISABLE KEYS */;
INSERT INTO `t_dict` (`id`, `label`, `value`, `target_column`, `descrption`, `sort`, `remark`, `disabled`) VALUES
	(1, '普通用户', 0, 'user_type', '用户类型1', 1, 's', 0),
	(2, '会员用户', 1, 'user_type', '用户类型', 2, '11', 0),
	(3, '管理员用户', 2, 'user_type', '用户类型', 3, '', 0),
	(4, '启用', 0, 'disabled', '是否启用', 1, '', 0),
	(5, '禁用', 1, 'disabled', '是否启用', 2, '', 0),
	(6, '菜单', 0, 'resource_type', '菜单', 0, '', 0),
	(7, '按钮', 1, 'resource_type', '按钮', 0, '', 0);
/*!40000 ALTER TABLE `t_dict` ENABLE KEYS */;


-- 导出  表 demo.t_resources 结构
CREATE TABLE IF NOT EXISTS `t_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '父节点id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `type` int(2) DEFAULT '0' COMMENT '类型:0:菜单,1:按钮',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `url` varchar(200) DEFAULT ' ',
  `permission` varchar(50) NOT NULL DEFAULT ' ' COMMENT '菜单编码',
  `icon` varchar(100) DEFAULT ' ',
  `disabled` tinyint(10) NOT NULL DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- 正在导出表  demo.t_resources 的数据：~29 rows (大约)
DELETE FROM `t_resources`;
/*!40000 ALTER TABLE `t_resources` DISABLE KEYS */;
INSERT INTO `t_resources` (`id`, `pid`, `name`, `type`, `sort`, `url`, `permission`, `icon`, `disabled`, `description`) VALUES
	(1, 0, '系统管理', 0, 1, '', 'sys:manager', '&#xe62e;', 0, 'sdf'),
	(2, 1, '角色列表', 0, 3, '/role/list', 'sys:role:list', ' icon-user', 0, ''),
	(3, 1, '管理员列表', 0, 2, '/user/list', 'sys:user:list', 'icon-hamburg-user', 0, '1,'),
	(4, 2, '添加', 1, NULL, '', 'sys:role:add', '', 0, '角色添加1'),
	(5, 2, '删除', 1, NULL, '', 'sys:role:delete', '', 0, '角色删除'),
	(6, 2, '修改', 1, NULL, '', 'sys:role:update', '', 0, '角色修改'),
	(7, 3, '添加', 1, 1, '', 'sys:user:add', '', 0, '11,1'),
	(8, 3, '删除', 1, 1, '', 'sys:user:delete', '', 0, '用户删除,用户删除,用户删除,用户删除'),
	(10, 11, '数据源监控', 0, 6, '/monitoring', 'monitoring:data', 'icon-hamburg-database', 0, ''),
	(11, 0, '系统监控', 0, 6, '', 'monitoring:system', '&#xe61a;', 0, ''),
	(12, 3, '修改', 1, NULL, '', 'sys:user:update', '', 0, '用户修改'),
	(13, 24, '添加', 1, NULL, '', 'sys:perm:add', '', 0, '菜单添加'),
	(14, 24, '修改', 1, NULL, '', 'sys:perm:update', '', 0, '菜单修改'),
	(15, 24, '删除', 1, NULL, '', 'sys:perm:delete', '', 0, '菜单删除'),
	(16, 2, '查看', 1, NULL, '', 'sys:role:view', '', 0, '角色查看'),
	(17, 3, '查看', 1, NULL, '', 'sys:user:view', '', 0, '用户查看'),
	(19, 3, '查看用户角色', 1, NULL, '', 'sys:user:roleView', '', 0, '查看用户角色'),
	(22, 2, '查看角色权限', 1, NULL, '', 'sys:role:permView', '', 0, '查看角色拥有的权限'),
	(23, 11, '定时任务管理', 0, 9, '/scheduleJob/list', 'sys:scheduleJob:list', 'icon-hamburg-full-time', 0, '定时任务管理，支持集群'),
	(24, 1, '菜单管理', 0, 4, '/resources/list', 'sys:perm:manager', 'icon-hamburg-old-versions', 0, ''),
	(25, 1, '字典管理', 0, 6, '/dict/list', 'sys:dictionaries', 'icon-hamburg-address', 0, '数据字典管理'),
	(29, 25, '添加数据字典', 1, 2, '', 'sys:dict:add', '', 0, '添加数据字典'),
	(30, 25, '数据字典修改', 1, 3, '', 'sys:dict:update', '', 0, '数据字典修改'),
	(31, 23, '添加定时任务', 1, 1, ' ', 'sys:scheduleJob:add', ' ', 0, '添加定时任务'),
	(33, 0, '资讯管理', 0, 2, '', 'sys:article', '&#xe616;', 0, ''),
	(34, 0, '图片管理', 0, 3, '', 'sys:picture', '&#xe613;', 0, ''),
	(35, 0, '产品管理', 0, 4, '', 'sys:product', '&#xe620;', 0, ''),
	(36, 0, '评论管理', 0, 5, '', 'sys:feedback', '&#xe622;', 0, ''),
	(37, 0, 'aa', 0, 1, NULL, '11', NULL, 0, 'a,a');
/*!40000 ALTER TABLE `t_resources` ENABLE KEYS */;


-- 导出  表 demo.t_role 结构
CREATE TABLE IF NOT EXISTS `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `role_code` varchar(20) NOT NULL,
  `description` text,
  `sort` int(6) DEFAULT '99',
  `disabled` int(2) NOT NULL DEFAULT '0' COMMENT '是否启用:0启用,1弃用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 正在导出表  demo.t_role 的数据：~6 rows (大约)
DELETE FROM `t_role`;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` (`id`, `name`, `role_code`, `description`, `sort`, `disabled`) VALUES
	(1, '超级管理员', 'admin', 'admin', 2, 0),
	(2, '来宾账号', 'guest', 'guest', 3, 0),
	(3, 'ss', 'superadmin', '超级管理员', 1, 0),
	(4, 'test', 'test', '11', 4, 0),
	(5, 'sdfs', 'sss', 'sssss', 5, 0),
	(6, '1', '1', '1', 99, 0);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;


-- 导出  表 demo.t_role_resources 结构
CREATE TABLE IF NOT EXISTS `t_role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resources_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ROLE_PER_REFERENCE_PERMISSI` (`resources_id`) USING BTREE,
  KEY `FK_ROLE_PER_REFERENCE_ROLE` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='资源-角色关联表';

-- 正在导出表  demo.t_role_resources 的数据：~28 rows (大约)
DELETE FROM `t_role_resources`;
/*!40000 ALTER TABLE `t_role_resources` DISABLE KEYS */;
INSERT INTO `t_role_resources` (`id`, `role_id`, `resources_id`) VALUES
	(1, 1, 1),
	(2, 1, 3),
	(3, 1, 12),
	(4, 1, 17),
	(5, 1, 19),
	(6, 1, 7),
	(7, 1, 8),
	(8, 1, 2),
	(9, 1, 4),
	(10, 1, 5),
	(11, 1, 6),
	(12, 1, 16),
	(13, 1, 22),
	(14, 1, 24),
	(15, 1, 13),
	(16, 1, 14),
	(17, 1, 15),
	(18, 1, 25),
	(19, 1, 29),
	(20, 1, 30),
	(21, 1, 33),
	(22, 1, 34),
	(23, 1, 35),
	(24, 1, 36),
	(25, 1, 11),
	(26, 1, 10),
	(27, 1, 23),
	(28, 1, 31);
/*!40000 ALTER TABLE `t_role_resources` ENABLE KEYS */;


-- 导出  表 demo.t_schedule_job 结构
CREATE TABLE IF NOT EXISTS `t_schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `bean_class` varchar(200) DEFAULT NULL COMMENT '任务执行时调用哪个类(完整类路径)',
  `spring_id` varchar(200) DEFAULT NULL COMMENT '任务执行时调用哪个类(spring中注册的别名)',
  `method_name` varchar(200) DEFAULT NULL COMMENT '任务执行时调用的具体方法',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `sched_name_job_name_job_group` (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  demo.t_schedule_job 的数据：~0 rows (大约)
DELETE FROM `t_schedule_job`;
/*!40000 ALTER TABLE `t_schedule_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_schedule_job` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
