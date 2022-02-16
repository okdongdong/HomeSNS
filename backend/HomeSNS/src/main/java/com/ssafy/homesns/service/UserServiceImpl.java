package com.ssafy.homesns.service;

import java.io.File;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.homesns.dao.FeedDao;
import com.ssafy.homesns.dao.UserDao;
import com.ssafy.homesns.dto.FeedParamDto;
import com.ssafy.homesns.dto.MainFeedDto;
import com.ssafy.homesns.dto.MainFeedResultDto;
import com.ssafy.homesns.dto.MainFileDto;
import com.ssafy.homesns.dto.ProfileImageDto;
import com.ssafy.homesns.dto.UserDto;
import com.ssafy.homesns.dto.UserResultDto;

@Service
public class UserServiceImpl implements UserService{

	private final PasswordEncoder passwordEncoder;

	@Autowired
	UserDao userDao;

	@Autowired
	FeedDao feedDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	private static final int NOEXIST = 2;
	
	String uploadFolder = "upload";


	String uploadPath = "/usr" + File.separator + "share" + File.separator + "nginx" + File.separator + "html";



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
			}else {
                ProfileImageDto profileImageDto = new ProfileImageDto();
                profileImageDto.setUserSeq(userSeq);
                profileImageDto.setProfileImageUrl("/img/noimage.png");
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


	// 비밀번호 확인
	@Override
	public UserResultDto passwordCheck(UserDto userDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		
		UserResultDto userResultDto = new UserResultDto();
		
		// 비밀번호 비교
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		if ( b.matches(userDto.getUserPassword(), userDao.userPasswordSearch(userSeq)))	{
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}
	
	@Override
	public UserResultDto userInfoUpdate(UserDto userDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		userDto.setUserSeq(userSeq);
		
		UserResultDto userResultDto = new UserResultDto();
		
		if ( userDao.userInfoUpdate(userDto) == 1 ) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userPasswordUpdate(UserDto userDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		userDto.setUserSeq(userSeq);
		
		UserResultDto userResultDto = new UserResultDto();
		
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		userDto.setUserPassword(b.encode(userDto.getUserPassword()));
		
		if ( userDao.userPasswordUpdate(userDto) == 1 ) {
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

	@Override
	public MainFeedResultDto userFeedSearch(FeedParamDto feedParamDto) {

		MainFeedResultDto mainFeedResultDto = new MainFeedResultDto();

		try {
			List<MainFeedDto> feedList = userDao.userFeedSearch(feedParamDto);

			for (int i = 0; i < feedList.size(); i++) {

				int feedId = feedList.get(i).getFeedId();
				System.out.println("feedId - " + feedId);

				List<MainFileDto> fileList = feedDao.mainFileList(feedId);
				System.out.println("fileList  - " + fileList);

				feedList.get(i).setFileList(fileList);
			}

			mainFeedResultDto.setFeedList(feedList);
			mainFeedResultDto.setResult(SUCCESS);
		} catch ( Exception e ) {
			e.printStackTrace();
			mainFeedResultDto.setResult(FAIL);
		}

		return mainFeedResultDto;
	}

	@Override
	public MainFeedResultDto userScrapSearch(FeedParamDto feedParamDto) {

		MainFeedResultDto mainFeedResultDto = new MainFeedResultDto();
		
		try {
			List<MainFeedDto> feedList = userDao.userScrapSearch(feedParamDto);

			for (int i = 0; i < feedList.size(); i++) {
				
				int feedId = feedList.get(i).getFeedId();
				System.out.println("feedId - " + feedId);

				List<MainFileDto> fileList = feedDao.mainFileList(feedId);
				System.out.println("fileList  - " + fileList);

				feedList.get(i).setFileList(fileList);
			}

			mainFeedResultDto.setFeedList(feedList);
			mainFeedResultDto.setResult(SUCCESS);
		} catch ( Exception e ) {
			e.printStackTrace();
			mainFeedResultDto.setResult(FAIL);
		}

		return mainFeedResultDto;
	}

	@Autowired
	private JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "limjjangguri@gmail.com";
	
	@Override
	public UserResultDto userFindId(String userEmail) {
		
		UserResultDto userResultDto = new UserResultDto();
		
		try {
			String userId = userDao.userFindId(userEmail);
			// userId를 찾았다면,
			if ( userId != null ) {
				// userEmail로 userId에 대한 정보를 전송한다
				SimpleMailMessage message = new SimpleMailMessage();
				
		        message.setTo(userEmail);
		        message.setFrom(FROM_ADDRESS);
		        message.setSubject("[HOME SNS] 요청하신 아이디 찾기 안내입니다");
		        message.setText("ID : " + userId);
		        System.out.println(message);
		        
		        mailSender.send(message);
		        
				userResultDto.setResult(SUCCESS);
			} else {
				System.out.println("해당 이메일로 가입한 아이디가 존재하지 않습니다.");
				userResultDto.setResult(NOEXIST);
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
			System.out.println("에러가 발생했습니다.");
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}

	@Override
	public UserResultDto userFindPw(UserDto userDto) {
		
		UserResultDto userResultDto = new UserResultDto();
		System.out.println(userDto);
		try {
			Integer userSeq = userDao.userFindUserSeq(userDto);
			// userSeq를 찾지 못했다면, FAIL or 다른 값을 줘서 구분할 수 있도록 할 것
			if ( userSeq == null ) userResultDto.setResult(NOEXIST);
			
			// userSeq를 찾았다면, 비밀번호를 난수로 생성한다
			String tempPassword = getRandomPassword(50);
			System.out.println(tempPassword);
			
			// 생성한 비밀번호를 암호화 해서 DB에 저장한다
			BCryptPasswordEncoder b = new BCryptPasswordEncoder();
			userDto.setUserPassword(b.encode(tempPassword));
			System.out.println(userDto.getUserPassword());
			userDto.setUserSeq(userSeq);
			userDao.userChangePassword(userDto);
			
			
			// userEmail로 생성한 비밀번호를 전송한다
			SimpleMailMessage message = new SimpleMailMessage();
			
	        message.setTo(userDto.getUserEmail());
	        message.setFrom(FROM_ADDRESS);
	        message.setSubject("[HOME SNS] 요청하신 비밀번호 찾기 안내입니다");
	        message.setText("임시 비밀번호 : " + tempPassword);
	        System.out.println(message);
	        
	        mailSender.send(message);
			
			userResultDto.setResult(SUCCESS);
		} catch( Exception e ) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}

		return userResultDto;
	}

	private static String getRandomPassword(int passwordLength) {
		char[] charSet = new char[] { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
				'!', '@', '#', '$', '%', '^', '&' };
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());
		
		int idx = 0;
		int len = charSet.length;
		for (int i = 0; i < passwordLength; i++) {
			idx = sr.nextInt(len);
			sb.append(charSet[idx]);
		}
		
		return sb.toString();
	}

	@Override
	public UserResultDto userPage(int userSeq) {
		
		UserResultDto userResultDto = new UserResultDto();

		try {
			UserDto userDto =  userDao.userPage(userSeq);
			
			userResultDto.setUserDto(userDto);			
			userResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

}



