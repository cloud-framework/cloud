package com.cloud.spider.entity.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductBO implements Serializable{
	
	private static final long serialVersionUID = 3854807877137057893L;
	
	private Long page_id;
	private Long pic_id;
	private List<String> picUrl = new ArrayList<String>();
	private String author;
	
	public Long getPage_id() {
		return page_id;
	}
	public void setPage_id(Long page_id) {
		this.page_id = page_id;
	}
	public Long getPic_id() {
		return pic_id;
	}
	public void setPic_id(Long pic_id) {
		this.pic_id = pic_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<String> getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(List<String> picUrl) {
		this.picUrl = picUrl;
	}
	
	
	
}
