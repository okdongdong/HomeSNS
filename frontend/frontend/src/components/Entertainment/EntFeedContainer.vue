<template>
  <div>
    <div>
      <h1 class="d-inline-flex">{{ content.info.createdAt }}</h1>
      <span>{{ contentType }}</span>
    </div>
    <div class="container content-box">
      <div>
        <img
          class="profile-img"
          :src="content.info.authorPicUrl"
          alt="유저프로필사진"
        />

        <h3 class="d-inline-flex">{{ contentType }}</h3>
        <span> {{ content.info.title }} </span>
        <span> {{ content.info.author }} </span>
      </div>

      <div v-if="content.type === 'vote'">
        <Vote :info="content.info" />
      </div>
      <div v-else-if="content.type === 'ghost'">
        <GhostLeg :info="content.info" />
      </div>
    </div>
  </div>
</template>

<script>
import GhostLeg from "./GhostLeg.vue";
import Vote from "./Vote.vue";
export default {
  name: "EntFeedContainer",
  props: {
    content: Object,
  },
  components: {
    GhostLeg,
    Vote,
  },
  computed: {
    contentType() {
      if (this.content.type === "vote") {
        return "투표";
      } else if (this.content.type === "ghost") {
        return "사다리타기";
      } else {
        return null;
      }
    },
  },
};
</script>

<style scoped>
.content-box {
  border: solid 2px black;
  border-radius: 25px;
  background-color: rgb(245, 245, 245);
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329);
}

.profile-img {
  overflow: hidden;
  width: 50px;
  height: 50px;

  border: 2px solid white;
  border-radius: 50%;
  /* box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329); */
  object-fit: cover;
}
</style>
