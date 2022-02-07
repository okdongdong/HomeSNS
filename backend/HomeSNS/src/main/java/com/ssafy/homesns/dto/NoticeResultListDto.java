package com.ssafy.homesns.dto;

import java.util.List;

public class NoticeResultListDto {

    private int result, count;
    private List<NoticeResultDto> noticeResultDtoList;
    private List<Integer> targetUserList;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<NoticeResultDto> getNoticeResultDtoList() {
		return noticeResultDtoList;
	}

	public void setNoticeResultDtoList(List<NoticeResultDto> noticeResultDtoList) {
		this.noticeResultDtoList = noticeResultDtoList;
	}

	public List<Integer> getTargetUserList() {
		return targetUserList;
	}

	public void setTargetUserList(List<Integer> targetUserList) {
		this.targetUserList = targetUserList;
	}

	@Override
	public String toString() {
		return "NoticeResultListDto{" +
				"result=" + result +
				", count=" + count +
				", noticeResultDtoList=" + noticeResultDtoList +
				", targetUserList=" + targetUserList +
				'}';
	}
}
