package com.ssafy.homesns.dto;

import java.util.List;

public class ScheduleResultDto {
	private int result;
	private List<ScheduleDto> scheduleDto;
	private int count;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<ScheduleDto> getScheduleDto() {
		return scheduleDto;
	}
	public void setScheduleDto(List<ScheduleDto> scheduleDto) {
		this.scheduleDto = scheduleDto;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "ScheduleResultDto [result=" + result + ", scheduleDto=" + scheduleDto + ", count=" + count + "]";
	}
	
	
}
