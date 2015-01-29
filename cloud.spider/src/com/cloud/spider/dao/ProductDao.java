package com.cloud.spider.dao;

import org.apache.log4j.Logger;

import cn.egame.common.data.BaseDao;
import cn.egame.common.exception.ExceptionCommonBase;

import com.cloud.spider.entity.constants.EnumType.ProductType;

public class ProductDao extends BaseDao {
	
	private static Logger logger = Logger.getLogger(ProductDao.class);

//	private static final String STR_T_PRODUCT_FIELD = " id, product_type, product_name, product_src_page_id "
//        			+ " , movie_alias_name, leading_role, screen_writer, directors, release_time "
//        			+ " , movie_length, region, language, type, imdb_id "
//        			+ " , imdb_rate, douban_id, douban_rate, movie_status, operator_id "
//        			+ " , insert_time, update_time, introduction, operator_name ";
	
	
	
	public ProductDao() throws ExceptionCommonBase {
		super("cloud");
	}
	
	public Long getMaxIdByType(ProductType productType) throws ExceptionCommonBase{
		String sql = " select max(id) from t_product where product_type=1 and product_type = "+productType.value();
		logger.debug("operate t_game SQL: " + sql);
        return this.getLong(sql);
	}

}
