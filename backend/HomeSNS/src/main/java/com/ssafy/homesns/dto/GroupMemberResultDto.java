package com.ssafy.homesns.dto;

import java.util.List;

public class GroupMemberResultDto {
	private int result;
	private GroupDto groupDto;
	
	private List<GroupMemberDto> groupMemberDtoList;
	private List<UserDto> userDtoList;
	private int count;
	
	
	public GroupDto getGroupDto() {
		return groupDto;
	}
	public void setGroupDto(GroupDto groupDto) {
		this.groupDto = groupDto;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<GroupMemberDto> getGroupMemberDtoList() {
		return groupMemberDtoList;
	}
	public void setGroupMemberDtoList(List<GroupMemberDto> groupMemberDtoList) {
		this.groupMemberDtoList = groupMemberDtoList;
	}
	public List<UserDto> getUserDtoList() {
		return userDtoList;
	}
	public void setUserDtoList(List<UserDto> userDtoList) {
		this.userDtoList = userDtoList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "GroupMemberResultDto [result=" + result + ", groupDto=" + groupDto + ", groupMemberDtoList="
				+ groupMemberDtoList + ", userDtoList=" + userDtoList + ", count=" + count + "]";
	}

}
