<template>
  <v-app>
    <v-container-fluid class="d-flex justify-center">
      <div style="width: 33%; background-color: black">
        123123
      </div>
      <v-main style="max-width: 450px; width: 100%">
        <router-view/>
      </v-main>
      <div style="width: 33%; background-color: black">
        123123123123123
      </div>
    </v-container-fluid>
  </v-app>
</template>

<script>
export default {
  name: "App",
  components :{

  },
  data: () => ({
    //
  }),
};
</script>

<style></style>
