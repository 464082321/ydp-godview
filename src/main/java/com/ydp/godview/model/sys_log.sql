CREATE TABLE `sys_log` (
  `id` varchar(32) NOT NULL,
  `req_method` varchar(64) NOT NULL DEFAULT '' COMMENT '被请求的方法',
  `req_param` varchar(64) NOT NULL DEFAULT '' COMMENT '请求的参数',
  `req_url` varchar(512) NOT NULL DEFAULT '' COMMENT '请求的url',
  `resp_data` LONGBLOB COMMENT '返回的数据',
  `create_time` datetime COMMENT '创建的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态日志记录';