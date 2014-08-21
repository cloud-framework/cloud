package com.cloud.spider.entity.bo;

import com.cloud.spider.entity.po.MovieInfo;

public class MovieBO {
	
	private Integer no;
	private MovieInfo movieInfo;
	private String iconUrl;
	
	public MovieInfo getMovieInfo() {
		return movieInfo;
	}

	public void setMovieInfo(MovieInfo movieInfo) {
		this.movieInfo = movieInfo;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}
	
	
}
