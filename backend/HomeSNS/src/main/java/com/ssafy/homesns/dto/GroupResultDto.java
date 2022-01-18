package com.ssafy.homesns.dto;

import java.util.List;

public class GroupResultDto {
	private int result;
	private List<GroupDto> groupDtoList;
	private int count;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<GroupDto> getGroupDtoList() {
		return groupDtoList;
	}
	public void setGroupDtoList(List<GroupDto> groupDtoList) {
		this.groupDtoList = groupDtoList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "GroupResultDto [result=" + result + ", groupDtoList=" + groupDtoList + ", count=" + count + "]";
	}

}
