<template>
  <v-bottom-navigation
    height="80px"
    fixed
    max-width="450"
    class="my-bar"
    style="left: calc(max(((100% - 450px) / 2), 0px))"
  >
    <v-row class="align-center py-4 mx-0" style="width: 100%">
      <v-row class="text-center px-5">
        <v-col cols="3" class="px-0">
          <v-btn
            @click.stop="move('Main')"
            style="background-color: rgba(0, 0, 0, 0)"
            class="py-5"
          >
            <h3 class="mt-1">게시물</h3>
            <v-icon>home</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="3" class="px-0">
          <v-btn
            @click.stop="move('Timeline')"
            style="background-color: rgba(0, 0, 0, 0)"
            class="py-5"
          >
            <h3 class="mt-1">추억 여행</h3>
            <v-icon>access_time</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="3" class="px-0">
          <v-btn
            @click.stop="move('Location')"
            style="background-color: rgba(0, 0, 0, 0)"
            class="py-5"
          >
            <h3 class="mt-1">추억의 장소</h3>
            <v-icon>airplane_ticket</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="3" class="px-0">
          <v-btn
            @click.stop="move('Calendar')"
            style="background-color: rgba(0, 0, 0, 0)"
            class="py-5"
          >
            <h3 class="mt-1">일정표</h3>
            <v-icon>date_range</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-row>
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

</style>
