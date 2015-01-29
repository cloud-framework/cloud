##t_movie
DROP TABLE IF EXISTS `t_movie`;

CREATE TABLE `t_movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品编号',
  `movie_subtype` int(11) DEFAULT '0' COMMENT '电影类型（1：电影；2：电视剧）',
  `movie_name` varchar(128) DEFAULT NULL COMMENT '电影名称',
  `movie_original_name` varchar(128) DEFAULT NULL COMMENT '电影原名',
  `movie_alias_name` varchar(256) DEFAULT NULL COMMENT '电影别名',
  `leading_role` varchar(256) DEFAULT NULL COMMENT '主演',
  `screen_writer` varchar(256) DEFAULT NULL COMMENT '编剧',
  `directors` varchar(128) DEFAULT NULL COMMENT '导演',
  `release_time` varchar(128) DEFAULT NULL COMMENT '上映时间',
  `movie_length` varchar(128) DEFAULT NULL COMMENT '片长',
  `region` varchar(64) DEFAULT NULL COMMENT '地区',
  `language` varchar(64) DEFAULT NULL COMMENT '语言',
  `type` varchar(65) DEFAULT NULL COMMENT '类型',
  `imdb_id` varchar(64) DEFAULT NULL COMMENT 'imdb对应的id',
  `imdb_rate` varchar(32) DEFAULT NULL COMMENT 'imdb对应的评分',
  `douban_id` varchar(64) DEFAULT NULL COMMENT '豆瓣电影对应的id',
  `douban_rate` varchar(32) DEFAULT NULL COMMENT '豆瓣电影对应的评分',
  `movie_status` int(8) DEFAULT '0' COMMENT '电影状态(1:已经上映、2:未上映)',
  `operator_id` int(16) DEFAULT '0' COMMENT '操作员',
  `insert_time` datetime DEFAULT '1970-01-01 00:00:00' COMMENT '入库时间',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `introduction` varchar(4096) DEFAULT NULL COMMENT '电影简介',
  `operator_name` varchar(128) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


##t_parameter_app
DROP TABLE IF EXISTS `t_parameter_app`;
CREATE TABLE `t_parameter_app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父节点编号',
  `type` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '分类代码',
  `name` varchar(64) DEFAULT NULL COMMENT '分类名称',
  `param` varchar(128) DEFAULT NULL,
  `sort` int(10) unsigned NOT NULL DEFAULT '9999' COMMENT '分类权重',
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `depth` int(10) unsigned NOT NULL DEFAULT '1',
  `remark` varchar(512) NOT NULL COMMENT '备注信息',
  `pic_id` bigint(16) DEFAULT NULL COMMENT '图片编号',
  `begin_time` datetime DEFAULT '1970-01-01 08:00:00' COMMENT '开始时间',
  `end_time` datetime DEFAULT '4000-01-01 08:00:00' COMMENT '结束时间',
  `update_time` datetime DEFAULT '1970-01-01 08:00:00' COMMENT '更新时间',
  `operator_id` int(11) DEFAULT '0' COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


##t_file
CREATE TABLE `t_file` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) DEFAULT '0',
  `u_id` bigint(16) DEFAULT '0',
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` int(255) DEFAULT '0',
  `image_type` varchar(20) DEFAULT NULL,
  `file_size` int(11) DEFAULT NULL,
  `save_name` varchar(255) DEFAULT NULL,
  `is_save` tinyint(1) DEFAULT '0',
  `is_delete` tinyint(1) DEFAULT '0',
  `is_complete` tinyint(1) DEFAULT '0',
  `create_time` bigint(20) DEFAULT '0',
  `update_time` bigint(20) DEFAULT '0',
  `operator_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

##t_movie_image
CREATE TABLE `t_movie_image` (
  `efs_id` bigint(16) NOT NULL,
  `m_id` int(11) NOT NULL DEFAULT '0',
  `operator_id` int(16) DEFAULT '0',
  `insert_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `file_type` int(16) NOT NULL DEFAULT '0',
  `sort` int(16),
  PRIMARY KEY (`efs_id`,`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



##t_parameter_tag
DROP TABLE IF EXISTS `t_parameter_tag`;

CREATE TABLE `t_parameter_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(32) DEFAULT NULL,
  `tag_type` int(11) DEFAULT NULL COMMENT '1:标签 2:参数代码（对应表t_parameter_app字段type）',
  `enable` tinyint(1) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


##t_parameter_tag_link

DROP TABLE IF EXISTS `t_parameter_tag_link`;
CREATE TABLE `t_parameter_tag_link` (
  `business_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL DEFAULT '0',
  `business_sort_no` int(11) DEFAULT '0' COMMENT '根据tag_id查出的list按此字段排序',
  `tag_sort_no` int(11) DEFAULT '0' COMMENT '根据business_id查出的taglist按此字段排序',
  `enable` tinyint(1) DEFAULT '1',
  `start_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`business_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


##t_movie_source_url
##t_movie_source_web

CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品编号',
  `product_type` int(11) DEFAULT NULL COMMENT '产品类型(1:煎蛋无聊图)',
  `product_name` varchar(128) DEFAULT NULL COMMENT '产品名称',
  `product_status` int(11) DEFAULT NULL COMMENT '产品状态(1:有效)',
  `product_src_page_id` int(16) DEFAULT NULL COMMENT '产品所在原网页的页码',
  `product_src_id` int(16) DEFAULT NULL COMMENT '产品所在原网页的编码',
  `product_author` varchar(256) DEFAULT NULL COMMENT '产品作者名',
  `release_time` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '产品原网站上传时间',
  `product_relative_id` varchar(64) DEFAULT NULL COMMENT '产品原网页对应的id(豆瓣电影对应的id)',
  `operator_id` int(16) DEFAULT '0' COMMENT '操作员',
  `insert_time` datetime DEFAULT '1970-01-01 00:00:00' COMMENT '入库时间',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `operator_name` varchar(128) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `t_product_image` (
  `efs_id` bigint(16) NOT NULL,
  `product_id` int(11) NOT NULL DEFAULT '0',
  `operator_id` int(16) DEFAULT '0',
  `insert_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `file_type` int(16) NOT NULL DEFAULT '0',
  `sort` int(16) DEFAULT NULL,
  PRIMARY KEY (`efs_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

