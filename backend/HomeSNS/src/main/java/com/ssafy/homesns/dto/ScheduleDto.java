package com.ssafy.homesns.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDto {
	private int scheduleId;
	private int groupId;
	private String scheduleTitle;
	private String scheduleContent;

	// 연-월-일까지만
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String scheduleDateStart;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String scheduleDateEnd;

	// 시:분
//	@DateTimeFormat(pattern = "HH:mm")
	private String scheduleTimeStart;
//	@DateTimeFormat(pattern = "HH:mm")
	private String scheduleTimeEnd;

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

	public String getScheduleDateStart() {
		return scheduleDateStart;
	}

	public void setScheduleDateStart(String scheduleDateStart) {
		this.scheduleDateStart = scheduleDateStart;
	}

	public String getScheduleDateEnd() {
		return scheduleDateEnd;
	}

	public void setScheduleDateEnd(String scheduleDateEnd) {
		this.scheduleDateEnd = scheduleDateEnd;
	}

	public String getScheduleTimeStart() {
		return scheduleTimeStart;
	}

	public void setScheduleTimeStart(String scheduleTimeStart) {
		this.scheduleTimeStart = scheduleTimeStart;
	}

	public String getScheduleTimeEnd() {
		return scheduleTimeEnd;
	}

	public void setScheduleTimeEnd(String scheduleTimeEnd) {
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
