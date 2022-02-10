<template>
  <v-app>
    aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    <Notice />
    bbbbbbbbbbbbbbbbbbbbbbbb
  </v-app>
</template>

<script>
import { mapActions } from "vuex";
import Notice from "../../components/Navbar/Notice.vue";

export default {
  name: "VoteList",
  components: {
    Notice,
  },
  methods: {
    ...mapActions(["getVote"]),
  },
};
</script>

<style></style>
