package com.ssafy.homesns.dto;

import lombok.Getter;
import lombok.Setter;

public class NoticePageDto {
    @Getter @Setter
    private int targetUserSeq;

    @Getter @Setter
    private String noticeType;

    @Getter @Setter
    private int noticeContentId;


}
