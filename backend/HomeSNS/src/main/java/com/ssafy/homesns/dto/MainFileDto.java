package com.ssafy.homesns.dto;

public class MainFileDto {
    private String fileContentType;
    private String fileUrl;

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

    @Override
    public String toString() {
        return "MainFileDto [fileContentType=" + fileContentType + ", fileUrl=" + fileUrl + "]";
    }
}
