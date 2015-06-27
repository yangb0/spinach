/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-06-28 00:19:40
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('1', 'demo', 'demo', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', 'demo@demo.com', '2', '2015-05-31 16:30:38', '0');
INSERT INTO `t_account` VALUES ('2', 'user', 'user1', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', '10001@qq.com', '0', '2015-06-04 21:47:49', '1');
INSERT INTO `t_account` VALUES ('3', 'test', 'test', 'MD5:e10adc3949ba59abbe56e057f20f883e', '13000000000', '111@qq.com', '0', '2015-05-31 21:14:47', '0');
INSERT INTO `t_account` VALUES ('5', 'test1', '1111', 'MD5:e10adc3949ba59abbe56e057f20f883e', '13000000000', '11@qq.com', '0', '2015-05-31 21:19:20', '0');
INSERT INTO `t_account` VALUES ('6', 'abcd', 'qwer', 'MD5:e10adc3949ba59abbe56e057f20f883e', '13000000001', 'qq@qq.com', '0', '2015-06-04 21:48:45', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表';

-- ----------------------------
-- Records of t_account_role
-- ----------------------------
INSERT INTO `t_account_role` VALUES ('1', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', '普通用户', '0', 'user_type', '用户类型', '0', ' ', '0');
INSERT INTO `t_dict` VALUES ('2', '会员用户', '1', 'user_type', '用户类型', '0', ' ', '0');
INSERT INTO `t_dict` VALUES ('3', '管理员用户', '2', 'user_type', '用户类型', '0', '', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='资源表';

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
INSERT INTO `t_resources` VALUES ('18', '3', '查看权限', '1', null, '', 'sys:perm:view', '', '0', '权限查看');
INSERT INTO `t_resources` VALUES ('19', '3', '查看用户角色', '1', null, '', 'sys:user:roleView', '', '0', '查看用户角色');
INSERT INTO `t_resources` VALUES ('22', '2', '查看角色权限', '1', null, '', 'sys:role:permView', '', '0', '查看角色拥有的权限');
INSERT INTO `t_resources` VALUES ('23', '11', '定时任务管理', '0', '9', '', 'monitoring:task', 'icon-hamburg-full-time', '0', '定时任务管理，支持集群');
INSERT INTO `t_resources` VALUES ('24', '1', '菜单管理', '0', '4', '/resources/list', 'sys:perm:manager', 'icon-hamburg-old-versions', '0', '');
INSERT INTO `t_resources` VALUES ('25', '1', '字典管理', '0', '6', '', 'sys:dictionaries', 'icon-hamburg-address', '0', '数据字典管理');
INSERT INTO `t_resources` VALUES ('28', '1', 'a', '0', null, '', '11', '', '1', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='资源-角色关联表';

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
