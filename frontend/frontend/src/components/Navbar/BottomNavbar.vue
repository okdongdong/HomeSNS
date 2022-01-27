<template>
  <v-bottom-navigation
    height="80px"
  >
    <v-btn @click="moveToFeed">
      <span>게시물</span>

      <v-icon>mdi-history</v-icon>
    </v-btn>

    <v-btn @click="moveToTimeLine">
      <span>추억여행</span>

      <v-icon>mdi-heart</v-icon>
    </v-btn>

    <v-btn @click="moveToSchedule">
      <span>일정표</span>

      <v-icon>mdi-map-marker</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  name: "FrontendBottomnavbar",
  props: { noneNav: Boolean },

  data() {
    return {};
  },

  mounted() {},

  methods: {
    moveToFeed() {
      this.$router.push({ name: "Main" });
    },
    moveToTimeLine() {
      this.$router.push({ name: "Timeline" });
    },
    moveToSchedule() {
      this.$router.push({ name: "Schedule" });
    },
  },
};
</script>

<style scoped></style>
