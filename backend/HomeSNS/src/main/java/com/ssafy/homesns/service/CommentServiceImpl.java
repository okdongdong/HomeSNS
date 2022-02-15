package com.ssafy.homesns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homesns.dao.CommentDao;
import com.ssafy.homesns.dto.CommentDto;
import com.ssafy.homesns.dto.CommentEmotionDto;
import com.ssafy.homesns.dto.CommentEmotionResultDto;
import com.ssafy.homesns.dto.CommentParamDto;
import com.ssafy.homesns.dto.CommentResultDto;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDao commentDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	// 댓글 작성 => 댓글 레코드 생성 + 댓글 감정표현 레코드 생성
	@Override
	@Transactional
	public CommentResultDto commentCreate(CommentDto commentDto) {

		CommentResultDto commentResultDto = new CommentResultDto();
		

		try {
			if ( commentDao.commentCreate(commentDto) == 1 ) {
				
				for (int commentTagSeq : commentDto.getCommentTags() ) {
					commentDto.setCommentTagSeq(commentTagSeq);
					commentDao.commentTagCreate(commentDto);
				}
				commentResultDto.setResult(SUCCESS);
			}
			else {
				System.out.println("comment Create Err");
				commentResultDto.setResult(FAIL);
			}
		}catch(Exception e ) {
			System.out.println("tagCreate Err");
			e.printStackTrace();
			commentResultDto.setResult(FAIL);
		}
		return commentResultDto;
		
		// 감정관련 해서 생성 할때 사용한 코드
//		// 댓글 레코드 생성했다면,
//		if ( commentDao.commentCreate(commentDto) == 1 ) {
//			// 댓글 감정표현 레코드도 생성한다
//			commentDao.commentEmotionCreate(commentDto.getCommentId());
//
//			commentResultDto.setResult(SUCCESS);
//		} else {
//			commentResultDto.setResult(FAIL);
//		}
//		return commentResultDto;
	}

	// 댓글 가져오기 
	@Override
	public CommentResultDto commentList(CommentParamDto commentParamDto) {
		
		CommentResultDto commentResultDto = new CommentResultDto();
		
		
		try {
			List<CommentDto> commentDtoList = commentDao.commentList(commentParamDto);
			
			for(int i =0 ; i < commentDtoList.size(); i++) {
				
				List<String> commentTagList =commentDao.commentTagList(commentDtoList.get(i).getCommentId());
				commentDtoList.get(i).setCommentTagNameList(commentTagList);
			}
 
			commentResultDto.setCommentList(commentDtoList);	
			commentResultDto.setResult(SUCCESS);
		}catch(Exception e ) {
			e.printStackTrace();
			commentResultDto.setResult(FAIL);
		}
		return commentResultDto;
	}

	

	// 댓글 수정 => 댓글 레코드 수정
	@Override
	public CommentResultDto commentUpdate(CommentDto commentDto) {
		
		CommentResultDto commentResultDto = new CommentResultDto();
		
		if ( commentDao.commentUpdate(commentDto) == 1 ) {
			commentResultDto.setResult(SUCCESS);
		} else {
			commentResultDto.setResult(FAIL);
		}
		return commentResultDto;
	}

	// 댓글 삭제 => 댓글 레코드 삭제 + 댓글 감정표현 레코드 삭제 + 댓글 감정표현 사용 레코드 삭제
	// CASCADE 한다는 가정 하에 생성한 코드
	@Override
	public CommentResultDto commentDelete(int commentId) {
		
		CommentResultDto commentResultDto = new CommentResultDto();

		if ( commentDao.commentDelete(commentId) == 1 ) {
			commentResultDto.setResult(SUCCESS);
		} else {
			commentResultDto.setResult(FAIL);
		}
		return commentResultDto;
	}

	// 감정표현 하기 => 댓글 감정표현 레코드 수정 + 댓글 감정표현 사용 레코드 추가
	@Override
	@Transactional
	public CommentEmotionResultDto commentEmotionAdd(CommentEmotionDto commentEmotionDto) {
		
		CommentEmotionResultDto commentEmotionResultDto = new CommentEmotionResultDto();
		
		if ( commentEmotionDto.getGood() == 1 ) {
			commentDao.commentGoodAdd(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30001");
			commentDao.commentEmotionUserUseCreate(commentEmotionDto);
		} else if ( commentEmotionDto.getSad() == 1 ) {
			commentDao.commentSadAdd(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30002");
			commentDao.commentEmotionUserUseCreate(commentEmotionDto);
		} else if ( commentEmotionDto.getCheck() == 1 ) {
			commentDao.commentCheckAdd(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30003");
			commentDao.commentEmotionUserUseCreate(commentEmotionDto);
		} else if ( commentEmotionDto.getFun() == 1 ) {
			commentDao.commentFunAdd(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30004");
			commentDao.commentEmotionUserUseCreate(commentEmotionDto);
		} else if ( commentEmotionDto.getAmaze() == 1 ) {
			commentDao.commentAmazeAdd(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30005");
			commentDao.commentEmotionUserUseCreate(commentEmotionDto);
		} else {
			commentEmotionResultDto.setResult(FAIL);
		}
		
		return commentEmotionResultDto;
	}

	// 감정표현 취소 => 댓글 감정표현 레코드 수정 + 댓글 감정표현 사용 레코드 삭제
	@Override
	@Transactional
	public CommentEmotionResultDto commentEmotionSub(CommentEmotionDto commentEmotionDto) {
		
		CommentEmotionResultDto commentEmotionResultDto = new CommentEmotionResultDto();
		
		if ( commentEmotionDto.getGood() == 1 ) {
			commentDao.commentGoodSub(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30001");
			commentDao.commentEmotionUserUseDelete(commentEmotionDto);
		} else if ( commentEmotionDto.getSad() == 1 ) {
			commentDao.commentSadSub(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30002");
			commentDao.commentEmotionUserUseDelete(commentEmotionDto);
		} else if ( commentEmotionDto.getCheck() == 1 ) {
			commentDao.commentCheckSub(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30003");
			commentDao.commentEmotionUserUseDelete(commentEmotionDto);
		} else if ( commentEmotionDto.getFun() == 1 ) {
			commentDao.commentFunSub(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30004");
			commentDao.commentEmotionUserUseDelete(commentEmotionDto);
		} else if ( commentEmotionDto.getAmaze() == 1 ) {
			commentDao.commentAmazeSub(commentEmotionDto.getCommentId());
			commentEmotionDto.setCode("30005");
			commentDao.commentEmotionUserUseDelete(commentEmotionDto);
		} else {
			commentEmotionResultDto.setResult(FAIL);
		}
		
		return commentEmotionResultDto;
	}

}
