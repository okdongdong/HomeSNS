package com.ssafy.homesns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.homesns.dto.GameDto;
import com.ssafy.homesns.dto.GameParamDto;
import com.ssafy.homesns.dto.GhostLegDto;
import com.ssafy.homesns.dto.VoteItemDto;

@Mapper
public interface GameDao {

	// 게임 생성
	public int gameCreate(GameDto gameDto);
	// 사다리타기 생성
	public int ghostLegCreate(GhostLegDto ghostLegDto);
	// 투표 생성, 첫 생성 때 count = 0 (DB 에서 default)
	public int voteCreate(VoteItemDto voteItemDto);
	
	// 게임 전체 조회 + 동시에 profileImg와 이름을 join으로 가져온다
	public List<GameDto> gameSearch(GameParamDto gameParamDto);
	// 게임 상세 조회
	public GameDto gameDetailSearch(int gameId);
	// 사다리타기 상세 조회
	public GhostLegDto ghostLegDetailSearch(int gameId);
	// 투표 상세 조회
	public List<VoteItemDto> voteDetailSearch(int gameId);
	
	// 게임 수정
	public int gameUpdate(GameDto gameDto);
	// 사다리타기 수정
	public int ghostLegUpdate(GhostLegDto ghostLegDto);
	// 투표 수정
	public int voteUpdate(VoteItemDto voteItemDto);

	// 투표 하기
	//// voter 레코드 생성
	public int voterCreate(VoteItemDto voteItemDto);
	//// 득표수 증가
	public int voteAdd(VoteItemDto voteItemDto);
	// 투표 취소
	//// voter 레코드 삭제
	public int voterDelete(VoteItemDto voteItemDto);
	//// 득표수 차감
	public int voteSub(VoteItemDto voteItemDto);
	// 투표 종료 - 게임 업데이트를 통해서 수정 불가 상태로 만들기
	public int gameDisable(GameDto gameDto);
	
	// 게임 삭제
	public int gameDelete(GameDto gameDto);
}
