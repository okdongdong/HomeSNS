package com.ssafy.homesns.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.homesns.dao.GroupDao;
import com.ssafy.homesns.dto.GroupDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.GroupMemberResultDto;
import com.ssafy.homesns.dto.GroupResultDto;
import com.ssafy.homesns.dto.ProfileImageDto;
import com.ssafy.homesns.dto.UserDto;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	GroupDao groupDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	String uploadFolder = "upload";
	
	/* for production code */
	//uploadPath = getServletContext().getRealPath("/");
	// F:\SSAFY\ssafy5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BoardWebFileUpload\

	/* for eclipse development code */
	String uploadPath = "/Users" + File.separator + "sac" + File.separator + "ssafy" + File.separator + "2nd";

	@Override
	@Transactional
	public GroupResultDto groupListCreate(GroupDto groupDto, MultipartHttpServletRequest request) {

		GroupResultDto groupResultDto = new GroupResultDto();

		// 그룹을 생성했다면,
		try {
			if ( groupDao.groupListCreate(groupDto) == 1 ) {
				// 생성된 그룹의 그룹ID를 찾아서 그룹 멤버로 추가한다
				GroupMemberDto groupMemberDto = new GroupMemberDto();
				// DB에 다시 접근하지 않고 데이터를 가져올 수 있는가?
				// groupMemberDto.setGroupId(groupDao.groupListRecentMake(groupDto.getGroupLeaderSeq()));
				groupMemberDto.setGroupId(groupDto.getGroupId());
				groupMemberDto.setUserSeq(groupDto.getGroupLeaderSeq());
				groupMemberDto.setManagerYn("Y");
				groupDao.groupMemberCreate(groupMemberDto);


				// group profile image insert
				MultipartFile userProfileImage = request.getFile("profileImage");
				if( userProfileImage != null ) {

					File uploadDir = new File(uploadPath + File.separator + uploadFolder);
					if (!uploadDir.exists()) uploadDir.mkdir();

					String profileImageName = userProfileImage.getOriginalFilename();

					//Random File Id
					UUID uuid = UUID.randomUUID();

					//file extension
					String extension = FilenameUtils.getExtension(profileImageName);

					String savingFileName = uuid + "." + extension;

					File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

					System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
					userProfileImage.transferTo(destFile);

					// Profile Image Insert
					ProfileImageDto profileImageDto = new ProfileImageDto();
					profileImageDto.setGroupId(groupDto.getGroupId());
					profileImageDto.setProfileImageName(profileImageName);
					profileImageDto.setProfileImageSize(userProfileImage.getSize());
					String profileImageUrl = uploadFolder + "/" + savingFileName;
					profileImageDto.setProfileImageUrl(profileImageUrl);
						
					// Profile Image 추가
					groupDao.groupProfileImageInsert(profileImageDto);
				}

				groupResultDto.setResult(SUCCESS);
			} else {
				groupResultDto.setResult(FAIL);
			}
		} catch(Exception e) {
			e.printStackTrace();
			groupResultDto.setResult(FAIL);
		}

		return groupResultDto;
	}


	@Override
	@Transactional
	public GroupResultDto groupProfileImageUpdate(int groupId, MultipartHttpServletRequest request) {

		GroupResultDto groupResultDto = new GroupResultDto();
		
		try {
			
			MultipartFile groupProfileImage = request.getFile("profileImage");
			if ( groupProfileImage != null ) {
				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
				if (!uploadDir.exists()) uploadDir.mkdir();

				String profileImageName = groupProfileImage.getOriginalFilename();

				//Random File Id
				UUID uuid = UUID.randomUUID();

				//file extension
				String extension = FilenameUtils.getExtension(profileImageName);

				String savingFileName = uuid + "." + extension;

				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				groupProfileImage.transferTo(destFile);
				
				ProfileImageDto profileImageDto = new ProfileImageDto();
				profileImageDto.setGroupId(groupId);
				profileImageDto.setProfileImageName(profileImageName);
				profileImageDto.setProfileImageSize(groupProfileImage.getSize());
				String profileImageUrl = uploadFolder + "/" + savingFileName;
				profileImageDto.setProfileImageUrl(profileImageUrl);
				
				// Profile Image 추가
				groupDao.groupProfileImageInsert(profileImageDto);
				
				// Group 테이블의 ProfileImageUrl 수정
				groupDao.groupProfileImageUpdate(profileImageDto);
			}
			
			groupResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			groupResultDto.setResult(FAIL);
		}
		
		return groupResultDto;
	}

	
	@Override
	@Transactional
	public GroupResultDto groupListSearch(int userSeq) {

		GroupResultDto groupResultDto = new GroupResultDto();

		try {
			List<GroupDto> list = groupDao.groupListSearch(userSeq);
			int count = groupDao.groupListTotalCount(userSeq);

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
	public GroupResultDto groupListUpdate(GroupDto groupDto) {

		GroupResultDto groupResultDto = new GroupResultDto();

		if ( groupDao.groupListUpdate(groupDto) == 1 ) {
			groupResultDto.setResult(SUCCESS);
		} else {
			groupResultDto.setResult(FAIL);
		}
		return groupResultDto;
	}

	// 그룹 삭제
	// 1. 해당 그룹의 리더가 자신인지 확인
	// 2. 그룹의 인원이 1명인지 확인
	// 3. 그룹 삭제
	@Override
	@Transactional
	public GroupResultDto groupListDelete(int groupId) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		GroupResultDto groupResultDto = new GroupResultDto();

		// 해당 그룹의 리더가 자신이면,
		if ( groupDao.groupListLeaderSearch(groupId) == userSeq ) {
			// 그룹의 총 인원수를 확인하고,
			if ( groupDao.groupMemberTotalCount(groupId) == 1 ) {
				// 그룹에 자신을 제외하고 아무도 없다면, 그룹 삭제
				groupDao.groupListDelete(groupId);
				groupResultDto.setResult(SUCCESS);
			}
			else {
				groupResultDto.setResult(FAIL);
			}

		} else {
			groupResultDto.setResult(FAIL);
		}

		return groupResultDto;
	}




	// GroupDto를 통해 PW를 비교하여, 일치하면 그룹 멤버로 추가한다
	@Override
	public GroupMemberResultDto groupMemberCreate(GroupDto groupDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		GroupMemberResultDto groupMemberResultDto = new GroupMemberResultDto();

		// 비밀번호 비교
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		if ( b.matches(groupDto.getGroupPassword(), groupDao.groupPasswordCheck(groupDto.getGroupId())) ) {
			// 비밀번호가 맞다면, 그룹멤버에 해당 유저를 추가한다
			// 제일 처음에는 매니저 권한이 없다
			GroupMemberDto groupMemberDto = new GroupMemberDto();
			groupMemberDto.setGroupId(groupDto.getGroupId());
			groupMemberDto.setUserSeq(userSeq);
			groupMemberDto.setManagerYn("N");

			if ( groupDao.groupMemberCreate(groupMemberDto) == 1 ) {
				groupMemberResultDto.setResult(SUCCESS);
			}
			else {
				System.out.println("groupMemberCreate FAIL");
				groupMemberResultDto.setResult(FAIL);
			}

		} else {
			System.out.println("groupPasswordCheck FAIL");
			groupMemberResultDto.setResult(FAIL);
		}

		return groupMemberResultDto;
	}

	@Override
	@Transactional
	public GroupMemberResultDto groupMemberSearch(int groupId) {

		GroupMemberResultDto groupMemberResultDto = new GroupMemberResultDto();

		try {
			List<UserDto> userDtoList = groupDao.groupMemberListSearch(groupId);
			int count = groupDao.groupMemberTotalCount(groupId);

			groupMemberResultDto.setUserDtoList(userDtoList);
			groupMemberResultDto.setCount(count);
			groupMemberResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			groupMemberResultDto.setResult(FAIL);
		}

		return groupMemberResultDto;
	}

	// 그룹의 리더라면 매니저 권한 승인 허가
	@Override
	public GroupMemberResultDto groupMemberManagerAuthApprove(GroupMemberDto groupMemberDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		GroupMemberResultDto groupMemberResultDto = new GroupMemberResultDto();

		// 그룹의 리더가 맞다면,
		if ( groupDao.groupLeaderSearch(groupMemberDto.getGroupId()) == userSeq ) {
			// 매니저 권한을 부여한다
			groupDao.groupMemberManagerAuthApprove(groupMemberDto.getUserSeq());
			groupMemberResultDto.setResult(SUCCESS);
		} else {
			groupMemberResultDto.setResult(FAIL);
		}
		return groupMemberResultDto;
	}

	// 그룹의 리더라면 매니저 권한 거부 허가
	@Override
	public GroupMemberResultDto groupMemberManagerAuthDecline(GroupMemberDto groupMemberDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		GroupMemberResultDto groupMemberResultDto = new GroupMemberResultDto();

		// 그룹의 리더가 맞다면,
		if ( groupDao.groupLeaderSearch(groupMemberDto.getGroupId()) == userSeq ) {
			// 매니저 권한을 제거한다
			groupDao.groupMemberManagerAuthDecline(groupMemberDto.getUserSeq());
			groupMemberResultDto.setResult(SUCCESS);
		} else {
			groupMemberResultDto.setResult(FAIL);
		}
		return groupMemberResultDto;
	}

	// 그룹 나가기
	@Override
	public GroupMemberResultDto groupMemberDelete(GroupMemberDto groupMemberDto) {

		GroupMemberResultDto groupMemberResultDto = new GroupMemberResultDto();

		if ( groupDao.groupMemberDelete(groupMemberDto) == 1) {
			groupMemberResultDto.setResult(SUCCESS);
		} else {
			groupMemberResultDto.setResult(FAIL);
		}

		return groupMemberResultDto;
	}

}
