
const addEvent = {
  namespaced: true,
  state: {
    event: {
      startDate: '',
      startTime: '',
      endDate: '',
      endTime: '',
      content: '',
      title: '',
    },
    
    
    events: [],
    dialog: false,
    calendar: {
      startDate: '',
      startTime: '',
      hasTime: '',
    },
  
    
  },

  mutations: {
    OPEN_CALENDAR_DIALOG(state, payload) {
      state.calendar.startDate = payload.date;
      state.calendar.startTime = payload.time;
      state.calendar.hasTime = payload.hasTime;
      state.dialog = true;
  },
    CLOSE_CALENDAR_DIALOG(state) {
      state.dialog = false;
    },

    ADD_EVENTS(state, event) {
      state.events[0] = event;
      state.events[1] = event;
      state.events[2] = event;
      state.dialog = false;
    }
  },
  actions: {
    
  },

  getters: {
    
  },
};

export default addEvent;
