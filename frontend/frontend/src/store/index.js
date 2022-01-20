import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import account from "./modules/account";
import vote from "./modules/vote";
import getMain from "./modules/getMain";


Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    account,
    vote,
    getMain,
  },
  plugins: [createPersistedState({ paths: ["account"] })],
});
