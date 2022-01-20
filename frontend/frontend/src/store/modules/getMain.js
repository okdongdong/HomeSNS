// import _ from 'lodash'
import axios from "axios";

const getMain = {
  namespaced: true,
  state: {
  },
  mutations: {
    
  },
  actions: {
    getMain : function(groupId){
      // 요청시 토큰 headers 설정하기 
      // console에 데이터 찍어보고 값 main에 넘기기 -> mapActions
      axios({
        method : 'get',
        url : `${process.env.VUE_APP_MCS_URL}/main/${groupId}`,
        // headers rootState.login.token: 
      })
      .then((res) =>{
        console.log(res)
      })
      .catch((err) => {
        console.log(err);
      });
    }
  },
  getters: {},
};

export default getMain;
