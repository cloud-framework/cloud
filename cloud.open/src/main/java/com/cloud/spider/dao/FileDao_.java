package com.cloud.spider.dao;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import cn.egame.common.data.BaseDao;
import cn.egame.common.data.SqlUtils;
import cn.egame.common.exception.ExceptionCommonBase;

import com.cloud.open.entity.po.FileInfo;

public class FileDao_ extends BaseDao{
	private static Logger logger = Logger.getLogger(FileDao_.class);

	public FileDao_() throws ExceptionCommonBase {
		super("cloud");
	}

	public int insertFileInfo(Connection conn, FileInfo fileInfo) throws ExceptionCommonBase {
        int identity = 0;
        if (fileInfo != null) {
        	String sql = " insert into t_file(app_id, u_id "
        			+ " , file_name, file_type, image_type, file_size, save_name "
        			+ " , is_save, is_delete, is_complete, create_time) "
        			+ " values(%1$d, %2$d, %3$s, %4$d, %5$s, %6$d, %7$s"
        			+ " , %8$d, %9$d, %10$d, %11$d) ";
            sql = String.format(sql, fileInfo.getAppId(), fileInfo.getuId()
            		, SqlUtils.QuataStr(fileInfo.getFileName()), fileInfo.getFileUsedType().value()
            		, SqlUtils.QuataStr(fileInfo.getImageType()), fileInfo.getFileSize()
            		, SqlUtils.QuataStr(fileInfo.getSaveName()), fileInfo.isSave()
            		, fileInfo.isDelete(), fileInfo.isComplete(), System.currentTimeMillis());
            logger.debug("operate t_game SQL: " + sql);
            identity = this.getIdentityId(conn, sql);
        }
        return identity;
    }
	
	
}
