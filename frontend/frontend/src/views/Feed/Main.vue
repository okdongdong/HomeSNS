<template>
  <v-app class="container">
    <div class="align-center">
      <div v-if="!feedList.length" class="content-box pb-5">
        <div class="justify-center d-flex">
          <h3 class="mt-5">아직 작성된 피드가 없습니다.</h3>
        </div>
        <div class="justify-center d-flex">
          <h3 class="my-5">어서 추억을 담아보아요 :)</h3>
        </div>
      </div>
      <div class="mb-5" v-for="(feeds, idx) in feedList" :key="idx">
        <div class="d-flex align-center">
          <h1>{{ feeds[0].feedEventDate }}</h1>
        </div>
        <div class="feeds-group" v-for="(feed, idx2) in feeds" :key="idx2">
          <div
            class="container content-box mb-2"
            @click.stop="
              $router.push({
                name: 'Detail',
                params: { feedId: feed.feedId },
              })
            "
          >
            <div class="d-flex align-center pa-0">
              <h2 class="d-flex align-center">{{ feed.feedTitle }}</h2>
              <span class="d-flex ml-auto" v-if="feed.feedLocation !== ''">{{
                feed.feedLocation
              }}</span>
            </div>
            <v-container>
              <v-row>
                <v-col v-for="(file, idx3) in feed.fileList" :key="idx3" class="pa-0" cols="4">
                  <!-- :src="require(`@/uploadImg/${file.fileUrl}`)" -->
                  <v-img
                    v-if="file.type == 'img'"
                    :src="`https://i6e205.p.ssafy.io/${file.fileUrl}`"
                    aspect-ratio="1"
                    class="grey lighten-2"
                  >
                    <template v-slot:placeholder>
                      <v-row class="fill-height ma-0" align="center" justify="center">
                        <v-progress-circular
                          indeterminate
                          color="grey lighten-5"
                        ></v-progress-circular>
                      </v-row>
                    </template>
                  </v-img>
                  <v-video
                    v-else
                    :src="`https://i6e205.p.ssafy.io/${file.fileUrl}`"
                    aspect-ratio="1"
                    class="grey lighten-2"
                  >
                    <template v-slot:placeholder>
                      <v-row class="fill-height ma-0" align="center" justify="center">
                        <v-progress-circular
                          indeterminate
                          color="grey lighten-5"
                        ></v-progress-circular>
                      </v-row>
                    </template>
                  </v-video>
                </v-col>
              </v-row>
            </v-container>
          </div>
        </div>
      </div>
    </div>
    <infinite-loading @infinite="getMain"></infinite-loading>
  </v-app>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  name: "Main",
  data: () => ({
    // feedId : 1,
    // feedData:{
    //   feedId : null,
    //   feedEventDate : '1900.01.01',
    //   feedLocation : null,
    //   feedTitle : null,
    //   fileList : [],
    // },
    offset: 0,
    feedList: [],
  }),
  created() {
    // this.getMain();
  },
  methods: {
    getMain($state) {
      const token = localStorage.getItem("jwt");
      const data = {
        groupId: this.nowGroup.groupId,
        limit: 10,
        offset: this.offset,
      };
      axios({
        method: "GET",
        url: `${process.env.VUE_APP_MCS_URL}/main`,
        params: data,
        headers: { Authorization: token },
      }).then((res) => {
        if (res.data.mainFeedDtoList.length) {
          let feedByDateList = [];
          for (let i = 0; i < res.data.mainFeedDtoList.length; i++) {
            let feedData = {
              feedId: null,
              feedEventDate: "1900.01.01",
              feedLocation: null,
              feedTitle: null,
              fileList: [],
            };
            let currFeed = res.data.mainFeedDtoList[i];
            feedData.feedId = currFeed.feedId;
            feedData.feedEventDate =
              currFeed.feedEventDate.year +
              "년" +
              " " +
              currFeed.feedEventDate.month +
              "월" +
              " " +
              currFeed.feedEventDate.day +
              "일";
            // feedData.feedEventDate = this.$moment(currFeed.feedEventDate).format("YYYY년 MM월 DD일");
            feedData.feedLocation = currFeed.feedLocation; // 등록안된 곳은 빈값''
            feedData.feedTitle = currFeed.feedTitle;
            feedData.fileList = [];
            for (let i = 0; i < currFeed.fileList.length; i++) {
              if (currFeed.fileList[i].fileContentType.includes("image")) {
                feedData.fileList.push({
                  fileUrl: currFeed.fileList[i].fileUrl,
                  type: "img",
                });
              } else {
                feedData.fileList.push({
                  fileUrl: currFeed.fileList[i].fileUrl,
                  type: "video",
                });
              }
            }

            if (i === 0) {
              feedByDateList.push(feedData);
            } else if (feedByDateList[0].feedEventDate === feedData.feedEventDate) {
              feedByDateList.push(feedData);
            } else {
              // 날짜 다르면 날짜별로 모아놓은거 feedList에 push
              this.feedList.push(feedByDateList);
              feedByDateList = []; // 초기화
              feedByDateList.push(feedData);
            }
          }
          if (feedByDateList.length > 0) {
            this.feedList.push(feedByDateList);
          }
          this.offset += 10;
          $state.loaded();
        } else {
          $state.complete();
        }
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
h3 {
  display: flex;
  padding: 10px 10px 0px 15px;
  color: rgb(92, 92, 92);
}

/* .feeds-group{
  background-color:white;
  margin : 10px 20px 20px 5px; 
  box-shadow: 12px 12px 2px 1px rgb(173, 173, 255); */
/* 위 오른 아래 왼 */
/* border-style: solid dotted dotted solid; 
  border-style: dotted solid solid dotted; */
/* border-color: rgb(240, 240, 240) rgb(192, 192, 192) rgb(192, 192, 192) rgb(240, 240, 240);
  border-width: 2px 6px 6px 2px;
  border-style: solid none none solid; */
/* border-style: dotted none none dotted; */
/* border-style: solid; */

/* } */
</style>
