package com.ssafy.homesns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.homesns.dto.CommentDto;
import com.ssafy.homesns.dto.EventMemberDto;
import com.ssafy.homesns.dto.FeedDto;
import com.ssafy.homesns.dto.FeedEmotionDto;
import com.ssafy.homesns.dto.FeedParamDto;
import com.ssafy.homesns.dto.FileDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.HashtagDto;
import com.ssafy.homesns.dto.LocationDto;
import com.ssafy.homesns.dto.LocationFavoriteDto;
import com.ssafy.homesns.dto.MainFeedDto;
import com.ssafy.homesns.dto.MainFileDto;
import com.ssafy.homesns.dto.TimelineDto;
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
	public int noticeFeedDelete(int feedId);
	public int locationFavoriteDelete(LocationFavoriteDto locationFavoriteDto);
	
	
	// 피드 감정표현 추가, 피드를 생성하면서 동시에 모든 값이 0인 피드 감정표현 테이블 생성
	public int feedEmotionCreate(int feedId);
	// 해당 피드의 감정표현 현황 찾기
	public FeedDto feedEmotionSearch(int feedId);
	// 해당 피드에 감정표현을 추가 / 삭제
	//// Good
	public int feedGoodAdd(int feedId);
	public int feedGoodSub(int feedId);
	//// Sad
	public int feedSadAdd(int feedId);
	public int feedSadSub(int feedId);
	//// Check
	public int feedCheckAdd(int feedId);
	public int feedCheckSub(int feedId);
	//// Fun
	public int feedFunAdd(int feedId);
	public int feedFunSub(int feedId);
	//// Amaze
	public int feedAmazeAdd(int feedId);
	public int feedAmazeSub(int feedId);
	// 피드 감정표현 삭제, 피드을 삭제하면서 동시에 피드 감정표현 테이블 삭제
	public int feedEmotionDelete(int commendId);
	
	
	// 피드 감정표현 기록 추가, 피드 감정표현의 수정에 따라 테이블 생성
	public int feedEmotionUserUseCreate(FeedEmotionDto feedEmotionDto);
	// 유저가 피드에 어떤 감정표현을 했는지 찾기 => 결과값이 없으면 아직 감정표현을 하지 않은 것
	public String feedEmotionUserUseSearch(@Param("feedId") int feedId,@Param("userSeq")int userSeq);
	
	// 피드 감정표현 기록 삭제, 피드 감정표현의 수정에 따라 테이블 삭제
	public int feedEmotionUserUseDelete(FeedEmotionDto feedEmotionDto);
	
	// 피드 타임라인 조회
	public List<TimelineDto> feedTimelineSearch(FeedParamDto feedParamDto);
	// userSeq를 사용해서 userProfileImageUrl
	public String userProfileImageUrlSearch(int userSeq);
	// feedLocationId를 사용해서 feedLocation
	public String locationNameSearch(int locationId);
	// 피드 타임라인 변경
	public int feedTimeline(int feedId);
	
	// 피드 스크랩 추가 
	public int feedScrapAdd(@Param("feedId") int feedId,@Param("userSeq")int userSeq);
	// 피드 스크랩 삭제 
	public int feedScrapSub(@Param("feedId") int feedId,@Param("userSeq")int userSeq);
	// 사용자 피드 스크랩 여부
	
	public String feedScrapUserUse(@Param("feedId") int feedId,@Param("userSeq")int userSeq);

}
