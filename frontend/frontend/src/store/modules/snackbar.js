// import axios from "axios";

const snackbar = {
  namespaced: true,
  state: {
    snackbar: false,
    text: "",
    color: "red accent-1",
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
    SET_SNACKBAR_OTHER_COLOR(state, content) {
      if (!state.snackbar) {
        state.snackbar = true;
        console.log(content);
        state.text = content.text;
        state.color = content.color;
        setTimeout(
          () => ((state.snackbar = false), (state.color = "red accent-1")),
          2000
        );
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
