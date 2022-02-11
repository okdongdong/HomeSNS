import axios from "axios";
import router from "../../router";

const vote = {
  state: {
    voteInfo: {
      voteName:null,
      voteItems: {},
      myVoteItem: null,
    },
  },
  mutations: {
    
    GET_VOTE: function (state, voteInfo) {
      state.voteInfo = voteInfo;
    },
    MY_VOTE: function (state, item) {
      if (!state.myVoteItem) {
        state.voteInfo.voteItems[item] += 1;
        state.voteInfo.myVoteItem = item;
      } else if (state.voteInfo.myVoteItem === item) {
        state.voteInfo.voteItems[state.voteInfo.myVoteItem] -= 1;
        state.voteInfo.myVoteItem = null;
      } else {
        state.voteItems[state.voteInfo.myVoteItem] -= 1;
        state.voteInfo.voteItems[item] += 1;
        state.voteInfo.myVoteItem = item;
      }
    },
  },
  actions: {
    // 투표생성
    voteCreate: function ({ commit }, voteInfo) {
      const token = localStorage.getItem("jwt");
      // 연결전에 테스트용
      router.push({name:"VoteList"})
      
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/vote`,
        headers: { Authorization: token },
        data: voteInfo,
      })
        .then((res) => {
          commit("GET_VOTE", voteInfo);
          console.log(res);
          router.push({name:"VoteList"})
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 투표조회
    getVote: function ({ commit }) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/vote`,
        headers: { Authorization: `JWT ${token}` },
      })
        .then((res) => {
          commit("GET_VOTE", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 투표하기
    myVote: function ({ commit }, item) {
      commit("MY_VOTE", item);
    },
  },
  getters: {},
};

export default vote;
