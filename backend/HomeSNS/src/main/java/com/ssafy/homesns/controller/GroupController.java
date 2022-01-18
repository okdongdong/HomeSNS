package com.ssafy.homesns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homesns.dto.GroupDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.GroupResultDto;
import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.service.GroupService;

@RestController
public class GroupController {
	
	@Autowired
	GroupService groupService;
	
	private static final int SUCCESS = 1;
	
	// 현재 유저가 들어가 있는 그룹의 리스트를 가져온다
	@GetMapping(value="/group")
	public ResponseEntity<GroupResultDto> groupList(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		GroupResultDto groupResultDto = groupService.groupList(userDto.getUserSeq());
		
		if (groupResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 새로운 그룹을 생성하는 동시에 그룹에 참가한다
	// 동시에 group member 에 추가되어야 한다는 것도 추가해야할 것
	@PostMapping(value="/group")
	public ResponseEntity<GroupResultDto> groupCreate(GroupDto groupDto, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		groupDto.setGroupLeaderSeq(userDto.getUserSeq());
		
		GroupResultDto groupResultDto = groupService.groupCreate(groupDto);
		
		if ( groupResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.OK);
		}  
		return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 그룹에서 나간다. 
	// 내가 그룹장이면 현재 그룹원이 그룹장 1명 뿐일때만 나갈 수 있다 => 현재 그룹원의 숫자를 알 수 있어야 하는가?
	// 나가기를 요청할 때, 우선적으로 확인할 수 도 있을 것
	// 현재 그룹장과 요청한 사람이 같다면, 현재 그룹원의 수를 우선 확인할 필요가 있다
	// 이거 하나를 위해서 추가적으로 겹치게 구현될 부분이 많을 것 같다
	// 그룹장이 그룹에서 나가기 위해서는 마이페이지 같은 부분에서 나가는 것도 좋은 방법일 것
	// 프론트에서 그룹장과 현재 유저가 같다면 알아서 기능을 못쓰도록 막아줘야 할 것
	@DeleteMapping(value="/group/{groupId}")
	public ResponseEntity<GroupResultDto> groupDelete(@PathVariable(value="groupId") int groupId, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		GroupMemberDto groupMemberDto = new GroupMemberDto();
		groupMemberDto.setGroupId(groupId);
		groupMemberDto.setUserSeq(userDto.getUserSeq());
		
		GroupResultDto groupResultDto = groupService.groupDelete(groupMemberDto);
		
		if ( groupResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}





