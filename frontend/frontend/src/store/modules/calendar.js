import axios from "axios";
// import store from "@/store";


const state = {
  event: initEvent(),
  events: [],
  dialog: false,
};
//그룹 아이디 받아오기

function initEvent() {
  return {
    scheduleDateStart: '',
    scheduleTimeStart: '',
    scheduleDateEnd: '',
    scheduleTimeEnd: '',
    scheduleContent: '',
    scheduleTitle: '',
    groupId: '1',
  }
}

const getters = {};

const mutations = {

  OPEN_CALENDAR_DIALOG(state, payload) {
    state.event.scheduleDateStart = payload.date;
    state.event.scheduleTimeStart = payload.time;
    // state.event.hasTime = payload.hasTime;
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
  async REQUEST_ADD_EVENT(context, calendar) {
    try {
      const response = await requestAddEvent(calendar)
      // console.log('1')
      //안찍힘
      console.log('addEvent정보')
      console.log(response)
      const addedEvent = makeEvent(calendar);
      context.commit('ADD_EVENT', addedEvent);
      // snack bar 작업 해보기
      // store.commit('SET_SNACKBAR', setSnackBarInfo('일정이 추가 되었습니다.', 'info', 'top'))
    } catch (e) {
      console.log('일정 추가 에러' + e);
    }
  },

  async REQEUST_QUERY_EVENTS_BY_DATE({ rootState }, context, date) {
    try {
      const groupId = rootState.account.nowGroup.groupId
      const response = await requestQueryEvents(date, groupId);
      console.log(response.data)
      console.log(date)
      console.log(groupId)
      context.commit('ADD_EVENTS', response.data);
    } catch (e) {
      // store.commit('SET_SNACKBAR', setSnackBarInfo('이벤트 전체 조회를 실패하였습니다.', 'error', 'top'))
      console.log('불러오기에러' + e);
    }
  },
}

function requestQueryEvents(date, groupId) {
  const token = localStorage.getItem("jwt")
  console.log(date)
  return axios({
    method: "GET",
    url: `${process.env.VUE_APP_MCS_URL}/schedule/${ groupId }`,
    headers: { Authorization: token },
    params: { date: date }
  })
}

// 비동기 작업 axios
function requestAddEvent(event) {
  const token = localStorage.getItem("jwt")
  // console.log('2')
  // console.log(event)
  return axios({
    method: "POST",
    url: `${process.env.VUE_APP_MCS_URL}/schedule`,
    headers: { Authorization: token },
    data: event
  })
}

const colors = ['blue', 'indigo', 'deep-purple', 'green', 'orange', 'red'];

const makeEvent = (event) => {
  // console.log('5');
  // console.log(event)
  return {
    name: event.scheduleTitle,
    start: event.scheduleDateStart + getTime(event.scheduleTimeStart),
    end: event.scheduleDateEnd + getTime(event.scheduleTimeEnd),
    color: colors[Math.floor(Math.random() * 6)]
  }
};

const getTime = (time) => {
  return time === null ? '' : ` ${time}`;
};

export default {
  state,
  getters,
  actions,
  mutations,
}