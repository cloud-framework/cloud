package com.cloud.open.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.egame.common.exception.ExceptionCommonBase;

import com.cloud.valueobject.vo.MovieInfo;

@Service
public class MovieDao {
	private static Logger logger = Logger.getLogger(MovieDao.class);
	

	private static final String STR_T_MOVIE_FIELD = " id, movie_subtype, movie_name, movie_original_name "
			+ " , movie_alias_name, leading_role, screen_writer, directors, release_time "
			+ " , movie_length, region, language, type, imdb_id "
			+ " , imdb_rate, douban_id, douban_rate, movie_status, operator_id "
			+ " , insert_time, update_time, introduction, operator_name ";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<MovieInfo> listMovies() throws ExceptionCommonBase {
		String sql = " select " + STR_T_MOVIE_FIELD + " from t_movie ";
		logger.info(sql);
		List<MovieInfo> list = jdbcTemplate.query(sql, new MovieInfo());
		return list;
	}
	
	/*
	public List<MovieInfo> listMovies() throws ExceptionCommonBase {
		List<MovieInfo> list = new ArrayList<MovieInfo>();
		String sql = " select " + STR_T_MOVIE_FIELD + " from t_movie ";
		logger.info(sql);
		List<Map<String, Object>> queryResult = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : queryResult) {
			MovieInfo info = new MovieInfo();
			info.setId((Long) map.get("id"));
			info.setMovieSubType((Long) map.get("movie_subtype"));
			info.setMovieName((String) map.get("movie_name"));
			info.setMovieOriginalName((String) map.get("movie_original_name"));
			info.setMovieAliasName((String) map.get("movie_alias_name"));
			info.setLeadingRole((String) map.get("leading_role"));
			info.setScreenWriter((String) map.get("screen_writer"));
			info.setDirectors((String) map.get("directors"));
			info.setReleaseTime((String) map.get("release_time"));
			info.setMovieLength((String) map.get("movie_length"));
			info.setRegion((String) map.get("region"));
			info.setLanguage((String) map.get("language"));
			info.setType((String) map.get("type"));
			info.setImdbId((String) map.get("imdb_id"));
			info.setImdbRate((String) map.get("imdb_rate"));
			info.setDoubanId((String) map.get("douban_id"));
			info.setDoubanRate((String) map.get("douban_rate"));
			info.setMoviestatus((Long) map.get("movie_status"));
			info.setOperatorId((Long) map.get("operator_id"));
			info.setInsertTime((Date) map.get("insert_time"));
			info.setUpdateTime((Date) map.get("update_time"));
			info.setIntroduction((String) map.get("introduction"));
			info.setOperatorName((String) map.get("operator_name"));
			list.add(info);
		}

		return list;
	}
	*/
	
}
