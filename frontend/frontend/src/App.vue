<template>
  <v-app>
    <v-container-fluid class="d-flex justify-center">
      <div style="width: 33%; background-color: black">
      </div>
      <v-main style="max-width: 450px; width: 100%">
        <top-navbar v-if="$route.name!=='GroupCreate'"></top-navbar>
        <router-view/>
        <bottom-navbar v-if="$route.name!=='GroupCreate'"></bottom-navbar>
      </v-main>
      <div style="width: 33%; background-color: black">
      </div>
    </v-container-fluid>
  </v-app>
</template>

<script>
import BottomNavbar from './components/Navbar/BottomNavbar.vue';
import TopNavbar from './components/Navbar/TopNavbar.vue';
export default {
  name: "App",
  components :{
    BottomNavbar,
    TopNavbar,
  },
  
};
</script>

<style></style>
