<template>
  <v-bottom-navigation
    height="80px"
    fixed
    max-width="450"
    class="my-bar"
    style="left: calc(max(((100% - 450px) / 2), 0px))"
  >
    <v-icon
      class="pa-1 back-button"
      @click.stop="$router.go(-1)"
      color="black"
      size="36"
      >arrow_back</v-icon
    >
    <a  href="#" title="맨 위로">
      <v-icon class="to-top-button pa-1" color="black" size="36">
        arrow_upward
        </v-icon>
    </a>
    <div class="align-center" style="width: 100%">
      <v-row class="text-center pa-5">
        <v-col cols="3" class="px-0">
          <v-btn @click.stop="move('Main')">
            <h3 class="mt-1">게시물</h3>
            <v-icon>mdi-history</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="3" class="px-0">
          <v-btn @click.stop="move('Timeline')">
            <h3 class="mt-1">시간여행</h3>
            <v-icon>mdi-heart</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="3" class="px-0">
          <v-btn @click.stop="move('Location')">
            <h3 class="mt-1">추억의 장소</h3>
            <v-icon>airplane_ticket</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="3" class="px-0">
          <v-btn @click.stop="move('Calendar')">
            <h3 class="mt-1">일정표</h3>
            <v-icon>mdi-map-marker</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </div>
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
    move(page) {
      this.$router.push({ name: page });
    },
  },
};
</script>

<style scoped>
.my-bar {
  left: calc((100vw - 450px) / 2);
}
.back-button {
  left: 20px;
}
.to-top-button {
  right: 20px;
  text-decoration: none;
}
.back-button,
.to-top-button {
  display: scroll;
  position: absolute;
  top: -65px;
  z-index: 4;
  transition-duration: 1s;
  border-radius: 50%;
  background-color:rgba(255,255,255,0.8)
}
</style>
