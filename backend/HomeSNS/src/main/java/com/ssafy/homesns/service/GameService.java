package com.ssafy.homesns.service;

import com.ssafy.homesns.dto.GameDto;
import com.ssafy.homesns.dto.GameParamDto;
import com.ssafy.homesns.dto.GameResultDto;
import com.ssafy.homesns.dto.VoteItemDto;
import com.ssafy.homesns.dto.VoteItemResultDto;

public interface GameService {

	// 사다리타기 생성
	public GameResultDto ghostLegCreate(GameDto gameDto);
	// 투표 생성
	public GameResultDto voteCreate(GameDto gameDto);
	
	// 게임 전체 조회
	public GameResultDto gameSearch(GameParamDto gameParamDto);
	// 사다리타기 상세 조회
	public GameResultDto ghostLegDetailSearch(int gameId);
	// 투표 상세 조회
	public GameResultDto voteDetailSearch(int gameId);
	
	// 사다리타기 수정
	public GameResultDto ghostLegUpdate(GameDto gameDto);
	// 투표 수정
	public GameResultDto voteUpdate(GameDto gameDto);
	
	// 투표 하기
	public VoteItemResultDto voteMake(VoteItemDto voteItemDto);
	// 투표 취소
	public VoteItemResultDto voteCancel(VoteItemDto voteItemDto);
	// 투표 종료
	public GameResultDto gameDisable(GameDto gameDto);
	
	// 게임 삭제
	public GameResultDto gameDelete(GameDto gameDto);
	
}
