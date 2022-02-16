import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import axios from "axios";
import router from "../../router";

const notice = {
  namespaced: true,
  state: {
    recv: {
      userProfileUrl: null,
      userSeq: null,
      noticeType: null,
      noticeContentId: null,
      userName: null,
      noticeMessage: null,
    },
    noticeAlarm: false,
    unreadNoticeCount: 0,
    noticeList: [],
    nowGroupId: null,
    start: 0,
    dialog: false,
  },
  mutations: {
    NOTICE_DISCONNECT(state) {
      state.noticeList = [];
    },
    NOTICE_ALARM(state, recv) {
      state.recv = recv;
      state.noticeAlarm = true;
      setTimeout(() => (state.noticeAlarm = false), 2500);
    },
    NOTICE_CLEAR(state) {
      state.noticeList = [];
      state.start = 0;
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/noticelist`,
        headers: { Authorization: token },
        params: {
          groupId: state.nowGroupId,
          start: state.start,
        },
      })
        .then((res) => {
          const noticeList = [];
          const count = res.data.count;
          if (res.data.noticeResultDtoList.length) {
            res.data.noticeResultDtoList.forEach((notice) => {
              let message = "";
              switch (notice.noticeType) {
                case "feedCreate":
                  message = "새로운 피드를 작성했습니다.";
                  break;
                case "commentCreate":
                  message = "회원님의 글에 댓글을 작성했습니다.";
                  break;
                case "emotionCreate":
                  message = "회원님의 글에 감정표현을 했습니다.";
                  break;
                case "shareCreate":
                  message = "추억을 공유했습니다.";
                  break;
                case "voteCreate":
                  message = "새로운 투표를 작성했습니다.";
                  break;
                case "voteEnd":
                  message = "투표를 종료했습니다.";
                  break;
                case "ghostLegCreate":
                  message = "새로운 사다리타기를 작성했습니다.";
                  break;
                default:
                  message = notice.noticeType + "이 작성되었습니다.";
              }
              notice["noticeMessage"] = message;
              noticeList.push(notice);
            });
          }

          return { noticeList, count };
        })

        .then((res) => {
          console.log(res);
          if (res.noticeList.length) {
            // 20개씩 인피니티스크롤로 구현
            state.start += 20;
            state.noticeList.push(...res.noticeList);
            state.unreadNoticeCount = res.count;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    GET_NOTICE_LIST(state, { noticeList, count }) {
      state.start += 20;
      state.noticeList.push(...noticeList);
      state.unreadNoticeCount = count;
    },
    CAL_NOTICE_COUNT(state, num) {
      state.unreadNoticeCount += num;
    },
    SET_GROUP_ID(state, nowGroupId) {
      state.nowGroupId = nowGroupId;
    },
  },
  actions: {
    setDialog({ commit, rootState }) {
      commit("SET_GROUP_ID", rootState.account.nowGroup.groupId);
    },
    noticeReadAll({ commit, rootState }) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "PUT",
        url: `${process.env.VUE_APP_MCS_URL}/noticelist/${rootState.account.nowGroup.groupId}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log(res);
          commit("NOTICE_CLEAR");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    noticeRead({ commit }, noticeId, noticeContentId, noticeType) {
      if (
        noticeType == "feed" ||
        noticeType == "comment" ||
        noticeType == "emotion" ||
        noticeType == "share"
      ) {
        router.push({
          name: "Detail",
          params: { feedId: noticeContentId },
        });
      } else if (noticeType == "vote") {
        router.push({ name: "EntFeedList" });
      } else if (noticeType == "ghostleg") {
        router.push({ name: "EntFeedList" });
      }

      commit("CAL_NOTICE_COUNT", -1);
      const token = localStorage.getItem("jwt");
      axios({
        method: "PUT",
        url: `${process.env.VUE_APP_MCS_URL}/noticeread/${noticeId}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          commit("CAL_NOTICE_COUNT", +1);
          console.log(err);
        });
    },
    getNoticeList({ rootState, state, commit }, $state) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/noticelist`,
        headers: { Authorization: token },
        params: {
          groupId: rootState.account.nowGroup.groupId,
          start: state.start,
        },
      })
        .then((res) => {
          const noticeList = [];
          const count = res.data.count;
          if (res.data.noticeResultDtoList.length) {
            res.data.noticeResultDtoList.forEach((notice) => {
              let message = "";
              switch (notice.noticeType) {
                case "feedCreate":
                  message = "새로운 피드를 작성했습니다.";
                  break;
                case "commentCreate":
                  message = "회원님의 글에 댓글을 작성했습니다.";
                  break;
                case "emotionCreate":
                  message = "회원님의 글에 감정표현을 했습니다.";
                  break;
                case "shareCreate":
                  message = "추억을 공유했습니다.";
                  break;
                case "voteCreate":
                  message = "새로운 투표를 작성했습니다.";
                  break;
                case "voteEnd":
                  message = "투표를 종료했습니다.";
                  break;
                case "ghostLegCreate":
                  message = "새로운 사다리타기를 작성했습니다.";
                  break;
                case "commentTagged":
                  message = "회원님을 댓글에 태그했습니다.";
                  break;
                default:
                  message = notice.noticeType + "이 작성되었습니다.";
              }
              notice["noticeMessage"] = message;
              noticeList.push(notice);
            });
          }

          return { noticeList, count };
        })
        .then((res) => {
          console.log(res);
          if (res.noticeList.length) {
            // 20개씩 인피니티스크롤로 구현
            commit("GET_NOTICE_LIST", {
              noticeList: res.noticeList,
              count: res.count,
            });
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    send({ state, rootState }, noticeInfo) {
      console.log("Send message:" + state.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          userSeq: rootState.account.userSeq,
          userName: rootState.account.userName,
          userProfileUrl: rootState.account.userImgUrl,
          targetUserSeq: noticeInfo.targetUserSeq,
          targetUserGroupId: rootState.account.nowGroup.groupId,
          noticeType: noticeInfo.noticeType,
          noticeContentId: noticeInfo.noticeContentId,
        };
        this.stompClient.send(
          `/api/notice/receive/${rootState.account.nowGroup.groupId}`,
          JSON.stringify(msg),
          {}
        );
      }
    },
    connect({ commit, rootState }) {
      const serverURL = `${process.env.VUE_APP_MCS_URL}/notice`;
      const token = localStorage.getItem("jwt");
      const headers = {
        Authorization: token,
        destination: `/api/notice/send/${rootState.account.nowGroup.groupId}`,
      };
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(
        `소켓 연결을 시도합니다. 서버 주소: ${serverURL}/notice/${rootState.account.nowGroup.groupId}`
      );
      if (!(this.stompClient && this.stompClient.connected)) {
        this.stompClient.connect(
          headers,
          (frame) => {
            // 소켓 연결 성공
            console.log("소켓 연결 성공", frame);

            // 서버의 메시지 전송 endpoint를 구독

            // 이런형태를 pub sub 구조라고 한다.
            this.stompClient.subscribe(
              `/api/notice/send/${rootState.account.nowGroup.groupId}`,
              (res) => {
                console.log("구독으로 받은 메시지 입니다.", res.body);
                // 받은 데이터를 json으로 파싱하고 저장
                const recv = JSON.parse(res.body);
                let message = "";
                switch (recv.noticeType) {
                  case "feedCreate":
                    message = "새로운 피드를 작성했습니다.";
                    break;
                  case "commentCreate":
                    message = "회원님의 글에 댓글을 작성했습니다.";
                    break;
                  case "emotionCreate":
                    message = "회원님의 글에 감정표현을 했습니다.";
                    break;
                  case "shareCreate":
                    message = "추억을 공유했습니다.";
                    break;
                  case "voteCreate":
                    message = "새로운 투표를 작성했습니다.";
                    break;
                  case "voteEnd":
                    message = "투표를 종료했습니다.";
                    break;
                  case "ghostLegCreate":
                    message = "새로운 사다리타기를 작성했습니다.";
                    break;
                  default:
                    message = notice.noticeType + "이 작성되었습니다.";
                }
                recv["noticeMessage"] = message;
                if (
                  recv.targetUserList.some(
                    (id) => id === rootState.account.userSeq
                  )
                ) {
                  commit("CAL_NOTICE_COUNT", 1);
                  commit("NOTICE_ALARM", recv);
                }

                commit("NOTICE_CLEAR");
              }
            );
          },
          (error) => {
            // 소켓 연결 실패
            console.log("소켓 연결 실패", error);
          }
        );
      }
    },
    disconnect({ commit }) {
      this.stompClient.disconnect();
      commit("NOTICE_DISCONNECT");
      console.log("연결해제");
    },
  },
  getters: {},
};

export default notice;
