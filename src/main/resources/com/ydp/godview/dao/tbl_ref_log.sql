/*
Navicat MySQL Data Transfer

Source Server         : 10.0.30.201
Source Server Version : 50611
Source Host           : 10.0.30.201:3306
Source Database       : dms_db

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2014-12-26 14:09:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_ref_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ref_log`;
CREATE TABLE `tbl_ref_log` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `interface_Name` varchar(256) NOT NULL DEFAULT '' COMMENT '请求的接口名称',
  `req_method` varchar(128) NOT NULL DEFAULT '' COMMENT '请求的方法',
  `req_params` varchar(2000) DEFAULT '' COMMENT '请求的参数',
  `resp_data` varchar(4000) DEFAULT NULL COMMENT '返回的数据',
  `access_period` int(11) DEFAULT NULL COMMENT '请求的时长',
  `service_id` varchar(32) DEFAULT NULL COMMENT '调用方ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='第三方动态日志记录';
