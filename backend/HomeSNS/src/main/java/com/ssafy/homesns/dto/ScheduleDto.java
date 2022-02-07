package com.ssafy.homesns.dto;

import java.sql.Date;
import java.sql.Time;

public class ScheduleDto {
	private int scheduleId;
	private int groupId;
	private String scheduleTitle;
	private String scheduleContent;
	private Date scheduleDate;
	private Time scheduleTime;
	private String scheduleLocation;
	
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getScheduleTitle() {
		return scheduleTitle;
	}
	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}
	public String getScheduleContent() {
		return scheduleContent;
	}
	public void setScheduleContent(String scheduleContent) {
		this.scheduleContent = scheduleContent;
	}
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public Time getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(Time scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public String getScheduleLocation() {
		return scheduleLocation;
	}
	public void setScheduleLocation(String scheduleLocation) {
		this.scheduleLocation = scheduleLocation;
	}
	
	@Override
	public String toString() {
		return "ScheduleDto [scheduleId=" + scheduleId + ", groupId=" + groupId + ", scheduleTitle=" + scheduleTitle
				+ ", scheduleContent=" + scheduleContent + ", scheduleDate=" + scheduleDate + ", scheduleTime="
				+ scheduleTime + ", scheduleLocation=" + scheduleLocation + "]";
	}
	
	
}
