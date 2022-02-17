<template>
  <v-app class="container pa-0">
    <div class="pa-3 pb-0">
      <v-overlay :value="nowLoading">
        <v-progress-circular
          :size="100"
          :width="10"
          color="white"
          indeterminate
        ></v-progress-circular>
      </v-overlay>

      <v-row class="py-3">
        <v-col cols="10">
          <div>
            <h1 class="d-inline-flex">{{ feed.title }}</h1>
          </div>
          <div>
            <div class="d-inline-flex">{{ feed.eventDate }}</div>
          </div>
        </v-col>
        <v-col v-if="userSeq == feedAuthorSeq" cols="2" class="d-flex justify-center align-center">
          <FeedPopup :feed-id="feedId" :feed-author-seq="feedAuthorSeq" />
        </v-col>
      </v-row>
      <hr />
      <div class="d-flex align-center" style="margin-top: 12px; margin-bottom: 3px">
        <ProfilePhoto :size="25" :img-url="feedAuthorProfileImageUrl" :user-seq="feedAuthorSeq" />
        <h3
          class="mx-3"
          @click="$router.push({ name: 'UserPage', params: { userSeq: feedAuthorSeq } })"
        >
          {{ feed.author }}
        </h3>
      </div>
      <v-row justify="space-around">
        <v-col cols="9"> <v-icon>location_on</v-icon>{{ feed.location }} </v-col>
        <v-col cols="3">
          <v-btn rounded small @click="sendNotice()" class="mb-1"> 추억공유 </v-btn>
        </v-col>
      </v-row>
    </div>
    <div class="feed-photos">
      <!-- 사진 -->
      <v-carousel height="400" hide-delimiter-background show-arrows-on-hover>
        <v-carousel-item v-for="(imgUrl, i) in feed.imgUrls" :key="i">
          <v-img :src="`https://i6e205.p.ssafy.io/${imgUrl.fileUrl}`" aspect-ratio="1"></v-img>
        </v-carousel-item>
      </v-carousel>
    </div>
    <div>
      <v-row class="icon-group">
        <!-- 하트 -->
        <v-col cols="9">
          <span style="padding: 8px"></span>
          <v-btn
            style="width: 25px"
            icon
            large
            @click="showEmotions ? (showEmotions = false) : (showEmotions = true)"
          >
            <!-- <v-img v-if="currEmotion == undefined" :src="emotions[0].emoji" style="width: 10px; height: auto"></v-img> -->
            <v-img :src="currEmotion.emoji" style="width: 10px; height: auto"></v-img>
          </v-btn>
        </v-col>
        <!-- 타임라인버튼 -->
        <v-col cols="1">
          <v-btn icon large style="padding: 0" @click="timelineToggle">
            <v-icon v-if="timeline == false">access_time</v-icon>
            <v-icon v-else color="green">access_time</v-icon>
          </v-btn>
        </v-col>
        <!-- 북마크 -->
        <v-col cols="1">
          <v-btn icon large style="padding: 0" @click="bookmarkToggle">
            <v-icon v-if="bookmark == false">bookmark_border</v-icon>
            <v-icon v-else color="blue">bookmark</v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <!-- 감정 버튼 -->
      <Emotion
        :show-emotions="showEmotions"
        @select-emotion="changeEmotion"
        style="position: absolute"
        @emotion-show-toggle="emotionShowToggle"
      />
    </div>
    <div class="content-group" v-html="getContent()"></div>
    <!-- 해시태그 부분 -->
    <div class="px-3">
      <v-chip-group active-class="primary--text" column>
        <v-chip v-for="hashtag in feed.hashtagList" :key="hashtag.hashtagContent" color="white">
          #{{ hashtag.hashtagContent }}
        </v-chip>
      </v-chip-group>
    </div>
    <!-- 감정 숫자표현 -->
    <div class="px-3">
      <v-chip-group active-class="primary--text" column>
        <span v-for="emotion in emotions" :key="emotion.code">
          <v-chip class="px-2" small color="white" v-if="emotion.cnt != 0">
            <v-avatar center>
              <v-img :src="emotion.emoji"></v-img>
            </v-avatar>
            <span class="pl-1">{{ emotion.cnt }}</span>
          </v-chip>
        </span>
      </v-chip-group>
    </div>
    <!-- 댓글 부분 ! -->
    <v-form class="px-3" ref="form" @submit.prevent="createComment">
      <div class="d-flex">
        <v-text-field label="댓글 달기" v-model="currComment"> </v-text-field>
        <v-btn
          class="my-5 ml-2"
          elevation="3"
          rounded
          dark
          color="indigo"
          small
          @click="createComment"
        >
          게시
        </v-btn>
      </div>
      <v-card class="ml-1" max-width="400" v-if="commentInTag">
        <v-list>
          <v-list-item-group v-model="tagList" multiple color="indigo">
            <v-list-item v-for="member in members" :key="member.userName">
              <v-list-item-content>
                <v-list-item-title
                  v-text="member.userName"
                  @click.stop="selectTagMember(member)"
                ></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </v-form>
    <Comment
      v-for="(comment, idx) in comments"
      :key="idx"
      :comment="comment"
      :feed-id="feedId"
      :members="members"
    />
    <infinite-loading @infinite="getComments"></infinite-loading>
  </v-app>
</template>

<script>
import Comment from "../../components/Feed/Comment.vue";
import FeedPopup from "../../components/Feed/FeedPopup.vue";
import Emotion from "../../components/Feed/Emotion.vue";
import ProfilePhoto from "../../components/ProfilePhoto.vue";
import axios from "axios";
import { mapState, mapActions } from "vuex";

export default {
  name: "Detail",
  props: {
    feedId: Number,
  },
  components: {
    Comment,
    FeedPopup,
    Emotion,
    ProfilePhoto,
  },
  data: () => ({
    nowLoading: false,
    tab: 0,
    showEmotions: false,
    feedAuthorSeq: null,
    feedAuthorProfileImageUrl: null,
    feed: {
      // 샘플 데이터
      author: null,
      title: null,
      //author profile img 필요
      content: null,
      imgUrls: [],
      uploadDate: null,
      eventDate: null,
      location: null,
      hashtagList: [],
    },
    // 감정
    emotions: [
      { emoji: require("@/assets/emotions/heart_off.png"), status: "null", code: 30000, cnt: 0 },
      { emoji: require("@/assets/emotions/heart_on.png"), status: "good", code: 30001, cnt: 0 },
      { emoji: require("@/assets/emotions/sad.png"), status: "sad", code: 30002, cnt: 0 },
      { emoji: require("@/assets/emotions/check.png"), status: "check", code: 30003, cnt: 0 },
      { emoji: require("@/assets/emotions/fun.png"), status: "fun", code: 30004, cnt: 0 },
      { emoji: require("@/assets/emotions/amaze.png"), status: "amaze", code: 30005, cnt: 0 },
    ],
    beforeEmotion: null,
    currEmotion: { emoji: require("@/assets/emotions/heart_off.png"), status: "null", code: 30000 },
    // 타임라인
    timeline: false,
    // 북마크
    bookmark: false,
    // 댓글쪽
    currComment: null,
    members: [], // 해시태그위한 멤버리스트
    memberToggle: false,
    tagList: [], // 해시태그한 사람
  }),
  methods: {
    ...mapActions("notice", ["send"]),
    sendNotice() {
      const noticeInfo = {
        targetUserSeq: -1,
        noticeType: "shareCreate",
        noticeContentId: this.feedId,
      };
      this.send(noticeInfo);
    },
    emotionShowToggle() {
      this.showEmotions = false;
    },
    getMember() {
      // 해시태그용
      let groupId = this.nowGroup.groupId;
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/feed/info/${groupId}`,
        headers: { Authorization: token },
      }).then((res) => {
        this.members = res.data.members;
      });
    },
    getFeed() {
      this.nowLoading = true;
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/feed/${this.feedId}`,
        headers: { Authorization: token },
      }).then((res) => {
        this.feedAuthorSeq = res.data.feedDto.feedAuthorSeq;
        this.feedAuthorProfileImageUrl = res.data.feedDto.authorProfileImageUrl;
        this.feed.author = res.data.feedDto.feedAuthor;
        this.feed.title = res.data.feedDto.feedTitle;
        this.feed.content = res.data.feedDto.feedContent;
        this.feed.imgUrls = res.data.feedDto.fileList;
        this.feed.uploadDate = res.data.feedDto.feedUploadDate;
        this.feed.eventDate =
          res.data.feedDto.feedEventDate.year +
          "년" +
          " " +
          res.data.feedDto.feedEventDate.month +
          "월" +
          " " +
          res.data.feedDto.feedEventDate.day +
          "일";
        this.feed.location = res.data.feedDto.locationDto.locationName;
        this.nowLoading = false;
        // this.memberList = res.data.feedDto.userList;
        this.feed.hashtagList = res.data.feedDto.hashtagList;
        if (res.data.feedDto.code != "30000") {
          for (let i = 0; i < this.emotions.length; i++) {
            if (this.emotions[i].code == res.data.feedDto.code) {
              this.currEmotion = this.emotions[i];
              this.beforeEmotion = this.emotions[i];
              break;
            }
          }
        }
        this.emotions[1].cnt = res.data.feedDto.good;
        this.emotions[2].cnt = res.data.feedDto.sad;
        this.emotions[3].cnt = res.data.feedDto.check;
        this.emotions[4].cnt = res.data.feedDto.fun;
        this.emotions[5].cnt = res.data.feedDto.amaze;
        if (res.data.feedDto.scrapYn == "y") {
          this.bookmark = true;
        } else {
          this.bookmark = false;
        }
        this.timeline = res.data.feedDto.timeline;
      });
    },
    getContent() {
      if (this.feed.content) {
        return this.feed.content.replaceAll("\r\n", "<br />");
      } else {
        return null;
      }
    },
    getComments($state) {
      let data = {
        commentParamDto: {
          feedId: this.feedId,
          limit: 10,
        },
        state: $state,
      };
      this.$store.dispatch("comments/getComments", data);
    },
    createComment(event) {
      event.preventDefault();
      if (this.currComment == null || this.currComment.trim() == "") {
        this.$store.commit("snackbar/SET_SNACKBAR", "댓글을 입력해주세요.");
      } else {
        let commentTag = [];
        for (let i = 0; i < this.tagList.length; i++) {
          commentTag.push(this.tagList[i].userSeq);
        }
        let data = {
          commentDto: {
            feedId: this.feedId,
            commentTags: commentTag,
            commentContent: this.currComment,
          },
          feedAuthorSeq: this.feedAuthorSeq,
        };
        this.$store.dispatch("comments/createComment", data);
        this.currComment = null;
      }
    },
    selectTagMember(member) {
      // 원래는 자동으로 토글되야하는데 안되서 일단 수동으로 구현(comment내에 태그 지우면 없어짐)
      let flag = 0;
      for (let i = 0; i < this.tagList.length; i++) {
        if (this.tagList[i].userName == member.userName) {
          this.tagList.splice(i, 1);
          flag = 1;
          break;
        }
      }
      if (flag == 0) {
        this.tagList.push({
          userSeq: member.userSeq,
          userName: member.userName,
        });
        this.currComment += member.userName + " ";
      }
    },
    changeEmotion(data) {
      const token = localStorage.getItem("jwt");
      if (data == undefined || this.currEmotion.status == data) {
        // 선택한 감정 없음(감정 삭제)
        this.currEmotion = this.emotions[0];
        let deleteEmotion = {
          feedId: this.feedId,
        };
        deleteEmotion[this.beforeEmotion.status] = 1;
        for (let i = 0; i < this.emotions.length; i++) {
          if (this.emotions[i].status == this.beforeEmotion.status) {
            this.emotions[i].cnt -= 1;
          }
        }
        axios({
          method: "PUT",
          url: `${process.env.VUE_APP_MCS_URL}/feed/emotion/sub`,
          data: deleteEmotion,
          headers: { Authorization: token },
        });
      } else {
        // 감정 수정
        if (this.beforeEmotion != null && this.beforeEmotion.cnt > 0) {
          for (let i = 0; i < this.emotions.length; i++) {
            if (this.emotions[i].status == this.beforeEmotion.status) {
              this.emotions[i].cnt -= 1;
              break;
            }
          }
        }
        for (let i = 0; i < this.emotions.length; i++) {
          if (this.emotions[i].status == data) {
            this.emotions[i].cnt += 1;
            this.currEmotion = this.emotions[i];
            this.beforeEmotion = this.emotions[i];
            break;
          }
        }

        let selectEmotion = {
          feedId: this.feedId,
        };
        selectEmotion[this.currEmotion.status] = 1;
        axios({
          method: "PUT",
          url: `${process.env.VUE_APP_MCS_URL}/feed/emotion/add`,
          data: selectEmotion,
          headers: { Authorization: token },
        });
      }
    },
    bookmarkToggle() {
      const token = localStorage.getItem("jwt");
      if (this.bookmark) {
        // 북마크 true이면 삭제해줘야함
        axios({
          method: "DELETE",
          url: `${process.env.VUE_APP_MCS_URL}/feed/scrap/${this.feedId}`,
          headers: { Authorization: token },
        }).then(() => {
          this.bookmark = !this.bookmark;
        });
      } else {
        axios({
          method: "POST",
          url: `${process.env.VUE_APP_MCS_URL}/feed/scrap/${this.feedId}`,
          headers: { Authorization: token },
        }).then(() => {
          this.bookmark = !this.bookmark;
        });
      }
    },
    timelineToggle() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "put",
        url: `${process.env.VUE_APP_MCS_URL}/feed/timeline/${this.feedId}`,
        headers: { Authorization: token },
      }).then(() => {
        this.timeline = !this.timeline;
      });
    },
  },
  mounted() {
    this.feedId *= 1;
    this.getFeed();
    this.getMember();
    // this.getAuthorProfileImageUrl();
  },
  beforeDestroy() {
    this.$store.dispatch("comments/resetOffset");
  },
  computed: {
    ...mapState("account", ["nowGroup", "userName", "userSeq"]),
    ...mapState("comments", ["comments", "offset"]),
    commentInTag() {
      if (this.currComment != null && this.currComment.substr(-1) == "@") {
        return true;
      } else {
        return false;
      }
    },
  },
  watch: {
    currComment: function (val) {
      for (let i = 0; i < this.tagList.length; i++) {
        if (!val.includes(this.tagList[i].userName)) {
          this.tagList.splice(i, 1);
        }
      }
    },
  },
};
</script>

<style scoped>
.icon-group {
  padding: 0px 3px;
}
.content-group {
  padding: 3px 15px;
}
</style>
