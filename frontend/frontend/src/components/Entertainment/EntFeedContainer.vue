<template>
  <div>
    <v-overlay :value="nowLoading">
      <v-progress-circular
        :size="100"
        :width="10"
        color="white"
        indeterminate
      ></v-progress-circular>
    </v-overlay>

    <div class="d-flex px-3 mt-5 align-center">
      <h2 class="d-inline-flex">{{ content.info.createdAt }}</h2>
      <span class="d-flex ml-auto">{{ contentType }}</span>
    </div>
    <div class="container content-box">
      <div class="d-flex">
        <div class="d-flex align-center">
          <ProfilePhoto
            :size="50"
            :userSeq="content.info.authorId"
            :imgUrl="content.info.authorPicUrl"
            class="mx-3"
          />
          <div>
            <h2>{{ content.info.title }}</h2>
            <p>{{ content.info.author }}</p>
          </div>
        </div>
        <div class="ma-2 d-flex ml-auto align-center">
          <EntDeletePopup
            v-if="content.info.authorId == userSeq"
            @deleteEntFeed="deleteEntFeed()"
          />
        </div>
      </div>

      <div v-if="content.type == 'vote'">
        <Vote :info="content.info" />
      </div>
      <div v-else-if="content.type == 'ghost'">
        <GhostLeg :info="content.info" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import GhostLeg from "./GhostLeg.vue";
import Vote from "./Vote.vue";
import ProfilePhoto from "../ProfilePhoto.vue";
import { mapState } from "vuex";
import EntDeletePopup from "./EntDeletePopup.vue";
export default {
  name: "EntFeedContainer",
  props: {
    content: Object,
    idx: Number,
  },
  components: {
    GhostLeg,
    Vote,
    ProfilePhoto,
    EntDeletePopup,
  },
  data: () => ({
    nowLoading: false,
    dialog: false,
  }),
  methods: {
    deleteEntFeed() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "delete",
        url: `${process.env.VUE_APP_MCS_URL}/game/${this.content.info.contentId}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          this.$emit("deleteContent", this.idx);
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    ...mapState("account", ["userSeq"]),
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
  /* border: solid 2px black; */
  border-radius: 10px;
  background-color: rgba(255,255,255,0.6);
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
