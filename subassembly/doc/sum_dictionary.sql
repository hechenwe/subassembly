/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-01-05 17:18:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sum_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sum_dictionary`;
CREATE TABLE `sum_dictionary` (
  `dictionary_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字段编号',
  `group_id` int(11) DEFAULT NULL COMMENT '组编号',
  `dictionary_code` varchar(32) DEFAULT NULL COMMENT '字典代码',
  `dictionary_name` varchar(32) DEFAULT NULL COMMENT '字典名称',
  `dictionary_state` int(11) DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`dictionary_id`),
  UNIQUE KEY `code` (`dictionary_code`),
  UNIQUE KEY `name` (`dictionary_name`),
  KEY `fk_dictionary_group` (`group_id`),
  CONSTRAINT `fk_dictionary_group` FOREIGN KEY (`group_id`) REFERENCES `sum_group` (`group_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组件模块_数据字典';
