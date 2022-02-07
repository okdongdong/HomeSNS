import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import account from "./modules/account";
import vote from "./modules/vote";
import getMain from "./modules/getMain";
import minigame from "./modules/minigame";
import addEvent from "./modules/addEvent";
import notice from "./modules/notice"

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    account,
    vote,
    getMain,
    minigame,
    addEvent,
    notice,
  },
  plugins: [createPersistedState({ paths: ["account"] })],
});
