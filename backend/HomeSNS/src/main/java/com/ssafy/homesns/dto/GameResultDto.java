package com.ssafy.homesns.dto;

import java.util.List;

public class GameResultDto {
	
	private int result;
	private GameDto gameDto;
	private List<GameDto> gameDtoList;
	
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public GameDto getGameDto() {
		return gameDto;
	}
	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}
	public List<GameDto> getGameDtoList() {
		return gameDtoList;
	}
	public void setGameDtoList(List<GameDto> gameDtoList) {
		this.gameDtoList = gameDtoList;
	}
	@Override
	public String toString() {
		return "GameResultDto [result=" + result + ", gameDto=" + gameDto + ", gameDtoList=" + gameDtoList + "]";
	}

}
