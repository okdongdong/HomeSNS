package com.ssafy.homesns.service;

import com.ssafy.homesns.dto.CommentDto;
import com.ssafy.homesns.dto.CommentEmotionDto;
import com.ssafy.homesns.dto.CommentEmotionResultDto;
import com.ssafy.homesns.dto.CommentResultDto;

public interface CommentService {
	
	// 댓글 작성 => 댓글 레코드 생성 + 댓글 감정표현 레코드 생성
	public CommentResultDto commentCreate(CommentDto commentDto);
	// 댓글 수정 => 댓글 레코드 수정
	public CommentResultDto commentUpdate(CommentDto commentDto);
	// 댓글 삭제 => 댓글 레코드 삭제 + 댓글 감정표현 레코드 삭제 + 댓글 감정표현 사용 레코드 삭제
	public CommentResultDto commentDelete(int commentId);
	
	// 감정표현 하기 => 댓글 감정표현 레코드 수정 + 댓글 감정표현 사용 레코드 추가
	public CommentEmotionResultDto commentEmotionAdd(CommentEmotionDto commentEmotionDto);
	// 감정표현 취소 => 댓글 감정표현 레코드 수정 + 댓글 감정표현 사용 레코드 삭제
	public CommentEmotionResultDto commentEmotionSub(CommentEmotionDto commentEmotionDto);
}
