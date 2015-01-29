package com.cloud.spider.entity.po;

import java.sql.Date;

import com.cloud.spider.entity.constants.FileUsedType;

public class ProductImageInfo {
	private Long efsId;
	private Long productId;
	private Long operatorId;
	private Date insertTime;
	private Date updateTime;
	private FileUsedType fileType;
	private Long sort;
	public Long getEfsId() {
		return efsId;
	}
	public void setEfsId(Long efsId) {
		this.efsId = efsId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	
}
