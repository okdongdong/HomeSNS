import axios from "axios";
import router from "../../router";

const account = {
  namespaced: true,
  state: {
    // 샘플 데이터
    userSeq: null,
    userName: null,
    userImgUrl: null,
    nowGroup: {
      groupId: null,
      groupName: null,
      groupProfileImageUrl: null,
    },
    isLogin: localStorage.getItem("jwt") ? true : false,
  },
  mutations: {
    LOGIN: function (state, res) {
      state.isLogin = true;
      state.userSeq = res.data.userSeq;
      state.userName = res.data.userName;
      state.userImgUrl = res.data.userProfileImageUrl;
    },
    LOGOUT: function (state) {
      state.isLogin = false;
      state.userSeq = null;
      state.userName = null;
      state.userImgUrl = null;
      state.nowGroup = null;
    },
    SET_NOW_GROUP: function (state, groupInfo) {
      state.nowGroup = groupInfo;
    },
  },
  actions: {
    login: function ({ commit }, credentials) {
      // 로그인 임시연결
      // router.push({name:"Select"})
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/login`,
        data: credentials,
      })
        .then((res) => {
          localStorage.setItem("jwt", res.headers.authorization);
          console.log(res);
          commit("LOGIN", res);
          router.push({ name: "Select" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    logout: function ({ commit }) {
      localStorage.removeItem("jwt");
      router.push({ name: "Login" });
      commit("LOGOUT");
    },
    setNowGroup: function ({ commit }, groupInfo) {
      commit("SET_NOW_GROUP", groupInfo);
    },
  },
  getters: {},
};

export default account;