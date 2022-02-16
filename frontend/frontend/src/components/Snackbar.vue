<template>
  <div>
    <v-snackbar
      v-model="snackbar"
      :multi-line="multiLine"
      :color="color"
      :timeout="timeout"
      tile
    >
      <h3 class="text-center">{{ text }}</h3>

      <!--      <template v-slot:action="{ attrs }">-->
      <!--        <v-btn-->
      <!--          color="black"-->
      <!--          text-->
      <!--          v-bind="attrs"-->
      <!--          @click="close"-->
      <!--        >-->
      <!--          Close-->
      <!--        </v-btn>-->
      <!--      </template>-->
    </v-snackbar>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "Snackbar",

  data: () => ({
    multiLine: true,
    timeout: 2000,
  }),

  computed: {
    ...mapState("snackbar", ["color", "snackbar", "text"]),
  },

  methods: {
    close() {
      this.$store.commit("CLOSE_SNACKBAR");
    },
  },
};
</script>

<style scoped></style>
