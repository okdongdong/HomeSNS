package com.ssafy.homesns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homesns.dto.GroupDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.GroupMemberResultDto;
import com.ssafy.homesns.dto.GroupResultDto;
import com.ssafy.homesns.service.GroupService;

@CrossOrigin(
		origins = "http://localhost:5500", // npm에서 5500번을 사용한다
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
public class GroupController {

	@Autowired
	GroupService groupService;

	private static final int SUCCESS = 1;


	// 새로운 그룹을 생성하는 동시에 그룹에 참가 => GroupList + GroupMember 동시에 추가
	// 1. 그룹을 생성
	// 2. 해당 그룹의 아이디 찾기
	// 3. 찾은 그룹의 아이디와 자신을 그룹 멤버로 등록
	@PostMapping(value="/group")
	public ResponseEntity<GroupResultDto> groupListCreate(@RequestBody GroupDto groupDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		System.out.println(groupDto);
		// 그룹 비밀번호 암호화
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		groupDto.setGroupPassword(b.encode(groupDto.getGroupPassword()));
		
		// 그룹 리더 추가
		groupDto.setGroupLeaderSeq(userSeq);
		System.out.println(groupDto);

		GroupResultDto groupResultDto = groupService.groupListCreate(groupDto);

		if ( groupResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.OK);
		}  
		return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	// 현재 유저가 들어가 있는 그룹의 리스트를 가져온다
	@GetMapping(value="/group")
	public ResponseEntity<GroupResultDto> groupListSearch() {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		GroupResultDto groupResultDto = groupService.groupListSearch(userSeq);

		if (groupResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 수정의 경우, 그룹의 리더만 할 수 있는가?
	// 수정 권한을 생각해볼것
	// 수정에서는 그룹 이름, 비밀번호를 변경할 수 있다. 프로필 이미지는 다른 메소드를 만들어서 수정해줄것
	@PutMapping(value="/group")
	public ResponseEntity<GroupResultDto> groupListUpdate(@RequestBody GroupDto groupDto) {

		// 그룹 비밀번호 암호화
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		groupDto.setGroupPassword(b.encode(groupDto.getGroupPassword()));
		

		GroupResultDto groupResultDto = groupService.groupListUpdate(groupDto);

		if ( groupResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.OK);
		}  
		return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 그룹을 나가는 것이 아니라 그룹을 삭제하는 동작이어야 한다
	@DeleteMapping(value="/group/{groupId}")
	public ResponseEntity<GroupResultDto> groupListDelete(@PathVariable(value="groupId") int groupId) {
		
		GroupResultDto groupResultDto = groupService.groupListDelete(groupId);

		if ( groupResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GroupResultDto>(groupResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	

	

	// 그룹에 멤버를 추가한다. GroupDto를 사용해서 그룹ID와 비밀번호를 입력받고, 일치한다면 그룹 멤버로 추가한다
	@PostMapping(value="/group/member")
	public ResponseEntity<GroupMemberResultDto> groupMemberCreate(@RequestBody GroupDto groupDto) {
		
		GroupMemberResultDto groupMemberResultDto = groupService.groupMemberCreate(groupDto);

		if ( groupMemberResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<GroupMemberResultDto>(groupMemberResultDto, HttpStatus.OK);
		}  
		return new ResponseEntity<GroupMemberResultDto>(groupMemberResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 그룹에 참여하고 있는 멤버 목록을 불러온다
	@GetMapping(value="/group/member/{groupId}")
	public ResponseEntity<GroupMemberResultDto> groupList(@PathVariable(value="groupId") int groupId) {
		
		GroupMemberResultDto groupMemberResultDto = groupService.groupMemberSearch(groupId);

		if (groupMemberResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<GroupMemberResultDto>(groupMemberResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GroupMemberResultDto>(groupMemberResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 그룹과 유저에 대한 정보를 받아서 해당 유저에게 매니저 권한을 부여한다
	// 매니저 권한을 부여하기 위해서 자신이 우선 그룹리더여야한다
	// 그룹ID, 자신의 ID를 통해서 권한이 있는지 확인 후, 권한이 있다면 특정 유저에게 매니저 권한을 부여한다
	@PutMapping(value="/group/memberManagerAuthApprove")
	public ResponseEntity<GroupMemberResultDto> groupMemberManagerAuthApprove(@RequestBody GroupMemberDto groupMemberDto) {
		
		GroupMemberResultDto groupMemberResultDto = groupService.groupMemberManagerAuthApprove(groupMemberDto);

		if ( groupMemberResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<GroupMemberResultDto>(groupMemberResultDto, HttpStatus.OK);
		}  
		return new ResponseEntity<GroupMemberResultDto>(groupMemberResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value="/group/memberManagerAuthDecline")
	public ResponseEntity<GroupMemberResultDto> groupMemberManagerAuthDecline(@RequestBody GroupMemberDto groupMemberDto) {

		GroupMemberResultDto groupMemberResultDto = groupService.groupMemberManagerAuthDecline(groupMemberDto);
		
		if ( groupMemberResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<GroupMemberResultDto>(groupMemberResultDto, HttpStatus.OK);
		}  
		return new ResponseEntity<GroupMemberResultDto>(groupMemberResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 그룹에서 나간다. 
	// 내가 그룹장이면 현재 그룹원이 그룹장 1명 뿐일때만 나갈 수 있다 => 현재 그룹원의 숫자를 알 수 있어야 하는가?
	// 나가기를 요청할 때, 우선적으로 확인할 수 도 있을 것
	// 현재 그룹장과 요청한 사람이 같다면, 현재 그룹원의 수를 우선 확인할 필요가 있다
	// 이거 하나를 위해서 추가적으로 겹치게 구현될 부분이 많을 것 같다
	// 그룹장이 그룹에서 나가기 위해서는 마이페이지 같은 부분에서 나가는 것도 좋은 방법일 것
	// 프론트에서 그룹장과 현재 유저가 같다면 알아서 기능을 사용 못하도록 해줄 수 있을 것
	// ==> 프론트에서 처리할 일, 그룹장이 그룹을 삭제하는 것은 따로 구현할 수 있을 것 ( 프론트에서 막는다 )
	@DeleteMapping(value="/group/member/{groupId}")
	public ResponseEntity<GroupMemberResultDto> groupMemberDelete(@PathVariable(value="groupId") int groupId) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		GroupMemberDto groupMemberDto = new GroupMemberDto();
		groupMemberDto.setGroupId(groupId);
		groupMemberDto.setUserSeq(userSeq);

		GroupMemberResultDto groupResultDto = groupService.groupMemberDelete(groupMemberDto);

		if ( groupResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GroupMemberResultDto>(groupResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GroupMemberResultDto>(groupResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

