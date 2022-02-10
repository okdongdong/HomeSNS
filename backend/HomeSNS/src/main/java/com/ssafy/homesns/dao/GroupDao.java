package com.ssafy.homesns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.GroupDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.ProfileImageDto;
import com.ssafy.homesns.dto.UserDto;

@Mapper
public interface GroupDao {
	
	// 그룹 생성
	public int groupListCreate(GroupDto groupDto);
	// 그룹 프로필 사진 저장
	public int groupProfileImageInsert(ProfileImageDto profileImageDto);
	// 그룹 프로필 사진 수정
	public int groupProfileImageUpdate(ProfileImageDto profileImageDto);
	
	// 특정 유저가 그룹 리더이고 가장 최근에 생성된 그룹ID 찾기
	public int groupListRecentMake(int userSeq);
	
	// 그룹 리스트 불러오기 / 특정 유저가 참여하고 있는 그룹 리스트
	public List<GroupDto> groupListSearch(int userSeq);

	// 특정 유저가 참여하고 있는 그룹의 수
	public int groupListTotalCount(int userSeq);

	// 그룹 수정
	public int groupListUpdate(GroupDto groupDto);

	// 그룹 삭제 / 그룹을 삭제하기 위해서 그룹의 인원이 1명이면서 내가 그룹리더여야 한다
	public int groupListDelete(int groupId);

	// 그룹 리더 찾기
	public int groupListLeaderSearch(int groupId);

	
	
	// 그룹 비밀번호 확인
	public String groupPasswordCheck(int groupId);
	
	// 그룹 멤버 생성
	public int groupMemberCreate(GroupMemberDto groupMemberDto);

	// 그룹 멤버의 유저정보 불러오기
	public List<UserDto> groupMemberListSearch(int groupId);
	
	// 그룹의 멤버 수
	public int groupMemberTotalCount(int groupId);

	// 그룹 리더 찾기
	public int groupLeaderSearch(int groupId);
	
	// 그룹 멤버 수정 -> 매니저 권한 수정
	public int groupMemberManagerAuthApprove(int userSeq);
	
	// 그룹 멤버 수정 -> 매니저 권한 삭제
	public int groupMemberManagerAuthDecline(int userSeq);

	// 그룹 멤버 삭제
	public int groupMemberDelete(GroupMemberDto groupMembetDto);
}
