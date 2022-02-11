package com.ssafy.homesns.dto;

import java.util.Date;

public class FileDto {
	
	private int fileId;
	private int feedId; 
	private String fileName; 
	private float fileSize;
	private String fileContentType;
	private String fileUrl; 
	private Date regDt;
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public float getFileSize() {
		return fileSize;
	}
	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	@Override
	public String toString() {
		return "FileDto [fileId=" + fileId + ", feedId=" + feedId + ", fileName=" + fileName + ", fileSize=" + fileSize
				+ ", fileContentType=" + fileContentType + ", fileUrl=" + fileUrl + ", regDt=" + regDt + "]";
	}
	
	
	

}
