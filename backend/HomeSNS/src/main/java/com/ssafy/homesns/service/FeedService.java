package com.ssafy.homesns.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.homesns.dto.FeedDto;
import com.ssafy.homesns.dto.FeedResultDto;

public interface FeedService {

	public FeedResultDto feedMainPage(int groupId);
	
	public FeedResultDto feedDetail(int feedId);
	
	public FeedResultDto feedInsert(FeedDto feedDto, MultipartHttpServletRequest request);
	
	public FeedResultDto feedUpdate(FeedDto feedDto, MultipartHttpServletRequest request);
}
