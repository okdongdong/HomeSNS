// import axios from "axios";

const snackbar = {
  namespaced: true,
  state: {
    snackbar: false,
    text: "",
  },
  mutations: {
    SET_SNACKBAR(state, content) {
      if (!state.snackbar) {
        state.snackbar = true;
        console.log(content);
        state.text = content;
        setTimeout(() => (state.snackbar = false), 2000);
      }
    },
    CLOSE_SNACKBAR(state) {
      state.snackbar = false;
    },
  },
  actions: {},
  getters: {},
};

export default snackbar;
