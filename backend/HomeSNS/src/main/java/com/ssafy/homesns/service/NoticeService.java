package com.ssafy.homesns.service;

import com.ssafy.homesns.dto.NoticeDto;
import com.ssafy.homesns.dto.NoticeResultListDto;
import org.springframework.transaction.annotation.Transactional;

public interface NoticeService {

    NoticeResultListDto noticeCreate(NoticeDto noticeDto);
    NoticeResultListDto noticeListSearch(int userSeq, int groupId, int start, int end);
    int noticeRead(int noticeId);
    int noticeReadAll(int groupId, int userSeq);
}
