package com.ssafy.homesns.service;

import com.ssafy.homesns.dao.*;
import com.ssafy.homesns.dto.NoticeDto;
import com.ssafy.homesns.dto.NoticeResultDto;
import com.ssafy.homesns.dto.NoticeResultListDto;
import com.ssafy.homesns.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeDao noticeDao;

    @Autowired
    GroupDao groupDao;

    @Autowired
    FeedDao feedDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    GameDao gameDao;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    @Transactional
    public NoticeResultListDto noticeCreate(NoticeDto noticeDto) {
        NoticeResultListDto noticeResultListDto = new NoticeResultListDto();
        List<Integer> noticeTargetUserList = new ArrayList<>();
        int targetUser = noticeDto.getTargetUserSeq();
        // 요청의 타겟 유저가 -1로 들어온 경우 알람 생성유저 본인을 제외한 해당 그룹 내 모든 인원에게 알림 전송
        boolean isTargetAll = targetUser == -1;
        noticeResultListDto.setResult(SUCCESS);
        System.out.println(isTargetAll);

        if (isTargetAll) {
            int nowGroupId = noticeDto.getTargetUserGroupId();

            // 그룹내 모든 인원리스트를 가져옴
            List<UserDto> nowGroupMemberList = groupDao.groupMemberListSearch(nowGroupId);

            // 그룹내 모든 인원에 대해서 하나하나 알람을 만들어줌
            for (UserDto userDto : nowGroupMemberList) {
                int nowTargetUser = userDto.getUserSeq();

                // 현재탐색중인 유저가 알림을 생성한 유저라면 넘어가야함
                if (nowTargetUser == noticeDto.getUserSeq()) {
                    continue;
                }

                // 타겟유저 설정
                noticeDto.setTargetUserSeq(nowTargetUser);
                noticeTargetUserList.add(nowTargetUser);

                // DB에 알림정보 저장
                if (noticeDao.noticeCreate(noticeDto) != 1) {
                    noticeResultListDto.setResult(FAIL);
                    break;
                }
            }
            noticeDto.setTargetUserSeq(-1);

        } else {
            // 타겟유저 설정
            noticeDto.setTargetUserSeq(targetUser);
            noticeTargetUserList.add(targetUser);

            // DB에 알림정보 저장
            if (noticeDao.noticeCreate(noticeDto) != 1) {
                noticeResultListDto.setResult(FAIL);
            }
        }
        if (noticeResultListDto.getResult() == FAIL) {
            noticeTargetUserList.clear();
            noticeTargetUserList.add(-1);
        }

        noticeResultListDto.setTargetUserList(noticeTargetUserList);

        return noticeResultListDto;
    }

    // 모든알림을 받아와서 반환
    @Override
    @Transactional
    public NoticeResultListDto noticeListSearch(int userSeq, int groupId, int start, int end) {

        NoticeResultListDto noticeResultListDto = new NoticeResultListDto();

        try {
            noticeResultListDto.setNoticeResultDtoList(noticeDao.noticeListSearch(userSeq, groupId, start, end));
            for (int i = 0; i < noticeResultListDto.getNoticeResultDtoList().size(); i++) {
                NoticeResultDto nowNoticeResultDto = noticeResultListDto.getNoticeResultDtoList().get(i);
                String nowNoticeType = nowNoticeResultDto.getNoticeType();
                int nowContentId = nowNoticeResultDto.getNoticeContentId();

                switch (nowNoticeType) {
                    case "feedCreate":
                        nowNoticeResultDto.setNoticeContentTitle(feedDao.feedDetail(nowContentId).getFeedTitle());
                        nowNoticeResultDto.setNoticeContentContent(feedDao.feedDetail(nowContentId).getFeedContent());
                        break;
//                    case "commentCreate":
////                        nowNoticeResultDto.setNoticeContentTitle(commentDao.feedDetail(nowContentId).getFeedTitle());
////                        nowNoticeResultDto.setNoticeContentContent(commentDao.feedDetail(nowContentId).getFeedContent());
//                        break;
//                    case "emotionCreate":
////                    nowNoticeResultDto.setNoticeContentTitle(feedDao.feedDetail(nowContentId).getFeedTitle());
////                    nowNoticeResultDto.setNoticeContentContent(feedDao.feedDetail(nowContentId).getFeedContent());
//                        break;
//                    case "share":
////                    nowNoticeResultDto.setNoticeContentTitle(feedDao.feedDetail(nowContentId).getFeedTitle());
////                    nowNoticeResultDto.setNoticeContentContent(feedDao.feedDetail(nowContentId).getFeedContent());
//                        break;
                    case "voteCreate":
                    case "ghostLegCreate":
                        nowNoticeResultDto.setNoticeContentTitle(gameDao.gameDetailSearch(nowContentId).getGameTitle());
                        break;
                    default:
                        break;
                }
            }
            noticeResultListDto.setCount(noticeDao.noticeCount(userSeq, groupId));
            noticeResultListDto.setResult(SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            noticeResultListDto.setResult(FAIL);

        }

        return noticeResultListDto;
    }

    @Override
    @Transactional
    public int noticeRead(int noticeId) {
        try {
            noticeDao.noticeRead(noticeId);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return FAIL;
    }

    @Override
    @Transactional
    public int noticeReadAll(int groupId, int userSeq) {
        try {
            noticeDao.noticeReadAll(groupId, userSeq);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return FAIL;
    }
}
