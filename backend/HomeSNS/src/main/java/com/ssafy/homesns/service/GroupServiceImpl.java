package com.ssafy.homesns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homesns.dao.GroupDao;
import com.ssafy.homesns.dto.GroupDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.GroupResultDto;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	GroupDao groupDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	@Transactional
	public GroupResultDto groupList(int userSeq) {
		
		GroupResultDto groupResultDto = new GroupResultDto();
		
		try {
			List<GroupDto> list = groupDao.groupList(userSeq);
			int count = groupDao.groupListTotalCount();
			
			groupResultDto.setGroupDtoList(list);
			groupResultDto.setCount(count);
			groupResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			groupResultDto.setResult(FAIL);
		}
		
		return groupResultDto;
	}
	
	@Override
	@Transactional
	public GroupResultDto groupCreate(GroupDto groupDto) {
		
		GroupResultDto groupResultDto = new GroupResultDto();
		
		if ( groupDao.groupCreate(groupDto) == 1 ) {
			groupResultDto.setResult(SUCCESS);
		} else {
			groupResultDto.setResult(FAIL);
		}
		return groupResultDto;
	}

	@Override
	public GroupResultDto groupDelete(GroupMemberDto groupMemberDto) {

		GroupResultDto groupResultDto = new GroupResultDto();
		
		if ( groupDao.groupDelete(groupMemberDto) == 1) {
			groupResultDto.setResult(SUCCESS);
		} else {
			groupResultDto.setResult(FAIL);
		}
		
		return groupResultDto;
	}

}
