package com.ssafy.homesns.dto;

import java.util.List;

public class ScheduleResultDto {
	private int result;
	private int scheduleId;
	
	private ScheduleDto scheduleDto;
	private List<ScheduleDto> scheduleDtoList;
	private int count;
	
	
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public ScheduleDto getScheduleDto() {
		return scheduleDto;
	}
	public void setScheduleDto(ScheduleDto scheduleDto) {
		this.scheduleDto = scheduleDto;
	}
	public List<ScheduleDto> getScheduleDtoList() {
		return scheduleDtoList;
	}
	public void setScheduleDtoList(List<ScheduleDto> scheduleDtoList) {
		this.scheduleDtoList = scheduleDtoList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "ScheduleResultDto [result=" + result + ", scheduleId=" + scheduleId + ", scheduleDto=" + scheduleDto
				+ ", scheduleDtoList=" + scheduleDtoList + ", count=" + count + "]";
	}

}
