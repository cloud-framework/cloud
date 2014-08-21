package com.cloud.spider.entity.po;

import java.sql.Date;

import com.cloud.spider.entity.constants.FileUsedType;

public class MovieImageInfo {
	private Long efsId;
	private Long mId;
	private Long operatorId;
	private Date insertTime;
	private Date updateTime;
	private FileUsedType fileType;
	private Integer sort;
	
	public Long getEfsId() {
		return efsId;
	}
	public void setEfsId(Long efsId) {
		this.efsId = efsId;
	}
	public Long getmId() {
		return mId;
	}
	public void setmId(Long mId) {
		this.mId = mId;
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
	public FileUsedType getFileType() {
		return fileType;
	}
	public void setFileType(FileUsedType fileType) {
		this.fileType = fileType;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
