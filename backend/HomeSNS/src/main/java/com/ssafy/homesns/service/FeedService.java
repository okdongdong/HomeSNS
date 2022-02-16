package com.ssafy.homesns.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.homesns.dto.FeedDto;
import com.ssafy.homesns.dto.FeedEmotionDto;
import com.ssafy.homesns.dto.FeedEmotionResultDto;
import com.ssafy.homesns.dto.FeedParamDto;
import com.ssafy.homesns.dto.FeedResultDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.LocationFavoriteDto;
import com.ssafy.homesns.dto.MainFeedResultDto;

public interface FeedService {

	public MainFeedResultDto feedMain(FeedParamDto feedParamDto);
	
	public FeedResultDto feedDetail(int feedId, int userSeq);
	
	public FeedResultDto feedInsert(FeedDto feedDto, MultipartHttpServletRequest request);
	
	public FeedResultDto feedCreateInfo(GroupMemberDto params);
	
	public FeedResultDto feedUpdate(FeedDto feedDto, MultipartHttpServletRequest request);
	
	public FeedResultDto feedDelete(int feedId);
	
	public FeedResultDto locationFavoriteDelete(LocationFavoriteDto locationFavoriteDto);
	
	// 피드 감정 추가
	public FeedEmotionResultDto feedEmotionAdd(FeedEmotionDto feedEmotionDto);
	// 피드 감정 삭제 
	public FeedEmotionResultDto feedEmotionSub(FeedEmotionDto feedEmotionDto);
	
	// 피드 타임라인 변경
	public FeedResultDto feedTimeline(int feedId);
}
