package com.cloud.open.dao;

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
import cn.egame.common.exception.ExceptionCommonBase;

import com.cloud.open.entity.exception.ErrorCode;
import com.cloud.open.entity.exception.ServiceExceptionBase;
import com.cloud.open.entity.po.FileInfo;

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
	
	public int insertFileInfo(final FileInfo fileInfo) throws ServiceExceptionBase{
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					if (fileInfo != null) {
						String sql = " insert into t_file(app_id, u_id "
								+ " , file_name, file_type, image_type, file_size, save_name "
								+ " , is_save, is_delete, is_complete, create_time, operator_id) "
								+ " values(%1$d, %2$d, %3$s, %4$d, %5$s, %6$d, %7$s"
								+ " , %8$d, %9$d, %10$d, %11$d, %12$d) ";
						sql = String.format(sql, fileInfo.getAppId(), fileInfo.getuId()
								, SqlUtils.QuataStr(fileInfo.getFileName()), fileInfo.getFileUsedType().value()
								, SqlUtils.QuataStr(fileInfo.getImageType()), fileInfo.getFileSize()
								, SqlUtils.QuataStr(fileInfo.getSaveName())
								, fileInfo.isSave() ? 1 : 0
								, fileInfo.isDelete() ? 1 : 0
								, fileInfo.isComplete() ? 1 : 0, System.currentTimeMillis()
								, fileInfo.getOperatorId());
						logger.info("operate t_game SQL: " + sql);
						PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						return ps;
					}
					return null;
				}
			}, keyHolder);
			
			int generatedId = keyHolder.getKey().intValue();
			return generatedId;
			
		} catch (Exception e) {
			throw ServiceExceptionBase.throwServiceExceptionBase(e);
		}
	}
	
	public Long updateFileInfo(final FileInfo fileInfo) throws ServiceExceptionBase{
		if(fileInfo == null || fileInfo.getId() <= 0){
			ServiceExceptionBase.throwServiceExceptionBase(new ExceptionCommonBase(ErrorCode.ParameterError
					, "fileInfo == null || fileInfo.getId() <= 0"));
		}
		String sql = " update t_file set app_id = %1$d, u_id = %2$d, file_name=%3$s "
				+ " ,file_type=%4$d, image_type=%5$s, file_size=%6$d, save_name=%7$s "
				+ " ,is_save=%8$d, is_delete=%9$d, is_complete=%10$d, update_time=%11$d"
				+ " ,operator_id=%12$d  where id=%13$d ";
		
		sql = String.format(sql, fileInfo.getAppId(), fileInfo.getuId()
				, SqlUtils.QuataStr(fileInfo.getFileName()), fileInfo.getFileUsedType().value()
				, SqlUtils.QuataStr(fileInfo.getImageType()), fileInfo.getFileSize()
				, SqlUtils.QuataStr(fileInfo.getSaveName())
				, fileInfo.isSave() ? 1 : 0
				, fileInfo.isDelete() ? 1 : 0
				, fileInfo.isComplete() ? 1 : 0
				, System.currentTimeMillis()
				, fileInfo.getOperatorId(), fileInfo.getId());
		logger.info("operate t_game SQL: " + sql);
		
		jdbcTemplate.update(sql);
		return fileInfo.getId();
	}
}
