
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


const state = {
  event: initEvent(),
  events: [],
  dialog: false,
  calendar: {
    startDate: '',
    startTime: '',
    hasTime: '',
  },
};

function initEvent(){
  return {
    startDate: '',
    startTime: '',
    endDate: '',
    endTime: '',
    content: '',
    title: '',
  }
}

const getters = {

};

const mutations = {

  OPEN_CALENDAR_DIALOG(state, payload) {
    state.calendar.startDate = payload.date;
    state.calendar.startTime = payload.time;
    state.calendar.hasTime = payload.hasTime;
    state.dialog = true;
  },

  CLOSE_CALENDAR_DIALOG(state) {
    state.dialog = false;
  },

  ADD_EVENT(state, getEvent) {
    state.events.push(getEvent);
    state.dialog = false;
    state.event = initEvent();
  },

  ADD_EVENTS(state, event) {
    state.events[0] = event;
    state.events[1] = event;
    state.events[2] = event;
    state.dialog = false;
  }
};

const actions = {
  async REQUEST_ADD_EVENT(context, calendar) {
      try {
          const response = await requestAddEvent(calendar);

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
function requestAddEvent(calendar) {
  return axios.get(`${process.env.VUE_APP_BASEURL}/api/events`, {
    params: { calendar }
  });
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