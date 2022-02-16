package com.ssafy.homesns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homesns.dto.CommentDto;
import com.ssafy.homesns.dto.CommentEmotionDto;
import com.ssafy.homesns.dto.CommentEmotionResultDto;
import com.ssafy.homesns.dto.CommentParamDto;
import com.ssafy.homesns.dto.CommentResultDto;
import com.ssafy.homesns.service.CommentService;

@CrossOrigin(
		origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://i6e205.p.ssafy.io" },

		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })

@RestController
@RequestMapping(value="/api")
public class CommentController {

	@Autowired
	CommentService commentService;

	private static final int SUCCESS = 1;

	
	// 댓글 가져오기 => 
	@GetMapping(value="/feed/comment")
	public ResponseEntity<CommentResultDto> commentList(CommentParamDto commentParamDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		commentParamDto.setUserSeq(userSeq);
		CommentResultDto commentResultDto = commentService.commentList(commentParamDto);

		if ( commentResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	// 댓글 작성 => 댓글 레코드 생성 + 댓글 감정표현 레코드 생성
	@PostMapping(value="/feed/comment")
	public ResponseEntity<CommentResultDto> commentCreate(@RequestBody CommentDto commentDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		commentDto.setCommentAuthorSeq(userSeq);
		
		System.out.println("commentDTO ===="+commentDto);
		CommentResultDto commentResultDto = commentService.commentCreate(commentDto);

		if ( commentResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 댓글 수정 => 댓글 레코드 수정
	// 댓글 수정 기능 없애기로 함! 
	@PutMapping(value="/feed/comment")
	public ResponseEntity<CommentResultDto> commentUpdate(@RequestBody CommentDto commentDto) {
		
		CommentResultDto commentResultDto = commentService.commentUpdate(commentDto);

		if ( commentResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.OK);

		}
		return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 댓글 삭제 => 댓글 레코드 삭제 + 댓글 감정표현 레코드 삭제 + 댓글 감정표현 사용 레코드 삭제
	@DeleteMapping(value="/feed/comment/{commentId}")
	public ResponseEntity<CommentResultDto> commentDelete(@PathVariable(value="commentId") int commentId) {
		
		CommentResultDto commentResultDto = commentService.commentDelete(commentId);
		
		if ( commentResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.OK);

		}
		return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	// 감정표현 하기 => 댓글 감정표현 레코드 수정 + 댓글 감정표현 사용 레코드 추가
//	@PutMapping(value="/feed/comment/emotion/add")
//	public ResponseEntity<CommentEmotionResultDto> commentEmotionAdd(CommentEmotionDto commentEmotionDto) {
//		// Security Context에서 UserSeq를 구한다
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		int userSeq = Integer.parseInt(authentication.getName());
//
//		commentEmotionDto.setUserSeq(userSeq);
//		CommentEmotionResultDto commentEmotionResultDto = commentService.commentEmotionAdd(commentEmotionDto);
//		
//		if ( commentEmotionResultDto.getResult() == SUCCESS ) {
//			return new ResponseEntity<CommentEmotionResultDto>(commentEmotionResultDto, HttpStatus.OK);
//		}
//		return new ResponseEntity<CommentEmotionResultDto>(commentEmotionResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	// 감정표현 취소 => 댓글 감정표현 레코드 수정 + 댓글 감정표현 사용 레코드 삭제
//	@PutMapping(value="/feed/comment/emotion/sub")
//	public ResponseEntity<CommentEmotionResultDto> commentEmotionSub(CommentEmotionDto commentEmotionDto) {
//		// Security Context에서 UserSeq를 구한다
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		int userSeq = Integer.parseInt(authentication.getName());
//		
//		commentEmotionDto.setUserSeq(userSeq);
//		CommentEmotionResultDto commentEmotionResultDto = commentService.commentEmotionSub(commentEmotionDto);
//
//		if ( commentEmotionResultDto.getResult() == SUCCESS ) {
//			return new ResponseEntity<CommentEmotionResultDto>(commentEmotionResultDto, HttpStatus.OK);
//		}
//		return new ResponseEntity<CommentEmotionResultDto>(commentEmotionResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}








