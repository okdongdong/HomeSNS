package com.ssafy.homesns.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class MainFeedDto {

    private int feedId;
    private String feedTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate feedEventDate;
    private String feedLocation;

    private List<MainFileDto> fileList;

    public int getFeedId() {
        return feedId;
    }
    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }
    public String getFeedTitle() {
        return feedTitle;
    }
    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }
    public LocalDate getFeedEventDate() {
        return feedEventDate;
    }
    public void setFeedEventDate(LocalDate feedEventDate) {
        this.feedEventDate = feedEventDate;
    }
    public String getFeedLocation() {
        return feedLocation;
    }
    public void setFeedLocation(String feedLocation) {
        this.feedLocation = feedLocation;
    }
    public List<MainFileDto> getFileList() {
        return fileList;
    }
    public void setFileList(List<MainFileDto> fileList) {
        this.fileList = fileList;
    }
    @Override
    public String toString() {
        return "MainFeedDto [feedId=" + feedId + ", feedTitle=" + feedTitle + ", feedLocation="
                + feedLocation + ", feedEventDate=" + feedEventDate +", fileList=" + fileList + "]";
    }
}
