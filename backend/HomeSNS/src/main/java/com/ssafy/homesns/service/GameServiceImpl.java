package com.ssafy.homesns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homesns.dao.GameDao;
import com.ssafy.homesns.dto.GameDto;
import com.ssafy.homesns.dto.GameParamDto;
import com.ssafy.homesns.dto.GameResultDto;
import com.ssafy.homesns.dto.GhostLegDto;
import com.ssafy.homesns.dto.VoteItemDto;
import com.ssafy.homesns.dto.VoteItemResultDto;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	GameDao gameDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	// 사다리타기 생성
	@Override
	@Transactional(rollbackFor = Exception.class)
	public GameResultDto ghostLegCreate(GameDto gameDto) {
		
		GameResultDto gameResultDto = new GameResultDto();
		
		// 게임 레코드 생성 + 사다리타기 레코드 생성
		try {
			gameDao.gameCreate(gameDto);
			gameDto.getGhostLegDto().setGameId(gameDto.getGameId());
			gameDao.ghostLegCreate(gameDto.getGhostLegDto());
			
			gameResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			gameResultDto.setResult(FAIL);
		}
		
		return gameResultDto;
	}
	
	// 투표 생성
	@Override
	@Transactional(rollbackFor = Exception.class)
	public GameResultDto voteCreate(GameDto gameDto) {
		
		GameResultDto gameResultDto = new GameResultDto();
		
		// 게임 레코드 생성 + 투표 레코드 생성
		try {
			gameDao.gameCreate(gameDto);
			
			for (int i = 0; i < gameDto.getVoteItemDtoList().size(); i++) {
				VoteItemDto voteItemDto = gameDto.getVoteItemDtoList().get(i);
				voteItemDto.setGameId(gameDto.getGameId());
				gameDao.voteCreate(voteItemDto);
			}
			
			gameResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			gameResultDto.setResult(FAIL);
		}
		
		return gameResultDto;
	}

	// 게임 전체 조회
	@Override
	@Transactional
	public GameResultDto gameSearch(GameParamDto gameParamDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		
		GameResultDto gameResultDto = new GameResultDto();
		
		try {
			
			List<GameDto> list = gameDao.gameSearch(gameParamDto);
			
			for (int i = 0; i < list.size(); i++) {
				// 사다리타기 코드 40001, 투표 코드 40002
				if ( list.get(i).getCode().equals("40001") ) {
					GhostLegDto ghostLegDto = gameDao.ghostLegDetailSearch(list.get(i).getGameId());
					list.get(i).setGhostLegDto(ghostLegDto);
				} else if ( list.get(i).getCode().equals("40002") ) {
					List<VoteItemDto> voteItemDtoList = gameDao.voteDetailSearch(list.get(i).getGameId());
					
					VoteItemDto voteItemDto = new VoteItemDto();
					voteItemDto.setGameId(list.get(i).getGameId());
					voteItemDto.setUserSeq(userSeq);
					Integer voteItemId = gameDao.voterSearch(voteItemDto);
					if ( voteItemId != null ) {
						list.get(i).setVoteItemId(voteItemId);
					}
					
					list.get(i).setVoteItemDtoList(voteItemDtoList);
				}
			}
			
			gameResultDto.setGameDtoList(list);
			gameResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			gameResultDto.setResult(FAIL);
		}
		
		return gameResultDto;
	}

	// 사다리타기 상세 조회
	@Override
	@Transactional
	public GameResultDto ghostLegDetailSearch(int gameId) {
		
		GameResultDto gameResultDto = new GameResultDto();
		try {
			
			GameDto gameDto = gameDao.gameDetailSearch(gameId);
			gameDto.setGhostLegDto(gameDao.ghostLegDetailSearch(gameId));
			
			gameResultDto.setGameDto(gameDto);
			gameResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			gameResultDto.setResult(FAIL);
		}
		
		return gameResultDto;
	}

	// 투표 상세 조회
	@Override
	@Transactional
	public GameResultDto voteDetailSearch(int gameId) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		
		GameResultDto gameResultDto = new GameResultDto();
		try {
			
			GameDto gameDto = gameDao.gameDetailSearch(gameId);
			gameDto.setVoteItemDtoList(gameDao.voteDetailSearch(gameId));
			
			VoteItemDto voteItemDto = new VoteItemDto();
			voteItemDto.setGameId(gameDto.getGameId());
			voteItemDto.setUserSeq(userSeq);
			Integer voteItemId = gameDao.voterSearch(voteItemDto);
			if ( voteItemId != null ) {
				gameDto.setVoteItemId(voteItemId);
			}
			
			gameResultDto.setGameDto(gameDto);
			gameResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			gameResultDto.setResult(FAIL);
		}
		
		return gameResultDto;
	}

	// 사다리타기 수정
	@Override
	@Transactional
	public GameResultDto ghostLegUpdate(GameDto gameDto) {

		GameResultDto gameResultDto = new GameResultDto();
		
		// 게임 레코드 수정 + 사다리타기 레코드 수정
		try {
			gameDao.gameUpdate(gameDto);
			gameDto.getGhostLegDto().setGameId(gameDto.getGameId());
			gameDao.ghostLegUpdate(gameDto.getGhostLegDto());
			
			gameResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			gameResultDto.setResult(FAIL);
		}
		
		return gameResultDto;
	}

	// 투표 수정
	@Override
	@Transactional
	public GameResultDto voteUpdate(GameDto gameDto) {
		
		GameResultDto gameResultDto = new GameResultDto();
		
		// 게임 레코드 수정 + 투표 레코드 수정
		try {
			gameDao.gameUpdate(gameDto);
			
			for (int i = 0; i < gameDto.getVoteItemDtoList().size(); i++) {
				VoteItemDto voteItemDto = gameDto.getVoteItemDtoList().get(i);
				System.out.println(voteItemDto);
				voteItemDto.setGameId(gameDto.getGameId());
				gameDao.voteUpdate(voteItemDto);
			}
			
			gameResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			gameResultDto.setResult(FAIL);
		}
		
		return gameResultDto;
	}

	// 투표하기 = voter 레코드 추가, voteItem의 count값 변경
	@Override
	@Transactional
	public VoteItemResultDto voteMake(VoteItemDto voteItemDto) {
		
		VoteItemResultDto voteItemResultDto = new VoteItemResultDto();
		
		try {
			gameDao.voterCreate(voteItemDto);
			gameDao.voteAdd(voteItemDto);
			
			voteItemResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			voteItemResultDto.setResult(FAIL);
		}
		
		return voteItemResultDto;
	}

	// 투표 취소하기
	@Override
	@Transactional
	public VoteItemResultDto voteCancel(VoteItemDto voteItemDto) {
		
		VoteItemResultDto voteItemResultDto = new VoteItemResultDto();
		
		try {
			gameDao.voterDelete(voteItemDto);
			gameDao.voteSub(voteItemDto);
			
			voteItemResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			voteItemResultDto.setResult(FAIL);
		}
		
		return voteItemResultDto;
	}

	// 수정 불가상태 만들기 - 투표 종료
	@Override
	public GameResultDto gameDisable(GameDto gameDto) {

		GameResultDto gameResultDto = new GameResultDto();
		
		if ( gameDao.gameDisable(gameDto) == SUCCESS) {
			gameResultDto.setResult(SUCCESS);
		} else {
			gameResultDto.setResult(FAIL);
		}
		return gameResultDto;
	}

	// 게임 삭제
	@Override
	public GameResultDto gameDelete(GameDto gameDto) {

		GameResultDto gameResultDto = new GameResultDto();
		
		if ( gameDao.gameDelete(gameDto) == SUCCESS) {
			gameResultDto.setResult(SUCCESS);
		} else {
			gameResultDto.setResult(FAIL);
		}
		return gameResultDto;
	}

}
