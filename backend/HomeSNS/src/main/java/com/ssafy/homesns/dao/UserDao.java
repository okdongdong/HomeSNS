package com.ssafy.homesns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.FeedParamDto;
import com.ssafy.homesns.dto.MainFeedDto;
import com.ssafy.homesns.dto.ProfileImageDto;
import com.ssafy.homesns.dto.UserDto;

@Mapper
public interface UserDao {
	
	// 유저 정보 저장
	public int userRegister(UserDto userDto);
	// 유저 프로필 사진 저장
	public int userProfileImageInsert(ProfileImageDto profileImageDto);
	// 유저 프로필 사진 수정
	public int userProfileImageUpdate(ProfileImageDto profileImageDto);
	
	// userSeq를 받아서 DB에 있는 유저 정보를 가져온다.
	public UserDto userMypageSearch(int userSeq);
	
	// userDto를 받아서 DB에 유저 정보를 수정한다
	public int userUpdate(UserDto userDto);
	
	// userSeq를 받아서 DB에 유저 정보를 삭제한다
	public int userDelete(int userSeq);
	
	// phone을 받아서 DB에 있는 유저 정보를 가져온다.
	public UserDto userExist(String userPhone);
	
	// id를 받아서 DB에 유저 정보가 있는지 확인한다
	public int checkUserId(String userId);
	
	// email을 받아서 DB에 유저 정보가 있는지 확인한다
	public int checkUserEmail(String userEmail);
	
	// phone을 받아서 DB에 유저 정보가 있는지 확인한다
	public int checkUserPhone(String userPhone);
	
	
	// userSeq를 받아서 유저가 작성한 피드를 찾는다
	public List<MainFeedDto> userFeedSearch(FeedParamDto feedParamDto);
	// userSeq를 받아서 유저가 스크랩한 피드를 찾는다
	public List<MainFeedDto> userScrapSearch(FeedParamDto feedParamDto);
}
