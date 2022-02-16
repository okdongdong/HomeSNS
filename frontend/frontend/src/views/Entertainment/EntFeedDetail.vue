<template>
  <v-app class="container">
    <div class="d-flex justify-space-between align-center">
      <h1>ENTERTAINMENT</h1>
      <EntCreatePopup />
    </div>
    <EntFeedContainer
      :content="content"
      @deleteContent="$router.push({ name: 'EntFeedList' })"
    />
  </v-app>
</template>

<script>
import EntFeedContainer from "../../components/Entertainment/EntFeedContainer.vue";
import EntCreatePopup from "../../components/Entertainment/EntCreatePopup.vue";
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "EntFeedDetail",
  props: {
    contentType: String,
    contentId: Number,
  },
  data: () => ({
    content: null,
  }),
  components: {
    EntFeedContainer,
    EntCreatePopup,
  },
  mounted() {
    this.getContent();
  },
  methods: {
    getContent() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/game/${this.contentType}/${this.contentId}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          const contentData = res.data.gameDto;
          console.log(res);
          if (contentData.code == "40001") {
            const tempEdge = [];
            contentData.ghostLegDto.map.split(",").forEach((arr) => {
              tempEdge.push(arr.split(""));
            });
            const tempPlayerNames = contentData.ghostLegDto.player.split(",");
            const tempResultNamse = contentData.ghostLegDto.result.split(",");
            this.content = {
              type: "ghostLeg",
              info: {
                title: contentData.gameTitle,
                authorId: contentData.gameAuthor,
                author: contentData.gameAuthorName,
                authorPicUrl: contentData.gameAuthorProfileImageUrl,
                createdAt: contentData.gameRegisterDate,
                playerNum: contentData.ghostLegDto.playerNum,
                contentId: contentData.gameId,
                isEdge: tempEdge,
                playerNames: tempPlayerNames,
                resultNames: tempResultNamse,
              },
            };
          } else if (contentData.code == "40002") {
            this.content = {
              type: "vote",
              info: {
                title: contentData.gameTitle,
                authorId: contentData.gameAuthor,
                author: contentData.gameAuthorName,
                updateYn: contentData.updateYn,
                authorPicUrl: contentData.gameAuthorProfileImageUrl,
                createdAt: contentData.gameRegisterDate,
                contentId: contentData.gameId,
                myVoteItem: contentData.voteItemId
                  ? contentData.voteItemId
                  : null,
                voteItems: contentData.voteItemDtoList,
              },
            };
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  watch: {
    contentId() {
      this.getContent();
    },
  },

  computed: {
    ...mapState("account", ["nowGroup"]),
  },
};
</script>

<style></style>
