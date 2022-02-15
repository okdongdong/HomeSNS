<template>
  <v-app class="container">
    <div class="d-flex justify-space-between align-center">
      <h1>ENTERTAINMENT</h1>
      <EntCreatePopup />
    </div>
    <div v-if="!contentDataList.length" class="content-box pb-5">
      <div class="justify-center d-flex">
        <h3 class="mt-5">아직 작성된 피드가 없습니다.</h3>
      </div>
    </div>
    <div v-for="(content, idx) in contentDataList" :key="idx">
      <EntFeedContainer
        :content="content"
        :idx="idx"
        @deleteContent="deleteContent(idx)"
      />
    </div>
    <infinite-loading @infinite="getContent"></infinite-loading>
  </v-app>
</template>

<script>
import EntFeedContainer from "../../components/Entertainment/EntFeedContainer.vue";
import EntCreatePopup from "../../components/Entertainment/EntCreatePopup.vue";
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "EntFeedList",
  data: () => ({
    start: 0,
    contentDataList: [],
  }),
  components: {
    EntFeedContainer,
    EntCreatePopup,
  },
  created() {},
  beforeUpdate() {},
  methods: {
    deleteContent(idx) {
      this.contentDataList.splice(idx, 1);
      console.log(idx);
    },
    getContent($state) {
      const token = localStorage.getItem("jwt");

      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/game`,
        headers: { Authorization: token },
        params: {
          groupId: this.nowGroup.groupId,
          offset: this.start,
          limit: 10,
        },
      })
        .then((res) => {
          console.log(res);
          if (res.data.gameDtoList.length) {
            res.data.gameDtoList.forEach((contentData) => {
              if (contentData.code == "40001") {
                const tempEdge = [];
                contentData.ghostLegDto.map.split(",").forEach((arr) => {
                  tempEdge.push(arr.split(""));
                });
                const tempPlayerNames =
                  contentData.ghostLegDto.player.split(",");
                const tempResultNamse =
                  contentData.ghostLegDto.result.split(",");
                this.contentDataList.push({
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
                });
              } else if (contentData.code == "40002") {
                this.contentDataList.push({
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
                    preVoteItem: contentData.voteItemId
                      ? contentData.voteItemId
                      : null,
                    voteItems: contentData.voteItemDtoList,
                  },
                });
              }
              this.start += 10;
              $state.loaded();
            });
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    ...mapState("account", ["nowGroup"]),
  },
};
</script>

<style scoped>
.content-box {
  /* border: solid 2px black; */
  border-radius: 5px;
  background-color: rgba(255, 255, 255, 0.5);
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329);
}
</style>
