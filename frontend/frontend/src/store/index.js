import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import account from "./modules/account";
import vote from "./modules/vote";
import getMain from "./modules/getMain";
import minigame from "./modules/minigame";
import calendar from "./modules/calendar";
import notice from "./modules/notice";
import snackbar from "./modules/snackbar";
import group from "./modules/group";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    account,
    vote,
    getMain,
    minigame,
    calendar,
    notice,
    snackbar,
    group,
  },
  plugins: [createPersistedState({ paths: ["account"] })],
});
