<template>
<v-app class="pa-5">
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

          <v-date-picker
            v-model="start"
            no-title
          >
            <v-btn text color="primary" @click="dateOpen = false">Cancel</v-btn>
            <v-btn text color="primary" @click="$refs.dateOpen.save(start)">OK</v-btn>
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
    <div class="text-center mb-3 display-1">
      {{start | moment('YYYY MMMM')}}
    </div>
    <v-sheet height="500">
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
    <EventDialog></EventDialog>
</v-app>
</template>

<script>
import EventDialog from '@/components/EventDialog.vue'
// 1

export default {
  name: 'Calendar',
  components: {
    EventDialog
  },

  data() {
    return {
      dateOpen: false,
      start: this.$moment(new Date()).format('YYYY-MM-DD'),
      type: 'month',
      typeOptions: [
        {text: 'Day', value: 'day'},
        {text: 'Week', value: 'week'},
        {text: 'Month', value: 'month'},
      ],
    }
  },

  methods: {
    open(date) {
      console.log('1')
      this.$store.commit('OPEN_CALENDAR_DIALOG', date)
    }
  },
}
</script>