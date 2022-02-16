package com.ssafy.homesns.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homesns.dao.FeedDao;
import com.ssafy.homesns.dao.HistoryTravelDao;
import com.ssafy.homesns.dto.HistoryTravelDto;
import com.ssafy.homesns.dto.HistoryTravelResultDto;
import com.ssafy.homesns.dto.LocationDto;
import com.ssafy.homesns.dto.MainFileDto;

@Service
public class HistoryTravelServiceImpl implements HistoryTravelService {

	@Autowired
	HistoryTravelDao historyTravelDao;
	FeedDao feedDao; 
		
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	
	
	@Override
	@Transactional
	public HistoryTravelResultDto groupFeedList(int groupId) {

		
		HistoryTravelResultDto historyTravelResultDto = new HistoryTravelResultDto(); 
		
		try {
			
			List<Integer> feedIdList = historyTravelDao.groupFeedIdList(groupId);
		
			List<HistoryTravelDto> historyTravelDtoList = new ArrayList<>();
		
	
			
			for(int i = 0 ; i < feedIdList.size(); i++) {
				int feedId = feedIdList.get(i);
				LocationDto locationDto = historyTravelDao.locationInfo(feedId);
				List<MainFileDto> fileDtoList = feedDao.mainFileList(feedId);
				
				
				HistoryTravelDto historyTravelDto = new  HistoryTravelDto();
				
				historyTravelDto.setFeedId(feedId);
				historyTravelDto.setLat(locationDto.getLat());
				historyTravelDto.setLng(locationDto.getLng());
				historyTravelDto.setFileDto(fileDtoList);
				
				historyTravelDtoList.add(historyTravelDto);
				}
			
			historyTravelResultDto.setGroupFeedList(historyTravelDtoList);
			historyTravelResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			historyTravelResultDto.setResult(FAIL);
		}
		
		return historyTravelResultDto;
	}



}
