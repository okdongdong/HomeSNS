// import _ from 'lodash'
import axios from "axios";
import createPersistedState from "vuex-persistedstate";

const login = {
  plugins: [createPersistedState()],
  namespaced: true,
  state: {
    isLogin: false,
  },
  mutations: {
    LOGIN: function (state) {
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/login`,
        data: { info: state },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  actions: {
    login: function ({ commit }) {
      commit("LOGIN");
    },
  },
  getters: {},
};

export default login;
