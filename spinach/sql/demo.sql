/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-05-22 22:28:17
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
  `user_type` int(2) NOT NULL DEFAULT '0' COMMENT '用户类型 0:普通会员 1:管理员',
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `disabled` int(2) NOT NULL DEFAULT '0' COMMENT '是否可用(0:可用 1:不可用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('1', 'demo', 'demo', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', 'demo@demo.com', '0', '2015-05-21 22:28:49', '0');
INSERT INTO `t_account` VALUES ('2', 'user', 'user', 'e10adc3949ba59abbe56e057f20f883e', '13000000000', ' ', '0', '2015-05-21 22:29:26', '0');

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
-- Table structure for t_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_resources`;
CREATE TABLE `t_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '父节点名称',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `type` int(2) DEFAULT NULL COMMENT '类型:0:菜单,1:功能',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `url` varchar(255) DEFAULT NULL,
  `permission` varchar(50) DEFAULT NULL COMMENT '菜单编码',
  `icon` varchar(255) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of t_resources
-- ----------------------------
INSERT INTO `t_resources` VALUES ('1', '0', '系统管理', '0', '1', '', '', 'icon-standard-cog', '', '');
INSERT INTO `t_resources` VALUES ('2', '1', '角色管理', '0', '3', '', '', 'icon-hamburg-my-account', 'closed', '');
INSERT INTO `t_resources` VALUES ('3', '1', '用户管理', '0', '2', '', '', 'icon-hamburg-user', 'closed', '');
INSERT INTO `t_resources` VALUES ('4', '2', '添加', '1', null, '', 'sys:role:add', '', '', '角色添加');
INSERT INTO `t_resources` VALUES ('5', '2', '删除', '1', null, '', 'sys:role:delete', '', '', '角色删除');
INSERT INTO `t_resources` VALUES ('6', '2', '修改', '1', null, '', 'sys:role:update', '', '', '角色修改');
INSERT INTO `t_resources` VALUES ('7', '3', '添加', '1', null, '', 'sys:user:add', '', '', '用户添加');
INSERT INTO `t_resources` VALUES ('8', '3', '删除', '1', null, '', 'sys:user:delete', '', '', '用户删除');
INSERT INTO `t_resources` VALUES ('9', '1', '权限管理', '0', '5', '', '', 'icon-hamburg-login', 'closed', '');
INSERT INTO `t_resources` VALUES ('10', '11', '数据源监控', '0', '6', '/monitoring', '', 'icon-hamburg-database', '', '');
INSERT INTO `t_resources` VALUES ('11', '0', '系统监控', '0', '5', '', '', 'icon-hamburg-graphic', '', '');
INSERT INTO `t_resources` VALUES ('12', '3', '修改', '1', null, '', 'sys:user:update', '', '', '用户修改');
INSERT INTO `t_resources` VALUES ('13', '24', '添加', '1', null, '', 'sys:perm:add', '', '', '菜单添加');
INSERT INTO `t_resources` VALUES ('14', '24', '修改', '1', null, '', 'sys:perm:update', '', '', '菜单修改');
INSERT INTO `t_resources` VALUES ('15', '24', '删除', '1', null, '', 'sys:perm:delete', '', '', '菜单删除');
INSERT INTO `t_resources` VALUES ('16', '2', '查看', '1', null, '', 'sys:role:view', '', '', '角色查看');
INSERT INTO `t_resources` VALUES ('17', '3', '查看', '1', null, '', 'sys:user:view', '', null, '用户查看');
INSERT INTO `t_resources` VALUES ('18', '3', '查看权限', '1', null, '', 'sys:perm:view', '', null, '权限查看');
INSERT INTO `t_resources` VALUES ('19', '3', '查看用户角色', '1', null, '', 'sys:user:roleView', '', null, '查看用户角色');
INSERT INTO `t_resources` VALUES ('20', '2', '保存授权', '1', null, '', 'sys:role:permUpd', '', null, '保存修改的角色权限');
INSERT INTO `t_resources` VALUES ('21', '3', '修改用户角色', '1', null, '', 'sys:user:roleUpd', '', null, '修改用户拥有的角色');
INSERT INTO `t_resources` VALUES ('22', '2', '查看角色权限', '1', null, '', 'sys:role:permView', '', null, '查看角色拥有的权限');
INSERT INTO `t_resources` VALUES ('23', '11', '定时任务管理', '0', '9', '', '', 'icon-hamburg-full-time', null, '定时任务管理，支持集群');
INSERT INTO `t_resources` VALUES ('24', '1', '菜单管理', '0', '4', '', '', 'icon-hamburg-old-versions', null, '');
INSERT INTO `t_resources` VALUES ('25', '1', '字典管理', '0', '6', '', null, 'icon-hamburg-address', null, '数据字典管理');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `role_code` varchar(20) NOT NULL,
  `description` text,
  `sort` int(6) DEFAULT NULL,
  `disabled` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', 'admin', 'admin', '2', null);
INSERT INTO `t_role` VALUES ('5', 'guest', 'guest', 'guest', '3', null);
INSERT INTO `t_role` VALUES ('13', 'superadmin', 'superadmin', '超级管理员', '1', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='资源-角色关联表';

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
