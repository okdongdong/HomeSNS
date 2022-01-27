import axios from "axios";
import router from "../../router";

const account = {
  namespaced: true,
  state: {
    // 샘플 데이터
    userSeq: 5,
    userName: "부와아아앜",
    userImgUrl: "https://pbs.twimg.com/media/CLVCqrsVEAAe9oo.jpg",
    userGroups: [1, 2, 3],
    nowUserGroup: 1,
    isLogin: localStorage.getItem("jwt") ? true : false,
  },
  mutations: {
    LOGIN: function (state, res) {
      state.isLogin = true;
      state.userSeq = res.data.userSeq;
      state.userName = res.data.userName;
      state.userImgUrl = res.data.userProfileImageUrl;
      state.userGroups = res.data.userGroups;
    },
    LOGOUT: function (state) {
      state.isLogin = false;
      state.userSeq = null;
      state.userName = null;
      state.userImgUrl = null;
      state.userGroups = null;
      state.nowUserGroup = null;
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
          localStorage.setItem("jwt", res.data.token);
          commit("LOGIN", res);
          console.log(res);
          console.log(res.data);
          router.push({name:"Select"})
        })
        .catch((err) => {
          console.log(err);
        });
    },
    logout: function ({ commit }) {
      localStorage.removeItem("jwt");
      router.push({name:"Login"})
      commit("LOGOUT");
    },
  },
  getters: {},
};

export default account;
