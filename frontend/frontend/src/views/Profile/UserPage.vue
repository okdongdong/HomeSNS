<template>
  <v-app>
    <div class="d-flex justify-center ma-5">

    <ProfilePhoto :size='150' :imgUrl="userInfo.imgUrl"/>
    </div>
    <h2 class="text-center">{{ userInfo.name }}</h2>
    <v-btn
      v-if="mySeq == userSeq"
      rounded="xl"
      color="green"
      @click.stop="$router.push({ name: 'MyPage' })"
      >정보수정</v-btn
    >
    <v-card>
      <v-toolbar color="rgb(98,101,232)" dark>
        <template v-slot>
          <v-tabs v-model="tab" grow>
            <v-tabs-slider size="xl" color="white"></v-tabs-slider>

            <v-tab v-for="item in items" :key="item">
              <div>{{ item }}</div>
            </v-tab>
          </v-tabs>
        </template>
      </v-toolbar>

      <v-tabs-items v-model="tab">
        <v-tab-item v-for="feeds in contents" :key="feeds">
          <v-row class="ma-1">
            <v-col cols="4" v-for="feed in feeds" :key="feed">
              <v-card
                width="150"
                height="120"
                elevation="12"
                rounded="xl"
                class="d-inline-flex justify-self-center ma-1"
              >
                <v-img
                  class="feed-img"
                  :src="feed.imgUrl"
                  alt="피드사진"
                  @click.stop="
                    $router.push({
                      name: 'FeedDetail',
                      params: { feedId: this.feed.Id },
                    })
                  "
                />
              </v-card>
            </v-col>
          </v-row>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
  </v-app>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import ProfilePhoto from "../../components/ProfilePhoto.vue";

export default {
  name: "UserPage",
  props: {
    userSeq: Number,
  },
  components: {
    ProfilePhoto,
  },
  data: function () {
    return {
      tab: null,
      items: ["게시물", "스크랩"],
      articleOffset: 1,
      scrapOffset: 1,
      contents: {
        articles: [
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
          {
            id: 1,
            imgUrl:
              "http://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0106/IE002921895_STD.jpg",
          },
        ],
        scraps: [
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
          {
            id: 1,
            imgUrl: "https://t1.daumcdn.net/cfile/tistory/99E88C335A33A50433",
          },
        ],
      },
      userInfo: {
        imgUrl:
          "https://image.ajunews.com/content/image/2016/12/26/20161226142046950664.jpg",
        name: "김영철",
      },
    };
  },
  created: function () {
    this.getProfile();
  },
  methods: {
    getProfile: function () {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/profile/${this.userSeq}`,
        headers: { Authorization: `JWT ${token}` },
      })
        .then((res) => {
          this.userInfo = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getArticle: function () {
      const token = localStorage.getItem("jwt");
      const params = {
        userSeq: this.userSeq,
        offset: this.articleOffset,
        limit: 18,
      };
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/article/`,
        headers: { Authorization: `JWT ${token}` },
        params: params,
      })
        .then((res) => {
          res.data.forEach((article) => {
            this.contents.articles.push(article);
            this.articleOffset++;
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getScrap: function () {
      const token = localStorage.getItem("jwt");
      const params = {
        userSeq: this.userSeq,
        offset: this.scrapOffset,
        limit: 18,
      };
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/scrap/`,
        headers: { Authorization: `JWT ${token}` },
        params: params,
      })
        .then((res) => {
          res.data.forEach((scrap) => {
            this.contents.scraps.push(scrap);
            this.scrapOffset++;
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    ...mapState("account", { mySeq: (state) => state.userSeq }),
  },
};
</script>

<style scoped>
.feed-img {
  overflow: hidden;

  width: inherit;
  height: inherit;
  border: 2px solid white;
  object-fit: cover;
}


</style>
