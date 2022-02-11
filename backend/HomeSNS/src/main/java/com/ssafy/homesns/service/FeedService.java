package com.ssafy.homesns.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.homesns.dto.FeedDto;
import com.ssafy.homesns.dto.FeedParamDto;
import com.ssafy.homesns.dto.FeedResultDto;
import com.ssafy.homesns.dto.GroupMemberDto;

import com.ssafy.homesns.dto.MainFeedResultDto;

public interface FeedService {

	public MainFeedResultDto feedMain(FeedParamDto feedParamDto);
	
	public FeedResultDto feedDetail(int feedId);
	
	public FeedResultDto feedInsert(FeedDto feedDto, MultipartHttpServletRequest request);
	
	public FeedResultDto feedCreateInfo(GroupMemberDto params);
	
	public FeedResultDto feedUpdate(FeedDto feedDto, MultipartHttpServletRequest request);
	
	public FeedResultDto feedDelete(int feedId);
}
