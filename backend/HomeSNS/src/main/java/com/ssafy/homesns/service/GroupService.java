package com.ssafy.homesns.service;

import com.ssafy.homesns.dto.GroupDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.GroupResultDto;

public interface GroupService {
	public GroupResultDto groupList(int userSeq);
	public GroupResultDto groupCreate(GroupDto groupDto);
	public GroupResultDto groupDelete(GroupMemberDto groupMemberDto);
}
