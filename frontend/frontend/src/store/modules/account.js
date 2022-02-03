import axios from "axios";
import router from "../../router";

const account = {
  namespaced: true,
  state: {
    // 샘플 데이터
    userSeq: 5,
    userName: "김영철",
    userImgUrl:
      "https://image.ajunews.com/content/image/2016/12/26/20161226142046950664.jpg",
    userGroups: [1, 2, 3],
    nowUserGroup: 1,
    isLogin: localStorage.getItem("jwt") ? true : false,
  },
  mutations: {
    LOGIN: function (state, res) {
      state.isLogin = true;
      // state.userSeq = 5;
      // state.userName = "김영철";
      // state.userImgUrl =
      //   "https://image.ajunews.com/content/image/2016/12/26/20161226142046950664.jpg";
      state.userSeq = res.data.userSeq;
      state.userName = res.data.userName;
      state.userImgUrl = res.data.userProfileImageUrl;
      state.userGroups = res.data.userSeq;
    },
    LOGOUT: function (state) {
      state.isLogin = false;
      state.userSeq = null;
      state.userName = null;
      state.userImgUrl = null;
      state.userGroups = null;
      state.nowUserGroup = null;
    },
    SET_NOW_GROUP: function (state, groupId) {
      state.nowUserGroup = groupId;
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
    setNowGroup: function ({ commit }, groupId) {
      commit("SET_NOW_GROUP", groupId);
      router.push({
        name: "Main",
        parmas: {
          groupId: groupId,
        },
      });
    },
  },
  getters: {},
};

export default account;
