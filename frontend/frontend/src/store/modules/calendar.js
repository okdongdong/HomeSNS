import axios from "axios";
// import store from './account.js'

const state = {
  event: {
    scheduleId:null,
    scheduleDateStart: "",
    scheduleTimeStart: "",
    scheduleDateEnd: "",
    scheduleTimeEnd: "",
    scheduleContent: "",
    scheduleTitle: "",
  },
  events: [],
  dialog: false,
};
//그룹 아이디 받아오기

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
    state.dialog = false;
  },

  ADD_EVENTS(state, events) {
    state.events = [];
    events.forEach((e) => {
      state.events.push(makeEvent(e));
    });
  },

  SHOW_EVENT_DETAIL(state, event) {
    state.event = event;
    state.eventDetailDialog = true;
  },
};

const actions = {
  async REQUEST_ADD_EVENT(context, calendar) {
    try {
      // 전송할 데이터를 양식에 맞춰서 준비
      const data = makeData(calendar);

      // actions밖에서는 rootState를 호출하지 못하므로 여기서 호출
      const groupId = context.rootState.account.nowGroup.groupId;
      // 전송할 데이터에 끼워넣어줌
      data["groupId"] = groupId;

      const response = await requestAddEvent(data);
      console.log("일정 추가 성공", response);

      // 일정 추가 후 프론트에서 바로 보여지도록 state에 저장
      // => 근데 이렇게하면 scheduleId가 없어서 상세조회가 불가능해서 그냥 새로고침되도록 만드는게 나을 듯
      const addedEvent = makeEvent(calendar);
      context.commit("ADD_EVENT", addedEvent);
      // store.commit('SET_SNACKBAR', setSnackBarInfo('일정이 추가 되었습니다.', 'info', 'top'))
    } catch (e) {
      console.log("일정 추가 에러" + e);
    }
  },

  async REQEUST_QUERY_EVENTS_BY_DATE(context, calendar) {
    try {
      const groupId = context.rootState.account.nowGroup.groupId;
      const response = await requestQueryEvents(calendar, groupId);
      // console.log(context)
      console.log("이벤트 vuex 저장 정보");
      console.log(response.data.scheduleDto);
      context.commit("ADD_EVENTS", response.data.scheduleDto);
    } catch (e) {
      // store.commit('SET_SNACKBAR', setSnackBarInfo('이벤트 전체 조회를 실패하였습니다.', 'error', 'top'))
      console.log("불러오기에러" + e);
    }
  },

  async REQUEST_DETAIL_EVENT(context) {
    try {
      console.log(context);
      const groupId = context.rootState.account.nowGroup.groupId;
      const respone = await requestEventDetail(groupId);
      context.commit("SHOW_EVENT_DETAIL", respone.data);
    } catch (e) {
      // store.commit('SET_SNACKBAR', setSnackBarInfo('이벤트 상세 조회를 실패하였습니다.', 'error', 'top'))
      console.log("이벤트 조회 에러" + e);
    }
  },
};

function requestEventDetail(scheduleId, groupId) {
  const token = localStorage.getItem("jwt");
  // console.log(date)
  return axios({
    method: "GET",
    url: `${process.env.VUE_APP_MCS_URL}/schedule/${groupId}`,
    headers: { Authorization: token },
  });
}

function requestQueryEvents(date, groupId) {
  const token = localStorage.getItem("jwt");
  // console.log(date)
  return axios({
    method: "GET",
    url: `${process.env.VUE_APP_MCS_URL}/schedule/${groupId}`,
    headers: { Authorization: token },
  });
}

// 비동기 작업 axios
function requestAddEvent(event) {
  const token = localStorage.getItem("jwt");
  // console.log('2')
  // console.log(event)
  return axios({
    method: "POST",
    url: `${process.env.VUE_APP_MCS_URL}/schedule`,
    headers: { Authorization: token },
    data: event,
  });
}

const colors = ["blue", "indigo", "deep-purple", "green", "orange", "red"];

// 요청보낼 때 사용할 데이터
const makeData = (event) => {
  return {
    scheduleDateStart: event.scheduleDateStart,
    scheduleTimeStart: getTime(event.scheduleTimeStart),
    scheduleDateEnd: event.scheduleDateEnd,
    scheduleTimeEnd: getTime(event.scheduleTimeEnd),
    scheduleContent: event.scheduleContent,
    scheduleTitle: event.scheduleTitle,
  };
};

const makeEvent = (event) => {
  // console.log('5');
  // console.log(event)
  return {
    name: event.scheduleTitle,
    start: event.scheduleDateStart + getTime(event.scheduleTimeStart),
    end: event.scheduleDateEnd + getTime(event.scheduleTimeEnd),
    color: colors[Math.floor(Math.random() * 6)],
  };
};

const getTime = (time) => {
  return time === null ? "" : ` ${time}`;
};

export default {
  state,
  getters,
  actions,
  mutations,
};
