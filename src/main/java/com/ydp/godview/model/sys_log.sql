CREATE TABLE `tbl_service_log` (
  `id` varchar(32) NOT NULL,
  `req_ip` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '请求的ip',
  `interface_Name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '请求的接口名称',
  `req_method` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '请求的方法',
  `req_params` VARCHAR(128) COMMENT '请求的参数',
  `resp_data` VARCHAR(1280) COMMENT '返回的数据',
  `err_message` VARCHAR(512) COMMENT '异常信息',
  `access_period` INTEGER COMMENT '请求的时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口调用的日志表';

CREATE TABLE `tbl_ref_log` (
  `id` varchar(32) NOT NULL,
  `interface_Name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '请求的接口名称',
  `req_method` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '请求的方法',
  `req_params` VARCHAR(128) COMMENT '请求的参数',
  `resp_data` VARCHAR(1280) COMMENT '返回的数据',
  `access_period` INTEGER COMMENT '请求的时长',
  `service_id` varchar(32) COMMENT '调用方ID',
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方接口调用的日志表';


CREATE TABLE `tbl_setting_log` (
	`methodName` VARCHAR(128) primary key NOT NULL DEFAULT '' COMMENT '需要记录调用情况的接口名称',
	`isOpen` VARCHAR(1) NOT NULL DEFAULT '0' COMMENT '是否打开记录功能'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口动态配置表';