package com.cloud.spider.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

import cn.egame.common.data.BaseDao;
import cn.egame.common.exception.ExceptionCommonBase;
import cn.egame.common.util.Utils;

import com.cloud.spider.entity.po.ParameterTagLinkInfo;

public class ParameterDao extends BaseDao {

	private static Logger logger = Logger.getLogger(ParameterDao.class);

	public ParameterDao() throws ExceptionCommonBase {
		super("cloud");
	}

	
	public void insertParameterTagLinkInfo(Connection conn, ParameterTagLinkInfo 
			parameterTagLinkInfo) throws ExceptionCommonBase {
        if (parameterTagLinkInfo != null) {
        	String sql = " insert into t_parameter_tag_link(business_id, tag_id, business_sort_no "
        			+ " , tag_sort_no, enable, start_time, end_time) "
        			+ " values(%1$d, %2$d, %3$d, %4$d, %5$d, %6$d, %7$d) ";
            sql = String.format(sql, parameterTagLinkInfo.getBusinessId(), parameterTagLinkInfo.getTagId()
            		, parameterTagLinkInfo.getBusinessSortNo(), parameterTagLinkInfo.getTagSortNo()
            		, parameterTagLinkInfo.getEnable(), parameterTagLinkInfo.getStartTime()
            		, parameterTagLinkInfo.getEndTime());
            logger.debug("operate t_game SQL: " + sql);
            this.executeUpdate(conn, sql);
        }
    }

	public void deleteParameterTagLinkInfoByTagId(Connection conn, int tagId) 
			throws ExceptionCommonBase {
		
		String sql = " delete from t_parameter_tag_link where tag_id = %1$d";
		sql = String.format(sql, tagId);
		logger.debug("operate t_game SQL: " + sql);
		this.executeUpdate(conn, sql);
    }
	
	
	public static void main(String[] args) {
		Utils.initLog4j();
		ParameterDao dao = null;
		Connection conn = null;
		try {
			dao = new ParameterDao();
			conn = dao.getConnection();
			ParameterTagLinkInfo parameterTagLinkInfo = new ParameterTagLinkInfo();
			parameterTagLinkInfo.setBusinessId(1L);
			parameterTagLinkInfo.setEnable(1);
			parameterTagLinkInfo.setTagId(1L);
			new ParameterDao().insertParameterTagLinkInfo(conn,
					parameterTagLinkInfo);
		} catch (ExceptionCommonBase e) {
			e.printStackTrace();
		} finally {
			try {
				dao.releaseConnection(conn);
			} catch (ExceptionCommonBase e) {
				e.printStackTrace();
			}
		}
	}
}
