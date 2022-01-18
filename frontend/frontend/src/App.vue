<template>
  <v-app>
    <div class="d-flex justify-center">
      <div style="width: 33%; background-color: black"></div>
      <v-main style="max-width: 450px; width: 100%">
        <top-navbar v-if="noneNav"></top-navbar>
        <router-view />
        <bottom-navbar v-if="noneNav"></bottom-navbar>
      </v-main>
      <div style="width: 33%; background-color: black"></div>
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

<style></style>
