package com.ssafy.homesns.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor  // 파라미터가 없는 기본생성자 생성
@AllArgsConstructor // 모든필드를 가진 생성자 자동생성
public class NoticeDto {

    @Getter
    private int noticeId;

    @Getter @Setter
    private int userSeq;

    @Getter @Setter
    private String userName, userProfileUrl;

    @Getter @Setter
    private int targetUserSeq;

    @Getter @Setter
    private int targetUserGroupId;

    @Getter @Setter
    private String noticeType;

    @Getter @Setter
    private int noticeContentId;

    @Getter @Setter
    private String noticeReadYn;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Getter @Setter
    private List<Integer> targetUserList;

}
