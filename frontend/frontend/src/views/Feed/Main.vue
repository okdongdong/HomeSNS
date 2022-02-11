<template>
  <v-app class="container">
    <div class="container px-3 align-center">
      <div class="mb-5" v-for="(feeds, idx) in feedList" :key="idx">
        <div class="d-flex px-3 align-center">
          <h1>{{ feeds[0].feedEventDate }}</h1>
        </div>
        <div class="feeds-group" v-for="(feed, idx2) in feeds" :key="idx2">
          <div class="container content-box mb-2">
            <div class="d-flex align-center pa-0">
              <h2 class="d-flex align-center">{{ feed.feedTitle }}</h2>
              <span class="d-flex ml-auto" v-if="feed.feedLocation !== ''">{{
                feed.feedLocation
              }}</span>
            </div>
            <v-container>
              <v-row>
                <v-col
                  v-for="(file, idx3) in feed.fileList"
                  :key="idx3"
                  class="pa-0"
                  cols="4"
                >
                  <!-- :src="require(`@/uploadImg/${file.fileUrl}`)" -->
                  <v-img
                    :src="file"
                    :lazy-src="`https://picsum.photos/200/300`"
                    aspect-ratio="1"
                    class="grey lighten-2"
                    @click.stop="
                      $router.push({
                        name: 'Detail',
                        params: { feedId: feed.feedId },
                      })
                    "
                  >
                    <template v-slot:placeholder>
                      <v-row
                        class="fill-height ma-0"
                        align="center"
                        justify="center"
                      >
                        <v-progress-circular
                          indeterminate
                          color="grey lighten-5"
                        ></v-progress-circular>
                      </v-row>
                    </template>
                  </v-img>
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
      })
        .then((res) => {
          console.log(res.data);
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
                currFeed.feedEventDate.month +
                "월" +
                currFeed.feedEventDate.day +
                "일";
              feedData.feedLocation = currFeed.feedLocation; // 등록안된 곳은 빈값''
              feedData.feedTitle = currFeed.feedTitle;
              feedData.fileList = [
                "http://www.newsinside.kr/news/photo/202102/1103606_781901_5945.jpg",
                "https://mblogthumb-phinf.pstatic.net/MjAyMDA0MThfMTA0/MDAxNTg3MTc1NTM2NDMw.l17QjLpVPqZcfTlvImeA15ObSuVmMPxt31b2ksSpZggg.uET2tQLrS7Il91L3SFMhyJ_54Ll-fiQgUdcb3I0H78sg.JPEG.eett7777/IMG_2543.jpg?type=w800",
                "https://mblogthumb-phinf.pstatic.net/MjAyMDA0MThfMjgw/MDAxNTg3MTc1NTQ1MTQ2.ixQHurCge5vwo0Uk0X7gftDjfGSA6q7ur-0Pv0v8nS8g.amtkC8I_oemzssSS72vqYOM21mP16oEK6fEfukYyXBwg.JPEG.eett7777/IMG_2539.jpg?type=w800",
                "https://mblogthumb-phinf.pstatic.net/MjAyMDA0MThfMjky/MDAxNTg3MTc1NTU3MTUz.aslYp9TZpSwYpKdSgSzNIR1_xhr6UYL83IPg6I_HgyUg.PSfXPMHLoRIeTVytsjXTxS-oLcYNz6oGbntPMFZ66_Qg.JPEG.eett7777/IMG_2540.jpg?type=w800",
                "https://mblogthumb-phinf.pstatic.net/MjAyMDA0MThfMjgw/MDAxNTg3MTc1NTQ1MTQ2.ixQHurCge5vwo0Uk0X7gftDjfGSA6q7ur-0Pv0v8nS8g.amtkC8I_oemzssSS72vqYOM21mP16oEK6fEfukYyXBwg.JPEG.eett7777/IMG_2539.jpg?type=w800",
                "https://mblogthumb-phinf.pstatic.net/MjAyMDA0MThfMTA0/MDAxNTg3MTc1NTM2NDMw.l17QjLpVPqZcfTlvImeA15ObSuVmMPxt31b2ksSpZggg.uET2tQLrS7Il91L3SFMhyJ_54Ll-fiQgUdcb3I0H78sg.JPEG.eett7777/IMG_2543.jpg?type=w800",
                "https://mblogthumb-phinf.pstatic.net/MjAyMDA0MThfMjgw/MDAxNTg3MTc1NTQ1MTQ2.ixQHurCge5vwo0Uk0X7gftDjfGSA6q7ur-0Pv0v8nS8g.amtkC8I_oemzssSS72vqYOM21mP16oEK6fEfukYyXBwg.JPEG.eett7777/IMG_2539.jpg?type=w800",
                "http://www.newsinside.kr/news/photo/202102/1103606_781901_5945.jpg",
                "https://mblogthumb-phinf.pstatic.net/MjAyMDA0MThfMjky/MDAxNTg3MTc1NTU3MTUz.aslYp9TZpSwYpKdSgSzNIR1_xhr6UYL83IPg6I_HgyUg.PSfXPMHLoRIeTVytsjXTxS-oLcYNz6oGbntPMFZ66_Qg.JPEG.eett7777/IMG_2540.jpg?type=w800",
              ];
              if (i === 0) {
                feedByDateList.push(feedData);
              } else if (
                feedByDateList[0].feedEventDate === feedData.feedEventDate
              ) {
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
            console.log("==================");
            console.log(this.feedList);
            this.offset += 10;
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getImg() {
      // getImg(url){
      //   const tmpUrl = '@assets/'+url
      //   return require(tmpUrl)
      return require("@/assets/upload/test1.png");
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
  /* box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329); */
  background-color: rgba(255, 255, 255, 0.5);
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
