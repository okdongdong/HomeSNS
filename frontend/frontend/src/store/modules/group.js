import axios from "axios";

const group = {
  namespaced: true,
  state: {
    userSeq: null,
    userProfileImageUrl: null,
    userName: null,
  },
  mutations: {
    SET_TARGET_USER(state, userInfo) {
      state.userSeq = userInfo.userSeq;
      state.userProfileImageUrl = userInfo.userProfileImageUrl;
      state.userName = userInfo.userName;
    },
  },
  actions: {
    getProfile({ commit }, userSeq) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/user/profile/${userSeq}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log("유저정보조회:" + userSeq, res);
          commit("SET_TARGET_USER", res.data.userDto);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  getters: {},
};

export default group;
