package com.ssafy.homesns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.homesns.dto.CommentDto;
import com.ssafy.homesns.dto.EventMemberDto;
import com.ssafy.homesns.dto.FeedDto;
import com.ssafy.homesns.dto.FeedParamDto;
import com.ssafy.homesns.dto.FileDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.HashtagDto;
import com.ssafy.homesns.dto.LocationDto;
import com.ssafy.homesns.dto.LocationFavoriteDto;
import com.ssafy.homesns.dto.MainFeedDto;
import com.ssafy.homesns.dto.MainFileDto;
import com.ssafy.homesns.dto.UserDto;

@Mapper
public interface FeedDao {

	//groupId에 맞는 feed를 DB에서 찾아온다.
	// 밑에 두개 태현이가 건들임
	public List<MainFeedDto> feedMain(FeedParamDto feedParamDto);
	public List<MainFileDto> mainFileList(int feedId);

	public List<FileDto> fileList(int feedId);

	public List<CommentDto> commentList(@Param("feedId") int feedId,@Param("userSeq")int userSeq);
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
	public int feedLocationUpdate(LocationDto locationDto);
	
	// feed 삭제후 수정용
	public int feedHashtagDelete(int feedId);
	public int feedEventMemberDelete(int feedId);
	
	// feed File 삭제후 수정
	public List<String> feedFileUrlDeleteList(int feedId);
	public int feedFileDelete(int feedId);
	public int feedFileDeleteUseFileID(int fileId);
	
	// feed 삭제
	// feed지우면 fk 테이블 자동삭제 cascade
	public int feedDelete(int feedId);
	public int locationFavoriteDelete(LocationFavoriteDto locationFavoriteDto);

}
