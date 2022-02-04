<template>
  <div>
    <div class="pa-3">
      <v-overlay :value="nowLoading">
        <v-progress-circular
          :size="100"
          :width="10"
          color="white"
          indeterminate
        ></v-progress-circular>
      </v-overlay>

      <div class="justify-space-between d-flex align-center px-3">
        <div>
          <h1 class="d-inline-flex">{{ feed.title }}</h1>
          <div class="d-inline-flex">
            <v-icon>location_on</v-icon>{{ feed.location }}
          </div>
        </div>
        <div>
          <div>행사일 : {{ feed.eventDate }}</div>
          <div>업로드 : {{ feed.uploadDate }}</div>
        </div>
      </div>
      <hr />
      <div class="d-inline-flex align-center my-3">
        <ProfilePhoto :size="50" />
        <h3 class="mx-3">
          {{ feed.author }}
        </h3>
      </div>
    </div>
    <div>
      <!-- 사진 -->
      <v-carousel height="400" hide-delimiter-background show-arrows-on-hover>
        <v-carousel-item v-for="(imgUrl, i) in feed.imgUrls" :key="i">
          <v-img :src="imgUrl" aspect-ratio="1"></v-img>
        </v-carousel-item>
      </v-carousel>
    </div>
    <div class="pa-3">
      {{ feed.content }}
      <Emotion />

      <Comment v-for="comment in comments" :key="comment" :comment="comment" />
    </div>
  </div>
</template>

<script>
import Comment from "../../components/Feed/Comment.vue";
import Emotion from "../../components/Feed/Emotion.vue";
import ProfilePhoto from "../../components/ProfilePhoto.vue";
import axios from "axios";
// import { mapState } from "vuex";

export default {
  name: "Detail",
  props: {
    feedId: Number,
  },
  components: {
    Comment,
    Emotion,
    ProfilePhoto,
  },
  data: () => ({
    nowLoading: false,
    tab: 0,
    feed: {
      // 샘플 데이터
      author: "전광렬",
      title: "제목목",
      //author profile img 필요
      content: "하하하하하!",
      imgUrls: [
        "http://cdn.ggilbo.com/news/photo/202105/849179_678277_489.jpg",
        "http://cdn.ggilbo.com/news/photo/202105/849179_678292_441.jpg",
        "http://cdn.ggilbo.com/news/photo/202105/849179_678290_21.jpg",
      ],
      uploadDate: "2121-21-21",
      eventDate: "2222-22-22",
      location: "우리집",
    },

    comments: [
      {
        author: "할매",
        tag: "임시태그",
        content: "댓글내용",
        uploadDate: "2011-11-11",
      },
    ],
  }),
  methods: {
    getFeed() {
      this.nowLoading = true;
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/feed/${this.feedId}`,
        headers: { Authorization: `JWT ${token}` },
      }).then((res) => {
        this.feed.author = res.data.feedAuthor;
        this.feed.title = res.data.feedTitle;
        this.feed.content = res.data.feedContent;
        this.feed.imgUrls = res.data.feedImgUrls;
        this.feed.uploadDate = res.data.feedUploadDate;
        this.feed.eventDate = res.data.feedEventDate;
        this.feed.location = res.data.feedLocation;
        this.nowLoading = false;
      });
    },
  },
  created() {
    // this.getFeed();
  },
};
</script>

<style scoped></style>
