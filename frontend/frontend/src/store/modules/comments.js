import axios from "axios";

const comments = {
  namespaced: true,
  state: {
    comments: [],
    offset: 0,
  },
  mutations: {
    GET_COMMENTS: function (state, comments) {
      console.log("getcomment=====================");
      console.log(comments);
      state.comments = comments.commentList;
      console.log(state.comments);
    },
  },
  actions: {
    getComments: function ({ commit, state }, commentParamDto, $state) {
      const token = localStorage.getItem("jwt");
      console.log("offset");
      console.log(state.offset);
      commentParamDto["offset"] = state.offset;
      axios({
        method: "GET",
        url: `${process.env.VUE_APP_MCS_URL}/feed/comment`,
        params: commentParamDto, //피드 아이디는 어디다가 넣어서 주면 되쥬?
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log("댓글들고옴");
          console.log(res.data);
          if (res.data.commentList.length) {
            commit("GET_COMMENTS", res.data);
            state.offset += 10;
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    createComment: function ({ dispatch }, data) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/feed/comment`,
        data: data.commentDto,
        headers: { Authorization: token },
      }).then((res) => {
        console.log("댓글 생성완료!!");
        console.log(res.data);
        //일단 가지고오는걸로 실행 , createComment 하고나서 던져주는 값 있으면
        //위로 올려보내서 리스트에 하나 추가하는걸로!
        dispatch("comments/getComments", data.commentDto.feedId, {
          root: true,
        });
      });
    },
    deleteComment({ dispatch }, data) {
      const token = localStorage.getItem("jwt");
      console.log(data);
      axios({
        method: "DELETE",
        url: `${process.env.VUE_APP_MCS_URL}/feed/comment/${data.commentId}`,
        headers: { Authorization: token },
      }).then(() => {
        dispatch("comments/getComments", data.feedId, { root: true });
      });
    },
    resetOffset({ state }) {
      state.offset = 0;
    },
  },
  getters: {},
};

export default comments;
