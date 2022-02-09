package com.ssafy.homesns.dto;

public class GhostLegDto {
	
	private int gameId;
	private String player;
	private int playerNum;
	private String result;
	private String map;
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		return "GhostLegDto [gameId=" + gameId + ", player=" + player + ", playerNum=" + playerNum + ", result="
				+ result + ", map=" + map + "]";
	}
	
}
