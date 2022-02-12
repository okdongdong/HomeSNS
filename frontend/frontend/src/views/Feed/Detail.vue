<template>
  <v-app class="container px-0">
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
          <div class="d-inline-flex"><v-icon>location_on</v-icon>{{ feed.location }}</div>
        </div>
        <div>
          <div class="d-inline-flex">{{ feed.eventDate }} 추억</div>
          <FeedPopup
            :feed-id="feedId"
          />
          <!-- <div>업로드 : {{ feed.uploadDate }}</div> -->
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
    <div class="feed-photos">
      <!-- 사진 -->
      <v-carousel height="400" hide-delimiter-background show-arrows-on-hover>
        <v-carousel-item v-for="(imgUrl, i) in feed.imgUrls" :key="i">
          <v-img :src="imgUrl" aspect-ratio="1"></v-img>
        </v-carousel-item>
      </v-carousel>
    </div>
    <div>
      <v-row class="icon-group">
        <v-col cols="10">
        <span style="padding:3px;"></span>
          <v-btn icon large @click="showEmotions ? (showEmotions = false) : (showEmotions = true)">
            <v-icon>favorite_border</v-icon>
          </v-btn>
          <v-btn icon large>
            <v-icon>chat_bubble_outline</v-icon>
          </v-btn>
        </v-col>
        <v-col cols="2">
          <v-btn icon large style="padding:0;">
            <v-icon>bookmark_border</v-icon>
          </v-btn>
        </v-col>
      </v-row>
   
    <!-- 감정 버튼 -->
    <Emotion
      :show-emotions="showEmotions"
      style="position:absolute;"
    />
     </div>
    <div class="content-group" v-html="getContent()">
    <!-- 댓글 부분 ! -->
    <!-- <Comment v-for="comment in comments" :key="comment" :comment="comment" /> -->
    </div>
  </v-app>
</template>

<script>
// import Comment from "../../components/Feed/Comment.vue";
import FeedPopup from "../../components/Feed/FeedPopup.vue"
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
    // Comment,
    FeedPopup,
    Emotion,
    ProfilePhoto,
  },
  data: () => ({
    nowLoading: false,
    tab: 0,
    showEmotions: false,
    feedAutorSeq : null,
    feed: {
      // 샘플 데이터
      author: null,
      title: null,
      //author profile img 필요
      content: null,
      imgUrls: [
        "https://bbs.bepick.in/bbs/2021/12/085af6f07ceabed2af78ce0646341f8c_1103933013.jpeg",
        "https://img.hankyung.com/photo/202112/BF.28426974.1.jpg",
        "http://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2021/07/05/41bdae8f-ae62-490e-9f28-f790ab37fe67.jpg",
      ],
      uploadDate: null,
      eventDate: null,
      location: null,
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
        headers: { Authorization: token },
      }).then((res) => {
        console.log(res.data)
        this.feedAuthorSeq = res.data.feedDto.feedAuthorSeq;
        this.feed.author = res.data.feedDto.feedAuthor;
        this.feed.title = res.data.feedDto.feedTitle;
        console.log(res.data.feedDto.feedContent)
        this.feed.content = res.data.feedDto.feedContent;
        console.log(this.feed.content)
        // this.feed.imgUrls = res.data.feedDto.feedImgUrls;
        this.feed.uploadDate = res.data.feedDto.feedUploadDate;
        this.feed.eventDate = res.data.feedDto.feedEventDate.year+'년'+' '+res.data.feedDto.feedEventDate.month+'월'+' '+res.data.feedDto.feedEventDate.day+'일';
        this.feed.location = res.data.feedDto.locationDto.locationName;
        this.nowLoading = false;
      });
    },
    getContent(){
      if(this.feed.content){
        return this.feed.content.replaceAll("\r\n", "<br />");
      }else{
        return null;
      }
    }
  },
  created() {
    this.feedId *=1;
    this.getFeed();
  },
};
</script>

<style scoped>
  .icon-group{
    padding : 0px 3px;
  }
  .content-group{
    padding : 3px 15px;
  }

</style>
