package com.ssafy.homesns.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.homesns.dao.FeedDao;
import com.ssafy.homesns.dto.CommentDto;
import com.ssafy.homesns.dto.EventMemberDto;
import com.ssafy.homesns.dto.FeedDto;
import com.ssafy.homesns.dto.FeedParamDto;
import com.ssafy.homesns.dto.FeedResultDto;
import com.ssafy.homesns.dto.FileDto;
import com.ssafy.homesns.dto.GroupMemberDto;
import com.ssafy.homesns.dto.HashtagDto;
import com.ssafy.homesns.dto.LocationDto;
import com.ssafy.homesns.dto.LocationFavoriteDto;
import com.ssafy.homesns.dto.MainFeedDto;
import com.ssafy.homesns.dto.MainFeedResultDto;
import com.ssafy.homesns.dto.MainFileDto;
import com.ssafy.homesns.dto.UserDto;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	FeedDao feedDao;

	String uploadFolder = "upload";



	// 경로는 수정해야함



	/* for production code */
	//uploadPath = getServletContext().getRealPath("/");
	// F:\SSAFY\ssafy5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BoardWebFileUpload\

	/* for eclipse development code */
	String uploadPath = "/Users" + File.separator + "sac" + File.separator + "ssafy" + File.separator + "2nd";




	private static final int SUCCESS = 1;
	private static final int FAIL = -1;


	// feedDao의 feedMainPage를 사용해 DB에서 메인페이지에 사용될feed를 가져온다.
	// 성공하면 Result를 SUCCESS로 set하고, 실패하면 Result를 FAIL로 set한다

	// feedParamDto로 해야겠다.
	// 태현이가 만든 피드메인 페이지 가지고오기
	@Override
	@Transactional
	public MainFeedResultDto feedMain(FeedParamDto feedParamDto) {

		MainFeedResultDto mainFeedResultDto = new MainFeedResultDto();


		try {

			List<MainFeedDto> feedList = feedDao.feedMain(feedParamDto);

			// 5개만가져온다치면 들고온 게시물만 id받아와서 그걸로 다시 db 들려서 댓글, 해시태그, 사진 가져오는 방법

			for(int i = 0 ; i < feedList.size(); i++) {

				int feedId = feedList.get(i).getFeedId();
				System.out.println("feedId - " +feedId);
				List<MainFileDto> fileList = feedDao.mainFileList(feedId);

				System.out.println("fileList  - " +fileList);
//				List<HashtagDto> hashtagList = feedDao.hashtagList(feedId);
//				List<UserDto> userList = feedDao.eventMemberList(feedId);
//				LocationDto locationDto = feedDao.locationSearch(feedId);

				//댓글은 상세보기에서만있으면 되지 않을까??
				//				List<CommentDto> commentList = feedDao.commentList(feedId);
				//				feedList.get(i).setCommentList(commentList);
				feedList.get(i).setFileList(fileList);
//				feedList.get(i).setHashtagList(hashtagList);
//				feedList.get(i).setUserList(userList);
//				feedList.get(i).setLocationDto(locationDto);
			}

			mainFeedResultDto.setFeedList(feedList);
			mainFeedResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			mainFeedResultDto.setResult(FAIL);
		}

		return mainFeedResultDto;


	}




	// Feed 상세보기 페이지
	// feedId를 이용하여 해당feed를 가져온다.
	// 성공하면 Result를 SUCCESS로 set하고, 실패하면 Result를 FAIL로 set한다
	@Override
	public FeedResultDto feedDetail(int feedId) {

		FeedResultDto feedResultDto = new FeedResultDto(); 
		FeedDto feedDto = new FeedDto();

		try {
			// 파일,댓글,해시태그 리스트들 Dao호출
			List<FileDto> fileList= feedDao.fileList(feedId);
			List<CommentDto> commentList = feedDao.commentList(feedId);
			List<HashtagDto> hashtagList = feedDao.hashtagList(feedId);
			List<UserDto> userList = feedDao.eventMemberList(feedId);
			LocationDto locationDto = feedDao.locationSearch(feedId);

			// feed 정보 호출
			feedDto = feedDao.feedDetail(feedId);

			// feedDto에 담기
			feedDto.setFileList(fileList);
			feedDto.setCommentList(commentList);
			feedDto.setHashtagList(hashtagList);
			feedDto.setUserList(userList);
			feedDto.setLocationDto(locationDto);

			// feedDto를 feedResultDto에 담아서 전달
			feedResultDto.setFeedDto(feedDto);
			feedResultDto.setResult(SUCCESS);
		}catch(Exception e ) {
			e.printStackTrace();
			feedResultDto.setResult(FAIL);
		}
		return feedResultDto;
	}



	// feed 추가
	// hashtag List추가 
	// file List 추가
	@Override
	@Transactional
	public FeedResultDto feedInsert(FeedDto feedDto, MultipartHttpServletRequest request) {

		FeedResultDto feedResultDto = new FeedResultDto();

		try {

			// 장소 추가
			String locationStr = feedDto.getFeedLocationStr();
			if(locationStr != null) {
				ObjectMapper objectMapper = new ObjectMapper(); 
				LocationDto locationDto = objectMapper.readValue(locationStr, LocationDto.class);
				locationDto.setGroupId(feedDto.getGroupId());
				feedDao.feedLocationInsert(locationDto);
				
				int locationId = locationDto.getLocationId();
				
				// 장소 즐겨찾기 추가
				if ( locationDto.getFavorite() ) {
					LocationFavoriteDto locationFavoriteDto = new LocationFavoriteDto();
					locationFavoriteDto.setLocationId(locationId);
					locationFavoriteDto.setUserSeq(feedDto.getFeedAuthorSeq());
					feedDao.feedLocationFavoriteInsert(locationFavoriteDto);
				}
				
				feedDto.setFeedLocationId(locationId);
				//feed table 추가
				feedDao.feedInsert(feedDto);
				
			}
			else {
				//feed table 추가
				feedDao.feedInsert(feedDto);				
			}
			
			int feedId = feedDto.getFeedId();

			// hashtag 추가 
			String hashtagStr = feedDto.getFeedHashtags();
			if(hashtagStr !=null) {				

				//JSON포맷의  String hashtag를 자동으로 JSON으로 변환시킨후 HashtagDto에 Mapping 
				ObjectMapper objectMapper = new ObjectMapper(); 
				List<HashtagDto> hashtagList = objectMapper.readValue(hashtagStr, new TypeReference<List<HashtagDto>>() {});

				int len = hashtagList.size();
				for(int i = 0 ; i < len ; i++) {
					hashtagList.get(i).setFeedId(feedId);
					feedDao.feedHashtagInsert(hashtagList.get(i));
				}				
			}


	
			// 참가 인원 추가
			String attendeeStr = feedDto.getFeedAttendees();

			if(attendeeStr != null) {
				ObjectMapper objectMapper = new ObjectMapper(); 
				List<EventMemberDto> attendeeList = objectMapper.readValue(attendeeStr, new TypeReference<List<EventMemberDto>>() {});
			
				int len = attendeeList.size();
				for(int i = 0 ; i < len ; i++) {
					attendeeList.get(i).setFeedId(feedId);
					feedDao.feedEventMemberInsert(attendeeList.get(i));
				}		
				
			}


			List<MultipartFile> fileList = request.getFiles("file");
			if(fileList != null) {
				
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) uploadDir.mkdir();


			for (MultipartFile part : fileList) {


				String fileName = part.getOriginalFilename();

				//Random File Id
				UUID uuid = UUID.randomUUID();

				//file extension
				String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()

				String savingFileName = uuid + "." + extension;

				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				part.transferTo(destFile);

				// Table Insert
				FileDto fileDto = new FileDto();
				fileDto.setFeedId(feedId);
				fileDto.setFileName(fileName);
				fileDto.setFileSize(part.getSize());
				fileDto.setFileContentType(part.getContentType());
				String fileUrl = uploadFolder + "/" + savingFileName;
				fileDto.setFileUrl(fileUrl);

				//file 추가 
				feedDao.feedFileInsert(fileDto);
				}
			}

			
			System.out.println("Service feedDto --------- ");
			System.out.println(feedDto);
			feedResultDto.setResult(SUCCESS);
			feedResultDto.setFeedId(feedId);

		}catch(IOException e) {
			e.printStackTrace();
			feedResultDto.setResult(FAIL);
			
		}
		return feedResultDto;
	}

	
	
	@Override
	public FeedResultDto feedCreateInfo(GroupMemberDto params) {
		
		FeedResultDto feedResultDto = new FeedResultDto(); 
		
		// 해당유저에 해당하는 장소 담기 (즐겨찾기 & 그룹 )
		// locationName , lng, lat, favorite만
		List<LocationDto> userLocationList = feedDao.userLocationList(params);
		
		// userName하고 userSeq만 필요
		List<GroupMemberDto> memberList = feedDao.groupMemberList(params.getGroupId()); 
		
		
		feedResultDto.setLocations(userLocationList);
		feedResultDto.setMembers(memberList);
		feedResultDto.setResult(SUCCESS);
		
		return feedResultDto;
		
	}
	
	
	
	
	
	// file관련해서는 모두 delete -> insert 
	// 나머지 feed데이터 관련해서는 update 하는 로직으로 구성
	@Override
	public FeedResultDto feedUpdate(FeedDto feedDto, MultipartHttpServletRequest request) {

		FeedResultDto feedResultDto = new FeedResultDto();

		int feedId = feedDto.getFeedId();
		try {
			
			
			String locationStr = feedDto.getFeedLocationStr();
			if(locationStr != null) {
				ObjectMapper objectMapper = new ObjectMapper(); 
				LocationDto locationDto = objectMapper.readValue(locationStr, LocationDto.class);
				locationDto.setGroupId(feedDto.getGroupId());
				
				int locationId = feedDto.getFeedLocationId();
				locationDto.setLocationId(locationId);
				
				// 있던 장소 수정한거면 원래 값이 있고 
				// 새로 만들었으면 값이 없음 -> 0으로 받기로함
				if( locationId != 0 ) { // 있던장소 수정 
					feedDao.feedLocationUpdate(locationDto);
					if( locationDto.getFavorite() ) { // 즐겨찾기 추가
						LocationFavoriteDto locationFavoriteDto = new LocationFavoriteDto();
						locationFavoriteDto.setLocationId(locationId);
						locationFavoriteDto.setUserSeq(feedDto.getFeedAuthorSeq());
						feedDao.feedLocationFavoriteInsert(locationFavoriteDto);
					}
					
				}else {// 기존에 없던 장소 추가
					
					feedDao.feedLocationInsert(locationDto);
					locationId = locationDto.getLocationId();
					if( locationDto.getFavorite() ) { // 즐겨찾기 추가
						LocationFavoriteDto locationFavoriteDto = new LocationFavoriteDto();
						locationFavoriteDto.setLocationId(locationId);
						locationFavoriteDto.setUserSeq(feedDto.getFeedAuthorSeq());
						feedDao.feedLocationFavoriteInsert(locationFavoriteDto);
					}
					feedDto.setFeedLocationId(locationId);
				}
				
				// 즐겨찾기 삭제는 버튼 누를때 바로 적용하는 걸로..
				
				
				
				
				// 장소 즐겨찾기 추가
				// 원래 추가 안되었었던거면 update문이 아니라 insert 써야 하는데...
				
				
			}
			//feed table 추가
			feedDao.feedUpdate(feedDto);				
			

			// hashtag 추가 
			String hashtagStr = feedDto.getFeedHashtags();
			if(hashtagStr !=null) {				

				//JSON포맷의  String hashtag를 자동으로 JSON으로 변환시킨후 HashtagDto에 Mapping 
				ObjectMapper objectMapper = new ObjectMapper(); 
				List<HashtagDto> hashtagList = objectMapper.readValue(hashtagStr, new TypeReference<List<HashtagDto>>() {});

				// feedId에 해당하는 hashtag가 여러 행이니까 update문 못씀..
				// hashtag 다 지우고 다시 insert 
				feedDao.feedHashtagDelete(feedId);
				
				int len = hashtagList.size();
				for(int i = 0 ; i < len ; i++) {
					hashtagList.get(i).setFeedId(feedId);
					feedDao.feedHashtagInsert(hashtagList.get(i));
				}				
			}


	
			// 참가 인원 추가
			String attendeeStr = feedDto.getFeedAttendees();

			if(attendeeStr != null) {
				ObjectMapper objectMapper = new ObjectMapper(); 
				List<EventMemberDto> attendeeList = objectMapper.readValue(attendeeStr, new TypeReference<List<EventMemberDto>>() {});

				// feedId에 해당하는 참석자가 여러 행이니까 update문 못씀..
				// eventMember 다 지우고 다시 insert
				feedDao.feedEventMemberDelete(feedId);
				int len = attendeeList.size();
				for(int i = 0 ; i < len ; i++) {
					attendeeList.get(i).setFeedId(feedId);
					feedDao.feedEventMemberInsert(attendeeList.get(i));
				}		
				
			}

			List<MultipartFile> fileList = request.getFiles("file");

			//file delete 물리 data 
			List<String> fileUrlList = feedDao.feedFileUrlDeleteList(feedDto.getFeedId());	
			for(String fileUrl : fileUrlList) {	
				File file = new File(uploadPath + File.separator, fileUrl);
				if(file.exists()) {
					file.delete();
				}
			}
			// file delete DB
			feedDao.feedFileDelete(feedDto.getFeedId()); 


			// file insert
			for (MultipartFile part : fileList) {

				String fileName = part.getOriginalFilename();

				//Random File Id
				UUID uuid = UUID.randomUUID();

				//file extension
				String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()

				String savingFileName = uuid + "." + extension;

				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				part.transferTo(destFile);

				// Table Insert
				FileDto fileDto = new FileDto();
				fileDto.setFeedId(feedId);
				fileDto.setFileName(fileName);
				fileDto.setFileSize(part.getSize());
				fileDto.setFileContentType(part.getContentType());
				String fileUrl = uploadFolder + "/" + savingFileName;
				fileDto.setFileUrl(fileUrl);

				//file DB추가 
				feedDao.feedFileInsert(fileDto);
			}

			feedResultDto.setResult(SUCCESS);

		}catch(IOException e) {
			e.printStackTrace();
			feedResultDto.setResult(FAIL);
		}

		return feedResultDto;
	}

	@Override
	@Transactional
	public FeedResultDto feedDelete(int feedId) {

		FeedResultDto feedResultDto = new FeedResultDto();

		// file찾아서 지우기 
		List<String> fileUrlList = feedDao.feedFileUrlDeleteList(feedId);	
		for(String fileUrl : fileUrlList) {	
			File file = new File(uploadPath + File.separator, fileUrl);
			if(file.exists()) {
				file.delete();
			}
		}

		// feed지우면 file,hashtag,comment등등 fk 다 지워짐
		if ( feedDao.feedDelete(feedId) == 1 ) {
			feedResultDto.setResult(SUCCESS);
		} else {
			feedResultDto.setResult(FAIL);
		}


		return feedResultDto;
	}


}
