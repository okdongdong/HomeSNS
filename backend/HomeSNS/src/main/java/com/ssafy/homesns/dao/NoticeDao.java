package com.ssafy.homesns.dao;

import com.ssafy.homesns.dto.NoticeDto;
import com.ssafy.homesns.dto.NoticeResultDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeDao {

    // 알림 생성
    public int noticeCreate(NoticeDto noticeDto);

    // 알림 읽음처리
    public int noticeRead(int noticeId);

    // 알림 모두 읽음처리
    public int noticeReadAll(int groupId, int userSeq);

    // 알림 리스트 불러오기 / 특정유저가 받는 알림
    public List<NoticeResultDto> noticeListSearch(int targetUserSeq, int targetUserGroupId, int start, int end);

    // 읽지않은 알림 개수
    public int noticeCount(int targetUserSeq, int targetUserGroupId);

}