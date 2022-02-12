<template>
  <v-app class="pa-5">
    <v-card>
      <v-container>
        <v-row>
          <v-col>
            <v-menu
              ref="dateOpen"
              v-model="dateOpen"
              :close-on-content-click="false"
              :return-value.sync="start"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="start"
                  label="Start Date"
                  prepend-icon="mdi-calendar"
                  dense
                  readonly
                  outlined
                  hide-details
                  v-on="on"
                ></v-text-field>
              </template>

              <v-date-picker v-model="start" no-title>
                <v-btn text color="primary" @click="dateOpen = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.dateOpen.save(start)"
                >OK
                </v-btn
                >
              </v-date-picker>
            </v-menu>
          </v-col>

          <v-col>
            <v-select
              v-model="type"
              :items="typeOptions"
              label="Type"
              class="my-auto"
              hide-details
              outlined
              dense
            ></v-select>
          </v-col>
        </v-row>
      </v-container>
      <div class="text-center mb-3 display-1">
        {{ start | moment("YYYY년 MM월") }}
      </div>
      <v-sheet height="750px">
        <v-calendar
          :event-color="getEventColor"
          :events="events"
          :start="start"
          :type="type"
          @click:date="open"
          @click:event="showEvent"
          @click:more="moreEvent"
          @click:time="open"
          ref="calendar"
          v-model="start"
        ></v-calendar>
      </v-sheet>
    </v-card>
    <EventDialog></EventDialog>
    <EventDetail></EventDetail>
    <Snackbar></Snackbar>
  </v-app>
</template>

<script>
import EventDialog from "@/components/EventDialog.vue";
import EventDetail from "@/components/EventDetail.vue";
import Snackbar from "@/components/Snackbar.vue";

export default {
  name: "Calendar",
  components: {
    EventDialog,
    EventDetail,
    Snackbar,
  },

  data() {
    return {
      dateOpen: false,
      // start: '',
      start: null,
      type: "month",
      typeOptions: [
        {text: "Day", value: "day"},
        {text: "Week", value: "week"},
        {text: "Month", value: "month"},
      ],
    };
  },

  created() {
    this.start = this.$moment(new Date()).format("YYYY-MM-DD");
  },

  methods: {
    open(date) {
      this.$store.commit("OPEN_CALENDAR_DIALOG", date);
    },

    showEvent({event}) {
      console.log('로그')
      console.log(event);
      this.$store.dispatch('REQUEST_DETAIL_EVENT', event.eventId);
    },
    moreEvent() {
      this.type = "day"
      console.log(this.typeOptions[0].text)
    }
  },

  computed: {
    events() {
      return this.$store.state.calendar.events;
    },
  },

  watch: {
    start(newDate, oldDate) {
      let newDateMonth = this.$moment(newDate).format("YYYY-MM");
      let oldDateMonth = this.$moment(oldDate).format("YYYY-MM");
      console.log("newDateMonth", newDateMonth);
      console.log("oldDateMonth", oldDateMonth);
      if (newDateMonth !== oldDateMonth) {
        this.$store.dispatch("REQEUST_QUERY_EVENTS_BY_DATE", newDate);
      }
    },
  },
};
</script>
