
import axios from "axios";
import store from './account.js'

const state = {
  event: initEvent(),
  events: [],
  dialog: false,
};
//그룹 아이디 받아오기

function initEvent() {
  // console.log(store.state.account.nowGroup.groupId)
  console.log('50')
  console.log(store.state.nowGroup.groupId)
  return {
    scheduleDateStart: '',
    scheduleTimeStart: '',
    scheduleDateEnd: '',
    scheduleTimeEnd: '',
    scheduleContent: '',
    scheduleTitle: '',
    // groupId: store.state.nowGroup.groupId,
    groupId: '1',
    // groupId: this.$store.account.['nowGroup'].groupId,
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
    console.log('20')
    console.log(getEvent)
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

  SHOW_EVENT_DETAIL(state, event) {
    state.event = event;
    state.eventDetailDialog = true;
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
      const groupId = context.rootState.account.nowGroup.groupId
      // console.log(calendar)
      console.log(context)
      const addedEvent = makeEvent(calendar);
      addedEvent["groupId"] = groupId
      console.log(addedEvent)
      context.commit('ADD_EVENT', addedEvent);
      // snack bar 작업 해보기
      // store.commit('SET_SNACKBAR', setSnackBarInfo('일정이 추가 되었습니다.', 'info', 'top'))
    } catch (e) {
      console.log('일정 추가 에러' + e);
    }
  },

  async REQEUST_QUERY_EVENTS_BY_DATE(context, calendar) {
    try {
      const groupId = context.rootState.account.nowGroup.groupId
      const response = await requestQueryEvents(calendar, groupId);
      // console.log(context)
      console.log('이벤트 vuex 저장 정보')
      console.log(response.data.scheduleDto)
      context.commit('ADD_EVENTS', response.data.scheduleDto);
    } catch (e) {
      // store.commit('SET_SNACKBAR', setSnackBarInfo('이벤트 전체 조회를 실패하였습니다.', 'error', 'top'))
      console.log('불러오기에러' + e);
    }
  },

  async REQUEST_DETAIL_EVENT(context) {
    try {
      console.log(context)
      const groupId = context.rootState.account.nowGroup.groupId
      const respone = await requestEventDetail(groupId);
      context.commit('SHOW_EVENT_DETAIL', respone.data);
    } catch (e) {
      // store.commit('SET_SNACKBAR', setSnackBarInfo('이벤트 상세 조회를 실패하였습니다.', 'error', 'top'))
      console.log('이벤트 조회 에러' + e);
    }
  },
}

function requestEventDetail(scheduleId, groupId) {
  const token = localStorage.getItem("jwt")
  // console.log(date)
  return axios({
    method: "GET",
    url: `${process.env.VUE_APP_MCS_URL}/schedule/${ groupId }`,
    headers: { Authorization: token },
  })
}

function requestQueryEvents(date, groupId) {
  const token = localStorage.getItem("jwt")
  // console.log(date)
  return axios({
    method: "GET",
    url: `${process.env.VUE_APP_MCS_URL}/schedule/${ groupId }`,
    headers: { Authorization: token },
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