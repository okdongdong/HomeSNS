<template>
  <div class="container">
    <div class="d-flex justify-space-between align-center">
      <h1>ENTERTAINMENT</h1>
      <EntCreatePopup />
    </div>
    <div v-for="(content, idx) in contentDataList" :key="idx">
      <EntFeedContainer :content="content" />
    </div>
    <infinite-loading @infinite="getContent"></infinite-loading>
  </div>
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
                  type: "ghost",
                  info: {
                    title: contentData.gameTitle,
                    author: contentData.gameAuthorName,
                    authorPicUrl: contentData.gameAuthorProfileImageUrl,
                    createdAt: contentData.gameRegisterDate,
                    playerNum: contentData.ghostLegDto.playerNum,
                    contentId: contentData.ghostLegDto.gameId,
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
                    author: contentData.gameAuthorName,
                    authorPicUrl: contentData.gameAuthorProfileImageUrl,
                    createdAt: contentData.gameRegisterDate,
                    myVoteItem: contentData.voteItemId
                      ? contentData.voteItemId
                      : null,
                    voteItems: contentData.voteItemDtoList,
                  },
                });
              }
              console.log(this.contentDataList);
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

<style></style>
