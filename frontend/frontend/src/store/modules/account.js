import axios from "axios";

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
      state.userSeq = res.userSeq;
      state.userName = res.userName;
      state.userImgUrl = res.userImgUrl;
      state.userGroups = res.userGroups;
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
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/login`,
        data: credentials,
      })
        .then((res) => {
          localStorage.setItem("jwt", res.data.token);
          commit("LOGIN", res);
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    logout: function ({ commit }) {
      localStorage.removeItem("jwt");
      commit("LOGOUT");
      this.$router.push("Login")
    },
    signup: function (credentials) {
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/signup`,
        data: credentials,
      })
        .then((res) => {
          this.login(credentials);
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  getters: {},
};

export default account;
