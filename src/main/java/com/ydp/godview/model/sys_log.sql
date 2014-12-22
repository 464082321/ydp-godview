
CREATE TABLE `tbl_ref_log` (
  `id` varchar(32) NOT NULL,
  `interface_Name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '请求的接口名称',
  `req_method` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '请求的方法',
  `req_params` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '请求的参数',
  `resp_data` LONGBLOB COMMENT '返回的数据',
  `access_period` INTEGER COMMENT '请求的时长',
  `service_id` varchar(32) COMMENT '调用方ID',
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方动态日志记录';


CREATE TABLE `tbl_service_log` (
  `id` varchar(32) NOT NULL,
	`interface_Name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '请求的接口名称',
  `req_method` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '请求的方法',
  `req_params` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '请求的参数',
  `req_ip` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '请求的ip',
  `resp_data` LONGBLOB COMMENT '返回的数据',
  `access_period` INTEGER COMMENT '请求的时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调用方动态日志记录';