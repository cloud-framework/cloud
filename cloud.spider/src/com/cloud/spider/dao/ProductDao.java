package com.cloud.spider.dao;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import cn.egame.common.data.BaseDao;
import cn.egame.common.data.SqlUtils;
import cn.egame.common.exception.ExceptionCommonBase;

import com.cloud.spider.entity.constants.EnumType.ProductType;
import com.cloud.spider.entity.po.ProductImageInfo;
import com.cloud.spider.entity.po.ProductInfo;

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

	public void insertProductImageInfo(Connection conn, ProductImageInfo productImageInfo) throws ExceptionCommonBase {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (productImageInfo != null) {
        	String sql = " insert into t_product_image(efs_id, product_id, operator_id, insert_time "
        			+ " , update_time, file_type, sort)"
        			+ " values(%1$d, %2$d, %3$d"
        			+ " , str_to_date(%4$s,'%%Y-%%m-%%d %%H:%%i:%%s') "
        			+ " , str_to_date(%5$s,'%%Y-%%m-%%d %%H:%%i:%%s') "
        			+ " , %6$d, %7$d) ";
            sql = String.format(sql, productImageInfo.getEfsId(), productImageInfo.getProductId()
            		, productImageInfo.getOperatorId(), SqlUtils.QuataStr(sf.format(new Date()))
            	    , SqlUtils.QuataStr(sf.format(new Date())), productImageInfo.getFileType().value()
            	    , productImageInfo.getSort());
            logger.debug("operate t_game SQL: " + sql);
            this.executeUpdate(conn, sql);
        }
    }
	
	public int insertProductInfo(Connection conn, ProductInfo productInfo) throws ExceptionCommonBase {
        int identity = 0;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (productInfo != null) {
        	String sql = " insert into t_product(product_type, product_name "
        			+ " , product_status, product_src_page_id, product_src_id "
        			+ " , product_author, release_time, product_relative_id, operator_id "
        			+ " , insert_time, operator_name)"
        			+ " values(%1$d, %2$s, %3$d, %4$d, %5$d, %6$s"
        			+ " , str_to_date(%7$s,'%%Y-%%m-%%d %%H:%%i:%%s'), %8$d"
        			+ " , %9$d,str_to_date(%10$s,'%%Y-%%m-%%d %%H:%%i:%%s'), %11$s)";
            sql = String.format(sql, productInfo.getProductType(), SqlUtils.QuataStr(productInfo.getProductName())
            		, productInfo.getProductStatus(), productInfo.getProductSrcPageId(), productInfo.getProductSrcId()
            		, SqlUtils.QuataStr(productInfo.getProductAuthor()), SqlUtils.QuataStr(sf.format(new Date()))
            		, productInfo.getProductRelativeId(), productInfo.getOperatorId()
                    , SqlUtils.QuataStr(sf.format(new Date()))
                    , SqlUtils.QuataStr(productInfo.getOperatorName()));
            logger.debug("operate t_game SQL: " + sql);
            identity = this.getIdentityId(conn, sql);
        }
        return identity;
    }
	
}
