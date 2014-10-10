package com.cloud.spider.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import cn.egame.common.data.BaseDao;
import cn.egame.common.data.SqlUtils;
import cn.egame.common.exception.ExceptionCommonBase;

import com.cloud.spider.entity.po.MovieImageInfo;
import com.cloud.spider.entity.po.MovieInfo;

public class MovieDao_ extends BaseDao {
	
	private static Logger logger = Logger.getLogger(MovieDao_.class);

	private static final String STR_T_MOVIE_FIELD = " id, movie_subtype, movie_name, movie_original_name "
        			+ " , movie_alias_name, leading_role, screen_writer, directors, release_time "
        			+ " , movie_length, region, language, type, imdb_id "
        			+ " , imdb_rate, douban_id, douban_rate, movie_status, operator_id "
        			+ " , insert_time, update_time, introduction, operator_name ";
	
	
	
	public MovieDao_() throws ExceptionCommonBase {
		super("cloud");
	}

	public int insertMovieInfo(Connection conn, MovieInfo movieInfo) throws ExceptionCommonBase {
        int identity = 0;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (movieInfo != null) {
        	String sql = " insert into t_movie(movie_subtype, movie_name, movie_original_name "
        			+ " , movie_alias_name, leading_role, screen_writer, directors, release_time "
        			+ " , movie_length, region, language, type, imdb_id "
        			+ " , imdb_rate, douban_id, douban_rate, movie_status, operator_id "
        			+ " , insert_time, update_time, introduction, operator_name)"
        			+ " values(%1$d, %2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s"
        			+ " , %9$s, %10$s, %11$s, %12$s, %13$s"
        			+ " , %14$s, %15$s, %16$s, %17$d, %18$d"
        			+ " , str_to_date(%19$s,'%%Y-%%m-%%d %%H:%%i:%%s'), str_to_date(%20$s,'%%Y-%%m-%%d %%H:%%i:%%s')"
        			+ " , %21$s, %22$s) ";
            sql = String.format(sql, movieInfo.getMovieSubType(), SqlUtils.QuataStr(movieInfo.getMovieName()), SqlUtils.QuataStr(movieInfo.getMovieOriginalName())
            		, SqlUtils.QuataStr(movieInfo.getMovieAliasName()), SqlUtils.QuataStr(movieInfo.getLeadingRole())
            		, SqlUtils.QuataStr(movieInfo.getScreenWriter()), SqlUtils.QuataStr(movieInfo.getDirectors())
                    , SqlUtils.QuataStr(movieInfo.getReleaseTime()), SqlUtils.QuataStr(movieInfo.getMovieLength())
                    , SqlUtils.QuataStr(movieInfo.getRegion()), SqlUtils.QuataStr(movieInfo.getLanguage())
                    , SqlUtils.QuataStr(movieInfo.getType()), SqlUtils.QuataStr(movieInfo.getImdbId())
                    , SqlUtils.QuataStr(movieInfo.getImdbRate()), SqlUtils.QuataStr(movieInfo.getDoubanId())
                    , SqlUtils.QuataStr(movieInfo.getDoubanRate()), movieInfo.getMoviestatus()
                    , movieInfo.getOperatorId(), SqlUtils.QuataStr(sf.format(new Date()))
                    , SqlUtils.QuataStr(sf.format(new Date())), SqlUtils.QuataStr(movieInfo.getIntroduction())
                    , SqlUtils.QuataStr(movieInfo.getOperatorName()));
            logger.debug("operate t_game SQL: " + sql);
            identity = this.getIdentityId(conn, sql);
        }
        return identity;
    }
	
	public MovieInfo selectMovieInfoByMoiveNameAndDoubanId(MovieInfo movieInfo) throws ExceptionCommonBase {
		if(movieInfo == null){
			return null;
		}
		
		StringBuilder sb = new StringBuilder(" select  ").append(STR_T_MOVIE_FIELD)
				.append(" from t_movie where movie_name = %1$s and douban_id=%2$s ");
		String sql = String.format(sb.toString(), SqlUtils.QuataStr(movieInfo.getMovieName())
				, SqlUtils.QuataStr(movieInfo.getDoubanId()));
		logger.debug("operate t_game SQL: " + sql);
		
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			rs = this.executeQuery(conn, sql);
			if (rs.next()) {
				return formatMovieInfo(rs);
			}
			return null;
		} catch (SQLException e) {
			throw ExceptionCommonBase.throwExceptionCommonBase(e);
		} finally {
			this.close(rs);
			this.releaseConnection(conn);
		}
    }
	
	private MovieInfo formatMovieInfo(ResultSet rs) throws SQLException {
        int i = 1;
        MovieInfo info = new MovieInfo();
        info.setId(rs.getLong(i++));
        info.setMovieSubType(rs.getLong(i++));
        info.setMovieName(rs.getString(i++));
        info.setMovieOriginalName(rs.getString(i++));
        info.setMovieAliasName(rs.getString(i++));
        info.setLeadingRole(rs.getString(i++));
        info.setScreenWriter(rs.getString(i++));
        info.setDirectors(rs.getString(i++));
        info.setReleaseTime(rs.getString(i++));
        info.setMovieLength(rs.getString(i++));
        info.setRegion(rs.getString(i++));
        info.setLanguage(rs.getString(i++));
        info.setType(rs.getString(i++));
        info.setImdbId(rs.getString(i++));
        info.setImdbRate(rs.getString(i++));
        info.setDoubanId(rs.getString(i++));
        info.setDoubanRate(rs.getString(i++));
        info.setMoviestatus(rs.getLong(i++));
        info.setOperatorId(rs.getLong(i++));
        info.setInsertTime(rs.getDate(i++));
        info.setUpdateTime(rs.getDate(i++));
        info.setIntroduction(rs.getString(i++));
        info.setOperatorName(rs.getString(i++));
        return info;
    }

	public void updateMovieInfo(Connection conn, MovieInfo movieInfo) throws ExceptionCommonBase {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(movieInfo.getId()<=0){
			return;
		}
		String sql = " update t_movie "
    			+ " set movie_subtype=%1$d, movie_name=%2$s, movie_original_name=%3$s "
    			+ " , movie_alias_name=%4$s, leading_role=%5$s, screen_writer=%6$s "
    			+ " , directors=%7$s, release_time=%8$s, movie_length=%9$s, region=%10$s "
    			+ " , language=%11$s, type=%12$s, imdb_id=%13$s, imdb_rate=%14$s "
    			+ " , douban_id=%15$s, douban_rate=%16$s, movie_status=%17$d, operator_id=%18$d "
    			+ " , insert_time=str_to_date(%19$s,'%%Y-%%m-%%d %%H:%%i:%%s') "
    			+ " , update_time=str_to_date(%20$s,'%%Y-%%m-%%d %%H:%%i:%%s') "
    			+ " , introduction=%21$s, operator_name=%22$s where id=%23$d ";
        sql = String.format(sql, movieInfo.getMovieSubType(), SqlUtils.QuataStr(movieInfo.getMovieName()), SqlUtils.QuataStr(movieInfo.getMovieOriginalName())
        		, SqlUtils.QuataStr(movieInfo.getMovieAliasName()), SqlUtils.QuataStr(movieInfo.getLeadingRole())
        		, SqlUtils.QuataStr(movieInfo.getScreenWriter()), SqlUtils.QuataStr(movieInfo.getDirectors())
                , SqlUtils.QuataStr(movieInfo.getReleaseTime()), SqlUtils.QuataStr(movieInfo.getMovieLength())
                , SqlUtils.QuataStr(movieInfo.getRegion()), SqlUtils.QuataStr(movieInfo.getLanguage())
                , SqlUtils.QuataStr(movieInfo.getType()), SqlUtils.QuataStr(movieInfo.getImdbId())
                , SqlUtils.QuataStr(movieInfo.getImdbRate()), SqlUtils.QuataStr(movieInfo.getDoubanId())
                , SqlUtils.QuataStr(movieInfo.getDoubanRate()), movieInfo.getMoviestatus()
                , movieInfo.getOperatorId(), SqlUtils.QuataStr(sf.format(new Date()))
                , SqlUtils.QuataStr(sf.format(new Date())), SqlUtils.QuataStr(movieInfo.getIntroduction())
                , SqlUtils.QuataStr(movieInfo.getOperatorName()), movieInfo.getId());
        logger.debug("operate t_game SQL: " + sql);
        this.executeUpdate(conn, sql);
	}
	
	public void insertMovieImageInfo(Connection conn, MovieImageInfo movieImageInfo) throws ExceptionCommonBase {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (movieImageInfo != null) {
        	String sql = " insert into t_movie_image(efs_id, m_id, operator_id, insert_time "
        			+ " , update_time, file_type, sort)"
        			+ " values(%1$d, %2$d, %3$d"
        			+ " , str_to_date(%4$s,'%%Y-%%m-%%d %%H:%%i:%%s') "
        			+ " , str_to_date(%5$s,'%%Y-%%m-%%d %%H:%%i:%%s') "
        			+ " , %6$d, %7$d) ";
            sql = String.format(sql, movieImageInfo.getEfsId(), movieImageInfo.getmId()
            		, movieImageInfo.getOperatorId(), SqlUtils.QuataStr(sf.format(new Date()))
            	    , SqlUtils.QuataStr(sf.format(new Date())), movieImageInfo.getFileType().value()
            	    , movieImageInfo.getSort());
            logger.debug("operate t_game SQL: " + sql);
            this.executeUpdate(conn, sql);
        }
    }
}
