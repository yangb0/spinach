# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.28-log
# Server OS:                    Win64
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2015-04-17 12:06:34
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for demo
CREATE DATABASE IF NOT EXISTS `demo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `demo`;


# Dumping structure for table demo.t_account
CREATE TABLE IF NOT EXISTS `t_account` (
  `id` mediumint(10) unsigned NOT NULL AUTO_INCREMENT,
  `linked_id` mediumint(10) DEFAULT '0',
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL,
  `user_type` mediumint(3) NOT NULL DEFAULT '0' COMMENT '会员类型 0:普通会员 1:药店会员 2:药师 3:管理员',
  `role_ids` varchar(100) DEFAULT '0' COMMENT '角色关联',
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `usable` varchar(10) NOT NULL DEFAULT 'true' COMMENT '会员是否可用(true:可用 false:不可用)',
  PRIMARY KEY (`id`),
  KEY `account_index` (`linked_id`,`user_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

INSERT INTO `t_account` (`id`, `linked_id`, `username`, `password`, `mobile`, `email`, `user_type`, `role_ids`, `login_time`, `usable`) VALUES (1, 1, 'demo', 'e10adc3949ba59abbe56e057f20f883e', '', 'demo@demo.com', 0, '0', '2014-10-29 14:54:19', 'true');
INSERT INTO `t_account` (`id`, `linked_id`, `username`, `password`, `mobile`, `email`, `user_type`, `role_ids`, `login_time`, `usable`) VALUES (2, 3, 'demovip', '618c397ee8bfdeb0938bce5813a4a96e', '', 'demovip@demovip.com', 0, '0', '2014-09-18 11:47:54', 'true');
INSERT INTO `t_account` (`id`, `linked_id`, `username`, `password`, `mobile`, `email`, `user_type`, `role_ids`, `login_time`, `usable`) VALUES (3, 4, 'sklef', '6d622500523ef0844eafaf2709bd93d5', NULL, 'luoyifan@qq.com', 0, '0', '2014-09-18 11:47:54', 'true');
INSERT INTO `t_account` (`id`, `linked_id`, `username`, `password`, `mobile`, `email`, `user_type`, `role_ids`, `login_time`, `usable`) VALUES (4, 5, 'sdszcn', '7f043598bd4cc7aca8d20576a609d0b0', NULL, '4528@qq.com', 0, '0', '2014-09-18 11:47:54', 'true');
INSERT INTO `t_account` (`id`, `linked_id`, `username`, `password`, `mobile`, `email`, `user_type`, `role_ids`, `login_time`, `usable`) VALUES (5, 12, 'uyhj', 'e10adc3949ba59abbe56e057f20f883e', NULL, '47082@qq.com', 0, '0', '2014-09-18 11:47:54', 'true');

