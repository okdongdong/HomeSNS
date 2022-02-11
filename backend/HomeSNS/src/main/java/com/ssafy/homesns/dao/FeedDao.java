package com.ssafy.homesns.dao;

import java.util.List;

import com.ssafy.homesns.dto.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedDao {

	//groupId에 맞는 feed를 DB에서 찾아온다.
	// 밑에 두개 태현이가 건들임
	public List<MainFeedDto> feedMain(FeedParamDto feedParamDto);
	public List<MainFileDto> mainFileList(int feedId);

	public List<FileDto> fileList(int feedId);

	public List<CommentDto> commentList(int feedId);
	public List<HashtagDto> hashtagList(int feedId);
	public List<UserDto> eventMemberList(int feedId);
	public LocationDto locationSearch(int feedId);
	public List<LocationFavoriteDto> locationFavoriteSearch(int userSeq);
	
	public FeedDto feedDetail(int feedId);
	
	// feed추가
	public int feedInsert(FeedDto feedDto);
	public int feedFileInsert(FileDto fileDto);
	public int feedHashtagInsert(HashtagDto hashtagDto);
	public int feedEventMemberInsert(EventMemberDto eventMemberDto);
	public int feedLocationInsert(LocationDto locationDto);
	public int feedLocationFavoriteInsert(LocationFavoriteDto locationFavoriteDto);
	
	// feed추가할때 프런트에서 가지고 있어야 하는 정보리턴
	public List<LocationDto> userLocationList(GroupMemberDto params); 	
	public List<GroupMemberDto> groupMemberList(int groupId); 
	
	
	// feed수정
	public int feedUpdate(FeedDto feedDto);
	
	// feed수정,삭제
	public List<String> feedFileUrlDeleteList(int feedId);
	public int feedFileDelete(int feedId);
	
	// feed 삭제
	// feed지우면 fk 테이블 자동삭제 cascade
	public int feedDelete(int feedId);

}
