/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-01-05 17:17:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sum_group
-- ----------------------------
DROP TABLE IF EXISTS `sum_group`;
CREATE TABLE `sum_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据字典分组编号',
  `group_code` varchar(32) DEFAULT NULL COMMENT '组代码',
  `group_name` varchar(32) DEFAULT NULL COMMENT '组名称',
  `group_state` int(11) DEFAULT '1' COMMENT '组状态',
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `code` (`group_code`),
  UNIQUE KEY `name` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='组件模块_数据字典分组';
