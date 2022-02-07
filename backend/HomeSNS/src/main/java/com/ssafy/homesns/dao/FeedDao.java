package com.ssafy.homesns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.CommentDto;
import com.ssafy.homesns.dto.EventMemberDto;
import com.ssafy.homesns.dto.FeedDto;
import com.ssafy.homesns.dto.FileDto;
import com.ssafy.homesns.dto.HashtagDto;
import com.ssafy.homesns.dto.LocationDto;
import com.ssafy.homesns.dto.LocationFavoriteDto;
import com.ssafy.homesns.dto.UserDto;

@Mapper
public interface FeedDao {

	//groupId에 맞는 feed를 DB에서 찾아온다.
	public List<FeedDto> feedMainPage(int groupId);

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
	
	// feed수정
	public int feedUpdate(FeedDto feedDto);
	
	// feed수정,삭제
	public List<String> feedFileUrlDeleteList(int feedId);
	public int feedFileDelete(int feedId);
	
	// feed 삭제
	// feed지우면 fk 테이블 자동삭제 cascade
	public int feedDelete(int feedId);

}
