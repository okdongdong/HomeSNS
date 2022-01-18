package com.ssafy.homesns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.GroupDto;
import com.ssafy.homesns.dto.GroupMemberDto;

@Mapper
public interface GroupDao {
	// 특정 유저가 참여하고 있는 그룹 리스트
	public List<GroupDto> groupList(int userSeq);
	// 특정 유저가 참여하고 있는 그룹의 수
	public int groupListTotalCount();
	// 그룹 생성
	public int groupCreate(GroupDto groupDto);
	// 그룹 나가기
	public int groupDelete(GroupMemberDto groupMembetDto);
	
	// 그룹에 멤버 추가하기
	public int GroupMemberAdd(GroupMemberDto groupMemberDto);
}
