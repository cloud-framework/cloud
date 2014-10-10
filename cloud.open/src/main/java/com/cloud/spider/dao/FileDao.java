package com.cloud.spider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import cn.egame.common.data.SqlUtils;

import com.cloud.spider.entity.po.FileInfo;

@Service
public class FileDao{
	private static Logger logger = Logger.getLogger(FileDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	public int insertFileInfo(Connection conn, FileInfo fileInfo) throws ExceptionCommonBase {
        int identity = 0;
        if (fileInfo != null) {
        	String sql = " insert into t_file(app_id, u_id "
        			+ " , file_name, file_type, image_type, file_size, save_name "
        			+ " , is_save, is_delete, is_complete, create_time) "
        			+ " values(%1$d, %2$d, %3$s, %4$d, %5$s, %6$d, %7$s"
        			+ " , %8$d, %9$d, %10$d, %11$d) ";
            sql = String.format(sql, fileInfo.getAppId(), fileInfo.getuId()
            		, SqlUtils.QuataStr(fileInfo.getFileName()), fileInfo.getFileType().value()
            		, SqlUtils.QuataStr(fileInfo.getImageType()), fileInfo.getFileSize()
            		, SqlUtils.QuataStr(fileInfo.getSaveName()), fileInfo.getIsSave()
            		, fileInfo.getIsDelete(), fileInfo.getIsComplete(), System.currentTimeMillis());
            logger.debug("operate t_game SQL: " + sql);
            identity = this.getIdentityId(conn, sql);
        }
        return identity;
    }
    */
	
	public int insertFileInfo(final FileInfo fileInfo) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		        if (fileInfo != null) {
		        	String sql = " insert into t_file(app_id, u_id "
		        			+ " , file_name, file_type, image_type, file_size, save_name "
		        			+ " , is_save, is_delete, is_complete, create_time) "
		        			+ " values(%1$d, %2$d, %3$s, %4$d, %5$s, %6$d, %7$s"
		        			+ " , %8$d, %9$d, %10$d, %11$d) ";
		            sql = String.format(sql, fileInfo.getAppId(), fileInfo.getuId()
		            		, SqlUtils.QuataStr(fileInfo.getFileName()), fileInfo.getFileType().value()
		            		, SqlUtils.QuataStr(fileInfo.getImageType()), fileInfo.getFileSize()
		            		, SqlUtils.QuataStr(fileInfo.getSaveName()), fileInfo.getIsSave()
		            		, fileInfo.getIsDelete(), fileInfo.getIsComplete(), System.currentTimeMillis());
		            logger.info("operate t_game SQL: " + sql);
		            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		            return ps;
		        }
		        return null;
			}
		}, keyHolder);
		
		int generatedId = keyHolder.getKey().intValue();
		return generatedId;
	}
}
