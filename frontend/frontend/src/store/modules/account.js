import axios from "axios";

const account = {
  namespaced: true,
  state: {
    isLogin: localStorage.getItem("jwt") ? true : false,
  },
  mutations: {
    LOGIN: function (state) {
      state.isLogin = true;
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
          commit("LOGIN");

          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
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
