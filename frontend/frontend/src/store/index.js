import Vue from "vue";
import Vuex from "vuex";
import account from "./modules/account";
import vote from "./modules/vote";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {},
  mutations: {},
  actions: {},
  modules: {
    account,
    vote,
  },
});
