package com.ssafy.homesns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.LocationDto;

@Mapper
public interface HistoryTravelDao {

	public List<Integer> groupFeedIdList(int groupId);
	public LocationDto locationInfo(int feedId);
//	public List<FileDto> groupFileList(int feedId);
}
