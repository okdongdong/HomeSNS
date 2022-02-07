package com.ssafy.homesns.dto;

import lombok.*;

import java.util.List;


@Getter @Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자 자동생성
@AllArgsConstructor // 모든필드를 가진 생성자 자동생성
public class NoticeResultListDto {

    private int result;
    private List<NoticeDto> noticeDtoList;
    private List<Integer> targetUserList;
    private int count;

}
