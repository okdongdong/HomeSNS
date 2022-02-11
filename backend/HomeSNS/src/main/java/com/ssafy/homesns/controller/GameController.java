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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homesns.dto.GameDto;
import com.ssafy.homesns.dto.GameParamDto;
import com.ssafy.homesns.dto.GameResultDto;
import com.ssafy.homesns.dto.VoteItemDto;
import com.ssafy.homesns.dto.VoteItemResultDto;
import com.ssafy.homesns.service.GameService;

@CrossOrigin(
		origins = "http://localhost:5500", // npm에서 5500번을 사용한다
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })

@RestController
public class GameController {

	@Autowired
	GameService gameService;

	private static final int SUCCESS = 1;


	// 사다리타기 게임 생성 = 값을 그냥 저장하기만 하면 될듯
	@PostMapping(value="/game/ghostLeg")
	public ResponseEntity<GameResultDto> ghostLegCreate(@RequestBody GameDto gameDto) {
		System.out.println(gameDto);
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		gameDto.setGameAuthor(userSeq);
		gameDto.setCode("40001");

		GameResultDto gameResultDto = gameService.ghostLegCreate(gameDto);
		
		if ( gameResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 투표 생성 = list로 받은 voteItem을 저장해줘야 할 것
	@PostMapping(value="/game/vote")
	public ResponseEntity<GameResultDto> voteCreate(@RequestBody GameDto gameDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		gameDto.setGameAuthor(userSeq);
		gameDto.setCode("40002");
		
		GameResultDto gameResultDto = gameService.voteCreate(gameDto);

		if ( gameResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// 게임 목록 조회 = 사다리타기, 투표를 구분하지 않고 모두 조회한다
	// 투표 현황 조회 == 내가 어떤 아이템에 투표를 했는지에 대한 정보 역시 같이 반환해줘야 할것
	// 게임 전체 조회
	@GetMapping(value="/game")
	public ResponseEntity<GameResultDto> gameSearch(GameParamDto gameParamDto) {

		System.out.println(gameParamDto);
		GameResultDto gameResultDto = gameService.gameSearch(gameParamDto);

		if ( gameResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 사다리타기 상세 조회
	@GetMapping(value="/game/ghostLeg/{gameId}")
	public ResponseEntity<GameResultDto> ghostLegDetailSearch(@PathVariable(value="gameId") int gameId) {

		GameResultDto gameResultDto = gameService.ghostLegDetailSearch(gameId);

		if ( gameResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 투표 상세 조회
	@GetMapping(value="/game/vote/{gameId}")
	public ResponseEntity<GameResultDto> voteDetailSearch(@PathVariable(value="gameId") int gameId) {

		GameResultDto gameResultDto = gameService.voteDetailSearch(gameId);

		if ( gameResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	// 사다리타기 게임 수정
	@PutMapping(value="/game/ghostLeg")
	public ResponseEntity<GameResultDto> ghostLegUpdate(@RequestBody GameDto gameDto) {
		
		GameResultDto gameResultDto = gameService.ghostLegUpdate(gameDto);

		if ( gameResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 투표 수정
	@PutMapping(value="/game/vote")
	public ResponseEntity<GameResultDto> voteUpdate(@RequestBody GameDto gameDto) {

		GameResultDto gameResultDto = gameService.voteUpdate(gameDto);

		if ( gameResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 투표 하기
	@PostMapping(value="/game/vote/voter")
	public ResponseEntity<VoteItemResultDto> voteMake(@RequestBody VoteItemDto voteItemDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		voteItemDto.setUserSeq(userSeq);
		
		VoteItemResultDto voteItemResultDto = gameService.voteMake(voteItemDto);

		if ( voteItemResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<VoteItemResultDto>(voteItemResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<VoteItemResultDto>(voteItemResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 투표 취소
	@DeleteMapping(value="/game/vote/voter")
	public ResponseEntity<VoteItemResultDto> voteCancel(@RequestBody VoteItemDto voteItemDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		voteItemDto.setUserSeq(userSeq);
		
		VoteItemResultDto voteItemResultDto = gameService.voteCancel(voteItemDto);

		if ( voteItemResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<VoteItemResultDto>(voteItemResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<VoteItemResultDto>(voteItemResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 투표 종료 and 수정불가상태 만들기 => updateYn을 사용하여 더 이상 투표를 할 수 없도록 한다
	@PutMapping(value="/game/vote/quit")
	public ResponseEntity<GameResultDto> gameUpdate(@RequestBody GameDto gameDto) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());
		gameDto.setGameAuthor(userSeq);
		
		GameResultDto gameResultDto = gameService.gameDisable(gameDto);

		if ( gameResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	// 게임 삭제 == 작성자에게만 부여된 기능
	@DeleteMapping(value="/game/{gameId}")
	public ResponseEntity<GameResultDto> gameDelete(@PathVariable(value="gameId") int gameId) {
		// Security Context에서 UserSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userSeq = Integer.parseInt(authentication.getName());

		GameDto gameDto = new GameDto();
		gameDto.setGameAuthor(userSeq);
		gameDto.setGameId(gameId);

		GameResultDto gameResultDto = gameService.gameDelete(gameDto);

		if ( gameResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<GameResultDto>(gameResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}










