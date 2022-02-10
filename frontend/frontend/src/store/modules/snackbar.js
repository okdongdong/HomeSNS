// import axios from "axios";

const snackbar = {
  state: {
    snackbar: false,
    text: "",
  },
  mutations: {
    SET_SNACKBAR(state, content) {
      state.snackbar = true;
      console.log(content);
      state.text = content;
    },
    CLOSE_SNACKBAR(state) {
      state.snackbar = false;
    }
  },
  actions: {},
  getters: {},
};

export default snackbar;
