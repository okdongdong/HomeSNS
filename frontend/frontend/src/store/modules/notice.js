import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

const notice = {
  namespaced: true,
  state: {
    recv: { userProfileUrl: null, userSeq: null, userName: null },
    noticeAlarm: false,
  },
  mutations: {
    NOTICE_ALARM(state) {
      state.noticeAlarm = true;
      setTimeout(() => (state.noticeAlarm = false), 2000);
    },
  },
  actions: {
    send({ state, rootState }, noticeInfo) {
      console.log("Send message:" + state.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          userSeq: rootState.account.userSeq,
          userName: rootState.account.userName,
          userImgUrl: rootState.account.userImgUrl,
          targetUserSeq: noticeInfo.targetUserSeq,
          targetUserGroupId: rootState.account.nowGroup.groupId,
          noticeType: noticeInfo.noticeType,
          noticeContentId: noticeInfo.noticeContentId,
        };
        this.stompClient.send(
          `/notice/receive/${rootState.account.nowGroup.groupId}`,
          JSON.stringify(msg),
          {}
        );
      }
    },
    connect({ state, commit, rootState }) {
      const serverURL = `${process.env.VUE_APP_MCS_URL}/notice`;
      const token = localStorage.getItem("jwt");
      const headers = {
        Authorization: token,
        destination: `/notice/send/${rootState.account.nowGroup.groupId}`,
      };
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(
        `소켓 연결을 시도합니다. 서버 주소: ${serverURL}/notice/${rootState.account.nowGroup.groupId}`
      );
      this.stompClient.connect(
        headers,
        (frame) => {
          // 소켓 연결 성공
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독

          // 이런형태를 pub sub 구조라고 한다.
          this.stompClient.subscribe(
            `/notice/send/${rootState.account.nowGroup.groupId}`,
            (res) => {
              console.log("구독으로 받은 메시지 입니다.", res.body);

              // 받은 데이터를 json으로 파싱하고 저장
              state.recv = JSON.parse(res.body);
              if (
                state.recv.targetUserList.some(
                  (id) => id === rootState.account.userSeq
                )
              ) {
                commit("NOTICE_ALARM");
              }
            }
          );
        },
        (error) => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
        }
      );
    },
    disconnect() {
      this.stompClient.disconnect();
      console.log("연결해제");
    },
  },
  getters: {},
};

export default notice;
