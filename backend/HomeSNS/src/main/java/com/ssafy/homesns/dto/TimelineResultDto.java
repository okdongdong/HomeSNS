package com.ssafy.homesns.dto;

import java.util.List;

public class TimelineResultDto {
	private int result;
	private TimelineDto timelineDto;
    private List<TimelineDto> timelineDtoList;
    
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public TimelineDto getTimelineDto() {
		return timelineDto;
	}
	public void setTimelineDto(TimelineDto timelineDto) {
		this.timelineDto = timelineDto;
	}
	public List<TimelineDto> getTimelineDtoList() {
		return timelineDtoList;
	}
	public void setTimelineDtoList(List<TimelineDto> timelineDtoList) {
		this.timelineDtoList = timelineDtoList;
	}
	@Override
	public String toString() {
		return "TimelineResultDto [result=" + result + ", timelineDto=" + timelineDto + ", timelineDtoList="
				+ timelineDtoList + "]";
	}
    
    
}
