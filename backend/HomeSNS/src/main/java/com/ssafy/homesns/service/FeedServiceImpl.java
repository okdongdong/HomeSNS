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

import com.ssafy.homesns.dao.FeedDao;
import com.ssafy.homesns.dto.CommentDto;
import com.ssafy.homesns.dto.EventMemberDto;
import com.ssafy.homesns.dto.FeedDto;
import com.ssafy.homesns.dto.FeedResultDto;
import com.ssafy.homesns.dto.FileDto;
import com.ssafy.homesns.dto.HashtagDto;
import com.ssafy.homesns.dto.LocationDto;
import com.ssafy.homesns.dto.LocationFavoriteDto;
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
	@Override
	public FeedResultDto feedMainPage(int groupId) {


		FeedResultDto feedResultDto = new FeedResultDto();


		try {

			List<FeedDto> feedList = feedDao.feedMainPage(groupId);


			// 5개만가져온다치면 들고온 게시물만 id받아와서 그걸로 다시 db 들려서 댓글, 해시태그, 사진 가져오는 방법

			for(int i = 0 ; i < feedList.size(); i++) {

				int feedId = feedList.get(i).getFeedId();
				List<FileDto> fileList = feedDao.fileList(feedId);
				List<HashtagDto> hashtagList = feedDao.hashtagList(feedId);
				List<UserDto> userList = feedDao.eventMemberList(feedId);
				LocationDto locationDto = feedDao.locationSearch(feedId);

				//댓글은 상세보기에서만있으면 되지 않을까??
				//				List<CommentDto> commentList = feedDao.commentList(feedId);
				//				feedList.get(i).setCommentList(commentList);
				feedList.get(i).setFileList(fileList);
				feedList.get(i).setHashtagList(hashtagList);
				feedList.get(i).setUserList(userList);
				feedList.get(i).setLocationDto(locationDto);
			}

			feedResultDto.setFeedList(feedList);
			feedResultDto.setResult(SUCCESS);			
		}catch(Exception e) {
			e.printStackTrace();
			feedResultDto.setResult(FAIL);
		}

		return feedResultDto;


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

			//feed table 추가
			feedDao.feedInsert(feedDto);
			int feedId = feedDto.getFeedId();
			System.out.println(feedDto);

			// hashtag 추가 
		
			List<HashtagDto> hashtagDtoList = feedDto.getHashtagList();

			if(hashtagDtoList != null) {
				
				for (HashtagDto hashtagDto : hashtagDtoList) {
					hashtagDto.setFeedId(feedId);
					feedDao.feedHashtagInsert(hashtagDto);
				}
			}

			// 참가 인원 추가
			List<UserDto> userDtoList = feedDto.getUserList();

			for (UserDto userDto : userDtoList) {
				EventMemberDto eventMemberDto = new EventMemberDto();
				eventMemberDto.setUserSeq(userDto.getUserSeq());
				eventMemberDto.setFeedId(feedId);
				feedDao.feedEventMemberInsert(eventMemberDto);
			}

			// 장소 추가
			LocationDto locationDto = feedDto.getLocationDto();
			feedDao.feedLocationInsert(locationDto);

			// 장소 즐겨찾기 추가
			if ( locationDto.isFavorite() ) {
				LocationFavoriteDto locationFavoriteDto = new LocationFavoriteDto();
				locationFavoriteDto.setLocationId(locationDto.getLocationId());
				locationFavoriteDto.setUserSeq(feedDto.getFeedAuthorSeq());
				feedDao.feedLocationFavoriteInsert(locationFavoriteDto);
			}


			List<MultipartFile> fileList = request.getFiles("file");
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

			feedResultDto.setResult(SUCCESS);

		}catch(IOException e) {
			e.printStackTrace();
			feedResultDto.setResult(FAIL);
		}
		return feedResultDto;
	}

	@Override
	public FeedResultDto feedUpdate(FeedDto feedDto, MultipartHttpServletRequest request) {

		FeedResultDto feedResultDto = new FeedResultDto();


		try {
			feedDao.feedUpdate(feedDto);

			List<MultipartFile> fileList = request.getFiles("file");

			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) uploadDir.mkdir();



			List<String> fileUrlList = feedDao.feedFileUrlDeleteList(feedDto.getFeedId());	
			for(String fileUrl : fileUrlList) {	
				File file = new File(uploadPath + File.separator, fileUrl);
				if(file.exists()) {
					file.delete();
				}
			}

			feedDao.feedFileDelete(feedDto.getFeedId()); 


			// 여기서 부터 하면 됨
			for (MultipartFile part : fileList) {
				int feedId = feedDto.getFeedId();

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


			feedResultDto.setResult(SUCCESS);

		}catch(IOException e) {
			e.printStackTrace();
			feedResultDto.setResult(FAIL);
		}

		return feedResultDto;
	}

	@Override
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
