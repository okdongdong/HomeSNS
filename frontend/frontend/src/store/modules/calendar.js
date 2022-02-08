// const addEvent = {
//   namespaced: true,


//   state: {
//     event: {
//       startDate: '',
//       startTime: '',
//       endDate: '',
//       endTime: '',
//       content: '',
//       title: '',
//     },
//     events: [],
//     dialog: false,
// calendar: {
//   startDate: '',
//   startTime: '',
//   hasTime: '',
// },
//   },

//   mutations: {
//     OPEN_CALENDAR_DIALOG(state, payload) {
//       state.calendar.startDate = payload.date;
//       state.calendar.startTime = payload.time;
//       state.calendar.hasTime = payload.hasTime;
//       state.dialog = true;
//   },
//     CLOSE_CALENDAR_DIALOG(state) {
//       state.dialog = false;
//     },

//     ADD_EVENT(state, getEvent) {
//       state.events.push(getEvent);
//       state.dialog = false;
//       // state.event = initEvent();
//   }
//   },
//   actions: {

//   },

//   getters: {

//   },
// };

// export default addEvent;

import axios from "axios";
// import store from "@/store";


const state = {
  event: initEvent(),
  events: [],
  dialog: false,
};

function initEvent() {
  return {
    startDate: '',
    startTime: '',
    endDate: '',
    endTime: '',
    content: '',
    title: '',
    groupId:'1',
  }
}

const getters = {};

const mutations = {

  OPEN_CALENDAR_DIALOG(state, payload) {
    state.event.startDate = payload.date;
    state.event.startTime = payload.time;
    state.event.hasTime = payload.hasTime;
    state.dialog = true;
  },

  CLOSE_CALENDAR_DIALOG(state) {
    state.dialog = false;
  },

  ADD_EVENT(state, getEvent) {
    state.events.push(getEvent);
    state.event = initEvent();
    state.dialog = false;
  },

  ADD_EVENTS(state, events) {
    state.events = [];
    events.forEach(e => {
      state.events.push(makeEvent(e));
    })
  },

};

const actions = {
  async REQUEST_ADD_EVENT(context, event) {
    try {
      const response = await requestAddEvent(event);
      console.log(response.data)
      const addedEvent = makeEvent(response.data);
      context.commit('ADD_EVENT', addedEvent);
      // snack bar 작업 해보기
      // store.commit('SET_SNACKBAR', setSnackBarInfo('일정이 추가 되었습니다.', 'info', 'top'))
    } catch (e) {
      console.log('일정 추가 에러' + e);
    }
  },
}

// 비동기 작업 axios
function requestAddEvent(event) {
  const token = localStorage.getItem("jwt")
  console.log(event)
  return axios({
    method: "POST",
    url: `${process.env.VUE_APP_MCS_URL}/schedule`,
    headers: { Authorization: token },
    data: event
  })
    // .then(res => res);
    .then((res) => {
      console.log(res);
      console.log('1');
    });
    // .catch((err) => {
    //   console.log(err)
    // });
}

const colors = ['blue', 'indigo', 'deep-purple', 'green', 'orange', 'red'];

const makeEvent = (event) => {
  return {
    name: event.title,
    start: event.startDate + getTime(event.startTime),
    end: event.endDate + getTime(event.endTime),
    color: colors[Math.floor(Math.random() * 6)]
  }
};

function getTime() {

}


export default {
  state,
  getters,
  actions,
  mutations,
}