package com.ssafy.homesns.service;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.homesns.dao.UserDao;
import com.ssafy.homesns.dto.FileDto;
import com.ssafy.homesns.dto.ProfileImageDto;
import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.dto.UserResultDto;

@Service
public class UserServiceImpl implements UserService{

	private final PasswordEncoder passwordEncoder;

	@Autowired
	UserDao userDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	String uploadFolder = "upload";


	/* for production code */
	//uploadPath = getServletContext().getRealPath("/");
	// F:\SSAFY\ssafy5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BoardWebFileUpload\

	/* for eclipse development code */
	String uploadPath = "/Users" + File.separator + "sac" + File.separator + "ssafy" + File.separator + "2nd";



	public UserServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	// userDao의 userRegister를 사용해서 DB에 insert한다
	// 성공하면 Result를 SUCCESS로 set하고, 실패하면 Result를 FAIL로 set한다
	@Override
	@Transactional
	public UserResultDto userRegister(UserDto userDto, MultipartHttpServletRequest request) {

		UserResultDto userResultDto = new UserResultDto();
		userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));

		try {
			// user table 추가
			userDao.userRegister(userDto);
			
			// profile image table 추가
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
				profileImageDto.setUserSeq(userDto.getUserSeq());
				profileImageDto.setProfileImageName(profileImageName);
				profileImageDto.setProfileImageSize(userProfileImage.getSize());
				String profileImageUrl = uploadFolder + "/" + savingFileName;
				profileImageDto.setProfileImageUrl(profileImageUrl);
				
				// Profile Image 추가
				userDao.userProfileImageInsert(profileImageDto);
			}

			userResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}

		return userResultDto;
	}

	@Override
	public UserResultDto profileImageUpdate( MultipartHttpServletRequest request ) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		
		UserResultDto userResultDto = new UserResultDto();
		
		try {
			// profile image table 추가
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
				profileImageDto.setUserSeq(userSeq);
				profileImageDto.setProfileImageName(profileImageName);
				profileImageDto.setProfileImageSize(userProfileImage.getSize());
				String profileImageUrl = uploadFolder + "/" + savingFileName;
				profileImageDto.setProfileImageUrl(profileImageUrl);
				
				// Profile Image 추가
				userDao.userProfileImageInsert(profileImageDto);
				
				// User테이블의 ProfileImageUrl 수정
				userDao.userProfileImageUpdate(profileImageDto);
			}

			userResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}

		return userResultDto;
	}

	
	
	@Override
	public UserResultDto userMypageSearch() {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		
		UserResultDto userResultDto = new UserResultDto();
		
		try {
			userResultDto.setUserDto(userDao.userMypageSearch(userSeq));
			userResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}
	
	
	@Override
	public UserResultDto userUpdate(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if ( userDao.userUpdate(userDto) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userDelete(int userSeq) {
		UserResultDto userResultDto = new UserResultDto();
		if ( userDao.userDelete(userSeq) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userExist(String userPhone){
		UserResultDto userResultDto = new UserResultDto();

		try {
			UserDto userDto =  userDao.userExist(userPhone);  
			userResultDto.setUserDto(userDto);			
			userResultDto.setResult(SUCCESS);

		}catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);

		}
		return userResultDto;



	}

	@Override
	public UserResultDto checkUserId(String userId) {
		UserResultDto userResultDto = new UserResultDto();
		if ( userDao.checkUserId(userId) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;

	}

	@Override
	public UserResultDto checkUserEmail(String userEmail) {
		UserResultDto userResultDto = new UserResultDto();
		if ( userDao.checkUserEmail(userEmail) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto checkUserPhone(String userPhone) {
		UserResultDto userResultDto = new UserResultDto();
		if ( userDao.checkUserPhone(userPhone) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

}
