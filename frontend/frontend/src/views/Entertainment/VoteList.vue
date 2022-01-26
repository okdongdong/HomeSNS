<template>
  <v-app>
    aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    <Vote />
    bbbbbbbbbbbbbbbbbbbbbbbb
  </v-app>
</template>

<script>
import Vote from "@/components/Entertainment/Vote.vue";
import { mapActions } from "vuex";

export default {
  name: "VoteList",
  components: {
    Vote,
  },
  methods: {
    ...mapActions(["getVote"]),
  },
};
</script>

<style></style>
