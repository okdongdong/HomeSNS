<template>
  <v-app>
    <img :src="userInfo.imgUrl" alt="유저프로필사진" />
    <span>{{ userInfo.name }}이름이들어가야해요</span>
    <v-card>
      <v-toolbar color="rgb(98,101,232)" dark>
        <template v-slot>
          <v-tabs v-model="tab" align-with-title>
            <v-tabs-slider color="white"></v-tabs-slider>

            <v-tab v-for="item in items" :key="item">
              {{ item }}
            </v-tab>
          </v-tabs>
        </template>
      </v-toolbar>

      <v-tabs-items v-model="tab">
        <v-tab-item v-for="feeds in contents" :key="feeds">
          <v-card flat>
            <v-card-text v-for="feed in feeds" :key="feed">{{feed}}사진 url을 받아서 이미지가 들어갈 곳</v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: "UserPage",
  props: {
    userSeq: Number,
  },
  data: function () {
    return {
      tab: null,
      items: ["게시물", "스크랩"],
      contents: { articles: [1,2,3,4,5,6,7,8,9], scraps: [9,87,5,6,4,8,3,2,5,4,8,2,354] },
      userInfo: {
        imgUrl: null,
        name: null,
      },
    };
  },
  created: function () {
    this.getProfile();
  },
  methods: {
    getProfile: function () {
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/profile/${this.userSeq}`,
      })
        .then((res) => {
          this.userInfo = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addArticle: function () {
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/profile/${this.personId}`,
      })
        .then((res) => {
          this.userInfo = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addScrap: function () {
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/profile/${this.personId}`,
      })
        .then((res) => {
          this.userInfo = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
