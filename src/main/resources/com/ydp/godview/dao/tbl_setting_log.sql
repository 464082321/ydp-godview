/*
Navicat MySQL Data Transfer

Source Server         : 10.0.30.201
Source Server Version : 50611
Source Host           : 10.0.30.201:3306
Source Database       : dms_db

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2015-01-04 11:22:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_setting_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_setting_log`;
CREATE TABLE `tbl_setting_log` (
  `methodName` varchar(128) NOT NULL DEFAULT '' COMMENT '需要记录调用情况的接口名称',
  `isOpen` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否打开记录功能',
  PRIMARY KEY (`methodName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口动态配置表';
