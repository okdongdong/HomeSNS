<template>
  <v-app id="app">
    <div class="d-flex justify-center">
      <div class="curtain"></div>

      <!-- 상단 내비바 높이맞추기 위한 태그 -->
      <v-main style="max-width: 450px; width: 100%">
        <top-navbar v-if="noneNav"></top-navbar>
        <div v-if="noneNav" style="height: 80px"></div>
        <router-view />
        <!-- 하단 내비바 높이맞추기 위한 태그 -->
        <div v-if="noneNav" style="height: 80px"></div>
        <bottom-navbar v-if="noneNav"></bottom-navbar>
      </v-main>
      <div class="curtain"></div>
    </div>
  </v-app>
</template>

<script>
import BottomNavbar from "./components/Navbar/BottomNavbar.vue";
import TopNavbar from "./components/Navbar/TopNavbar.vue";
export default {
  name: "App",
  components: {
    BottomNavbar,
    TopNavbar,
  },
  data: () => ({
    noneNavPages: [
      "SignUp",
      "Login",
      "FindId",
      "FindPassword",
      "Select",
      "GroupCreate",
    ],
  }),
  computed: {
    noneNav: function () {
      if (this.noneNavPages.includes(this.$router.app._route.name)) {
        return false;
      }
      return true;
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap");

#app {
  font-family: "Gowun Dodum", sans-serif;
}
.curtain {
  width: calc((100vw - 450px) / 2);
  background-color: rgb(50, 52, 121);
  z-index: 99;
}
</style>
