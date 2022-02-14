package com.ssafy.homesns.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.CommentDto;
import com.ssafy.homesns.dto.CommentEmotionDto;
import com.ssafy.homesns.dto.CommentParamDto;

@Mapper
// 댓글 목록 찾기 기능은 피드를 불러올 때 동시에 진행
// 댓글 감정표현 찾기 기능은 피드를 불러올 때 동시에 진행
public interface CommentDao {
	
	// 댓글 생성
	public int commentCreate(CommentDto commentDto);
	// 댓글 수정
	public int commentUpdate(CommentDto commentDto);
	// 댓글 삭제
	public int commentDelete(int commentId);
	// 댓글 읽기
	public int commentList(CommentParamDto commentDto);
	
	
	// 댓글 감정표현 추가, 댓글을 생성하면서 동시에 모든 값이 0인 댓글 감정표현 테이블 생성
	public int commentEmotionCreate(int commentId);
	// 해당 댓글의 감정표현 현황 찾기
	public CommentDto commentEmotionSearch(int commentId);
	// 해당 댓글에 감정표현을 추가 / 삭제
	//// Good
	public int commentGoodAdd(int commentId);
	public int commentGoodSub(int commentId);
	//// Sad
	public int commentSadAdd(int commentId);
	public int commentSadSub(int commentId);
	//// Check
	public int commentCheckAdd(int commentId);
	public int commentCheckSub(int commentId);
	//// Fun
	public int commentFunAdd(int commentId);
	public int commentFunSub(int commentId);
	//// Amaze
	public int commentAmazeAdd(int commentId);
	public int commentAmazeSub(int commentId);
	// 댓글 감정표현 삭제, 댓글을 삭제하면서 동시에 댓글 감정표현 테이블 삭제
	public int commentEmotionDelete(int commendId);
	
	
	// 댓글 감정표현 기록 추가, 댓글 감정표현의 수정에 따라 테이블 생성
	public int commentEmotionUserUseCreate(CommentEmotionDto commentEmotionDto);
	// 유저가 댓글에 어떤 감정표현을 했는지 찾기 => 결과값이 없으면 아직 감정표현을 하지 않은 것
	public CommentEmotionDto commentEmotionUserUseSearch(CommentEmotionDto commentEmotionDto);
	// 댓글 감정표현 기록 삭제, 댓글 감정표현의 수정에 따라 테이블 삭제
	public int commentEmotionUserUseDelete(CommentEmotionDto commentEmotionDto);
	
}
