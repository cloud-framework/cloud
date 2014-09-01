package com.cloud.valueobject.vo;

import java.io.Serializable;

public class SearchResultVO implements Serializable{
	
	private static final long serialVersionUID = 372769373804744068L;
	
	private String torrentName;
	private String torrentUrl;
	private String size;
	public String getTorrentName() {
		return torrentName;
	}
	public void setTorrentName(String torrentName) {
		this.torrentName = torrentName;
	}
	public String getTorrentUrl() {
		return torrentUrl;
	}
	public void setTorrentUrl(String torrentUrl) {
		this.torrentUrl = torrentUrl;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
}
