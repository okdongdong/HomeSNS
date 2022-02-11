<template>
  <div class="container content-box mb-2">
    <v-row class="ma-1">
      <v-col class="pa-0" cols="4" v-for="feed in feeds" :key="feed.id">
        <v-img
          class="feed-img"
          aspect-ratio="1"
          :src="feed.fileList[0].fileUrl"
          alt="피드사진"
          @click.stop="
            $router.push({
              name: 'Detail',
              params: { feedId: feed.feedId },
            })
          "
        />
      </v-col>
    </v-row>
    <infinite-loading @infinite="getScrap"></infinite-loading>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "UserPageScrap",
  props: {
    userSeq: Number,
  },
  data: () => ({
    feeds: [],
    offset: 0,
  }),
  methods: {
    getScrap: function ($state) {
      console.log("스크랩 조회");
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/user/scrap/other`,
        headers: { Authorization: token },
        params: { userSeq: this.userSeq, offset: this.offset, limit: 18 },
      })
        .then((res) => {
          console.log(res);

          if (res.data.mainFeedDtoList.length) {
            res.data.mainFeedDtoList.forEach((feed) => {
              this.feeds.push(feed);
              this.offset += 18;
            });
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped></style>
