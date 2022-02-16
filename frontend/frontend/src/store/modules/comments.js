import axios from "axios";

const comments = {
  namespaced: true,
  state: {
    comments:[],
    offset :0,
  },
  mutations: {
    GET_COMMENTS : function(state, comments){
      state.comments.push(...comments.commentList);
    },
    CREATE_COMMENT:function(state, comment){
      state.comments.unshift(comment.comment)
      console.log(state.comments)
    },
    DELETE_COMMENT : function(state,data){
      for(let i=0; i< state.comments.length;i++){
        if(state.comments[i].commentId == data.commentId){
          state.comments.splice(i,1)
          break
        }
      }
    }
  },
  actions: {
    
    getComments:function({commit,state},data){
      const token = localStorage.getItem("jwt");
      data.commentParamDto["offset"]=state.offset
      axios({
        method : "GET",
        url :`${process.env.VUE_APP_MCS_URL}/feed/comment`,
        params : data.commentParamDto, //피드 아이디는 어디다가 넣어서 주면 되쥬?
        headers: { Authorization: token },
      })
      .then((res)=>{
        console.log('댓글들고옴')
        console.log(res.data)
        if(res.data.commentList.length){
          commit(('GET_COMMENTS'),res.data);
          state.offset += 10;
          data.state.loaded();
        }else{
          console.log('더이상 들고올 데이터 없음')
          data.state.complete();
        }
      })
      .catch((err)=>{
        console.log(err);
      })
    },
    createComment:function({commit},data){
      const token = localStorage.getItem("jwt");
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/feed/comment`,
        data: data.commentDto,
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log('댓글 생성완료!!')
        console.log(res.data)
        commit(('CREATE_COMMENT'),res.data)
      });
    },
    deleteComment({commit},data){
      const token = localStorage.getItem("jwt");
      console.log(data)
      axios({
        method : "DELETE",
        url: `${process.env.VUE_APP_MCS_URL}/feed/comment/${data.commentId}`,
        headers : { Authorization: token },
      })
      .then(()=>{
        commit(('DELETE_COMMENT'),data);
      })
    },
    resetOffset({state}){
      state.offset = 0
      state.comments =[]
    }
  },
  getters: {},
};

export default comments;
