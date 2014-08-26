package com.cloud.valueobject.vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.jdbc.core.RowMapper;

@XmlRootElement 
public class MovieInfo implements RowMapper<MovieInfo>{
	private Long id;
	private Long movieSubType;
	private String movieName;
	private String movieOriginalName;
	private String movieAliasName;
	private String leadingRole;
	private String ScreenWriter;
	private String directors;
	private String releaseTime;
	private String movieLength;
	private String region;
	private String language;
	private String type;
	private String imdbId;
	private String imdbRate;
	private String doubanId;
	private String doubanRate;
	private Long moviestatus;
	private Long operatorId;
	private Date insertTime;
	private Date updateTime;
	private String introduction;
	private String operatorName;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMovieSubType() {
		return movieSubType;
	}
	public void setMovieSubType(Long movieSubType) {
		this.movieSubType = movieSubType;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieOriginalName() {
		return movieOriginalName;
	}
	public void setMovieOriginalName(String movieOriginalName) {
		this.movieOriginalName = movieOriginalName;
	}
	public String getMovieAliasName() {
		return movieAliasName;
	}
	public void setMovieAliasName(String movieAliasName) {
		this.movieAliasName = movieAliasName;
	}
	public String getLeadingRole() {
		return leadingRole;
	}
	public void setLeadingRole(String leadingRole) {
		this.leadingRole = leadingRole;
	}
	public String getScreenWriter() {
		return ScreenWriter;
	}
	public void setScreenWriter(String screenWriter) {
		ScreenWriter = screenWriter;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getMovieLength() {
		return movieLength;
	}
	public void setMovieLength(String movieLength) {
		this.movieLength = movieLength;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getImdbRate() {
		return imdbRate;
	}
	public void setImdbRate(String imdbRate) {
		this.imdbRate = imdbRate;
	}
	public String getDoubanId() {
		return doubanId;
	}
	public void setDoubanId(String doubanId) {
		this.doubanId = doubanId;
	}
	public String getDoubanRate() {
		return doubanRate;
	}
	public void setDoubanRate(String doubanRate) {
		this.doubanRate = doubanRate;
	}
	public Long getMoviestatus() {
		return moviestatus;
	}
	public void setMoviestatus(Long moviestatus) {
		this.moviestatus = moviestatus;
	}
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	@Override
	public MovieInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		MovieInfo info = new MovieInfo();
		info.setId(rs.getLong("id"));
		info.setMovieSubType(rs.getLong("movie_subtype"));
		info.setMovieName(rs.getString("movie_name"));
		info.setMovieOriginalName(rs.getString("movie_original_name"));
		info.setMovieAliasName(rs.getString("movie_alias_name"));
		info.setLeadingRole(rs.getString("leading_role"));
		info.setScreenWriter(rs.getString("screen_writer"));
		info.setDirectors(rs.getString("directors"));
		info.setReleaseTime(rs.getString("release_time"));
		info.setMovieLength(rs.getString("movie_length"));
		info.setRegion(rs.getString("region"));
		info.setLanguage(rs.getString("language"));
		info.setType(rs.getString("type"));
		info.setImdbId(rs.getString("imdb_id"));
		info.setImdbRate(rs.getString("imdb_rate"));
		info.setDoubanId(rs.getString("douban_id"));
		info.setDoubanRate(rs.getString("douban_rate"));
		info.setMoviestatus(rs.getLong("movie_status"));
		info.setOperatorId(rs.getLong("operator_id"));
		info.setInsertTime(rs.getDate("insert_time"));
		info.setUpdateTime(rs.getDate("update_time"));
		info.setIntroduction(rs.getString("introduction"));
		info.setOperatorName(rs.getString("operator_name"));
		return info;
	}
	
}
