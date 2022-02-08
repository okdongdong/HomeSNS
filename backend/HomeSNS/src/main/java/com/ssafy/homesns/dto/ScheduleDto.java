package com.ssafy.homesns.dto;

import java.sql.Date;
import java.sql.Time;

public class ScheduleDto {
	private int scheduleId;
	private int groupId;
	private String scheduleTitle;
	private String scheduleContent;

	// 연-월-일까지만
	private Date scheduleDateStart;
	private Date scheduleDateEnd;

	// 시:분
	private Time scheduleTimeStart;
	private Time scheduleTimeEnd;

	// 장소는 일단 보류
//	private String scheduleLocation;


	public int getScheduleId() {
		return scheduleId;
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

	public Date getScheduleDateStart() {
		return scheduleDateStart;
	}

	public void setScheduleDateStart(Date scheduleDateStart) {
		this.scheduleDateStart = scheduleDateStart;
	}

	public Date getScheduleDateEnd() {
		return scheduleDateEnd;
	}

	public void setScheduleDateEnd(Date scheduleDateEnd) {
		this.scheduleDateEnd = scheduleDateEnd;
	}

	public Time getScheduleTimeStart() {
		return scheduleTimeStart;
	}

	public void setScheduleTimeStart(Time scheduleTimeStart) {
		this.scheduleTimeStart = scheduleTimeStart;
	}

	public Time getScheduleTimeEnd() {
		return scheduleTimeEnd;
	}

	public void setScheduleTimeEnd(Time scheduleTimeEnd) {
		this.scheduleTimeEnd = scheduleTimeEnd;
	}

	@Override
	public String toString() {
		return "ScheduleDto{" +
				"scheduleId=" + scheduleId +
				", groupId=" + groupId +
				", scheduleTitle='" + scheduleTitle + '\'' +
				", scheduleContent='" + scheduleContent + '\'' +
				", scheduleDateStart=" + scheduleDateStart +
				", scheduleDateEnd=" + scheduleDateEnd +
				", scheduleTimeStart=" + scheduleTimeStart +
				", scheduleTimeEnd=" + scheduleTimeEnd +
				'}';
	}
}
