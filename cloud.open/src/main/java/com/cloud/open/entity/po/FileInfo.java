package com.cloud.open.entity.po;

import com.cloud.open.entity.constants.FileUsedType;

public class FileInfo {
	private Long id;
	private Long appId;
	private Long uId;
	private String fileName;
	private FileUsedType fileUsedType;
	private String imageType;
	private Long fileSize;
	private String saveName;
	private boolean isSave;
	private boolean isDelete;
	private boolean isComplete;
	private Long createTime;
	private Long updateTime;
	private Long operatorId;
	
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public FileUsedType getFileUsedType() {
		return fileUsedType;
	}
	public void setFileUsedType(FileUsedType fileUsedType) {
		this.fileUsedType = fileUsedType;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	
	public boolean isSave() {
		return isSave;
	}
	public void setSave(boolean isSave) {
		this.isSave = isSave;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	
}
