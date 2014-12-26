/*
Navicat MySQL Data Transfer

Source Server         : 10.0.30.201
Source Server Version : 50611
Source Host           : 10.0.30.201:3306
Source Database       : dms_db

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2014-12-26 14:09:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_service_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_service_log`;
CREATE TABLE `tbl_service_log` (
  `id` int(32) NOT NULL,
  `interface_Name` varchar(256) NOT NULL DEFAULT '' COMMENT '请求的接口名称',
  `req_method` varchar(128) NOT NULL DEFAULT '' COMMENT '请求的方法',
  `req_params` varchar(2000) DEFAULT '' COMMENT '请求的参数',
  `req_ip` varchar(64) NOT NULL DEFAULT '' COMMENT '请求的ip',
  `resp_data` varchar(4000) DEFAULT NULL COMMENT '返回的数据',
  `err_msg` varchar(1000) DEFAULT NULL,
  `access_period` int(11) DEFAULT NULL COMMENT '请求的时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调用方动态日志记录';
