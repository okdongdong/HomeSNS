package com.ssafy.homesns.dto;

import java.util.List;

public class NoticeResultListDto {

    private int result;
    private List<NoticeDto> noticeDtoList;
    private List<Integer> targetUserList;
    private int count;
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<NoticeDto> getNoticeDtoList() {
		return noticeDtoList;
	}
	public void setNoticeDtoList(List<NoticeDto> noticeDtoList) {
		this.noticeDtoList = noticeDtoList;
	}
	public List<Integer> getTargetUserList() {
		return targetUserList;
	}
	public void setTargetUserList(List<Integer> targetUserList) {
		this.targetUserList = targetUserList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "NoticeResultListDto [result=" + result + ", noticeDtoList=" + noticeDtoList + ", targetUserList="
				+ targetUserList + ", count=" + count + "]";
	}

}
