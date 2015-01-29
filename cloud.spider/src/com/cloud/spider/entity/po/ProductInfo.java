package com.cloud.spider.entity.po;

import java.io.Serializable;
import java.util.Date;

public class ProductInfo implements Serializable{
	
	private static final long serialVersionUID = 2457902725243819253L;
	
	private Long id;
	private Long productType;
	private String productName;
	private Integer productStatus;
	private Long productSrcPageId;
	private Long productSrcId;
	private String productAuthor;
	private Date releaseTime;
	private String productRelativeId;
	private Long operatorId;
	private Date insertTime;
	private Date updateTime;
	private String operatorName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductType() {
		return productType;
	}
	public void setProductType(Long productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getProductSrcPageId() {
		return productSrcPageId;
	}
	public void setProductSrcPageId(Long productSrcPageId) {
		this.productSrcPageId = productSrcPageId;
	}
	public Long getProductSrcId() {
		return productSrcId;
	}
	public void setProductSrcId(Long productSrcId) {
		this.productSrcId = productSrcId;
	}
	public String getProductAuthor() {
		return productAuthor;
	}
	public void setProductAuthor(String productAuthor) {
		this.productAuthor = productAuthor;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getProductRelativeId() {
		return productRelativeId;
	}
	public void setProductRelativeId(String productRelativeId) {
		this.productRelativeId = productRelativeId;
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
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	
	
}
