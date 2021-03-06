package com.ssafy.homesns.controller;

import com.ssafy.homesns.dto.NoticeDto;
import com.ssafy.homesns.dto.NoticeResultListDto;
import com.ssafy.homesns.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(
		origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://i6e205.p.ssafy.io" },
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    // receive를 메시지를 받을 endpoint로 설정합니다.
    @MessageMapping("/api/notice/receive/{groupId}")

    // send로 메시지를 반환합니다.
    @SendTo("/api/notice/send/{groupId}")
    public NoticeDto noticeCreate(NoticeDto noticeDto) {

        NoticeResultListDto noticeResultListDto = noticeService.noticeCreate(noticeDto);
        noticeDto.setTargetUserList(noticeResultListDto.getTargetUserList());

        return noticeDto;
    }

    // 현재 유저가 들어가 있는 그룹의 리스트를 가져온다
    @GetMapping(value = "/api/noticelist")
    public ResponseEntity<NoticeResultListDto> noticeListSearch(
            @RequestParam("groupId") int groupId,
            @RequestParam("start") int start
    ) {
        int end = start + 20;
        // Security Context에서 UserSeq를 구한다
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userSeq = Integer.parseInt(authentication.getName());

        NoticeResultListDto noticeResultListDto = noticeService.noticeListSearch(userSeq, groupId, start, end);

        if (noticeResultListDto.getResult() == SUCCESS) {
            return new ResponseEntity<NoticeResultListDto>(noticeResultListDto, HttpStatus.OK);
        }
        return new ResponseEntity<NoticeResultListDto>(noticeResultListDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/api/noticeread/{noticeId}")
    @ResponseBody
    public ResponseEntity noticeRead(@PathVariable int noticeId) {
        if (noticeService.noticeRead(noticeId) == SUCCESS) {
            return new ResponseEntity(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/api/noticelist/{groupId}")
    @ResponseBody
    public ResponseEntity noticeReadAll(@PathVariable int groupId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userSeq = Integer.parseInt(authentication.getName());
        if (noticeService.noticeReadAll(groupId, userSeq) == SUCCESS) {
            return new ResponseEntity(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

