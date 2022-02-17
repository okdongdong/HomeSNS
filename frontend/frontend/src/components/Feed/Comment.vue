<template>
  <!-- <v-row class='comment-box d-flex justify-space-between align-center'> -->
  <div class="comment-box-987987">
    <v-row class="justify-space-between align-center pt-2">
      <v-col cols="3" class="pl-3">
        <ProfilePhoto
          :size="40"
          :imgUrl="comment.commentUserProfileImageUrl"
          :name="comment.commentAuthor"
          :userSeq="comment.commentAuthorSeq"
        />
      </v-col>
      <v-col
        class="comment-content-987987 pr-3"
        :cols="comment.commentAuthorSeq == userSeq ? 7 : 9"
        ><div>
          <div>
            <span
              v-for="(content, idx) in comment.commentContent"
              :key="idx"
              :class="content.type"
              @click="move(content)"
            >
              {{ content.content }}
            </span>
          <br>
          <div>{{ comment.commentUploadDate }}</div>
          </div>
        </div>
      </v-col>
      <v-col
        cols="2"
        class="d-inline-flex pr-3"
        v-if="comment.commentAuthorSeq == userSeq"
      >
        <v-btn icon @click="deleteComment(comment.commentId)">
          <v-icon size="32">delete_forever</v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <hr />
  </div>
  <!-- <Emotion /> -->
  <!-- </v-row> -->
</template>

<script>
import { mapState } from "vuex";
import ProfilePhoto from "../ProfilePhoto.vue";
// import Emotion from "./Emotion.vue";
export default {
  name: "Comment",
  data: () => ({
    commentContentList: [],
  }),
  props: {
    comment: Object,
    feedId: Number,
    members : Array,
  },
  components: {
    ProfilePhoto,
    // Emotion,
  },
  methods: {
    move(content){
      if(content.type == 'hashtag'){
        let memberName = content.content.substr(1).trim();
        console.log(memberName)
        for(let i=0;i<this.members.length;i++){
          if(this.members[i].userName == memberName){
            this.$router.push({name : 'UserPage', params:{userSeq : this.members[i].userSeq}})
          }
        }
      }
    },
    deleteComment(commentId) {
      console.log(this.feedId);
      let data = {
        // feedId : this.feedId,
        commentId: commentId,
      };
      this.$store.dispatch("comments/deleteComment", data);
    },
  },
  computed: {
    ...mapState("account", ["userSeq"]),
    ...mapState("comments", ["comments"]),
  },
};
</script>

<style>
.comment-box-987987 {
  background-color: rgba(251, 251, 251, 0.705);
}
.hashtag {
  color: rgb(87, 75, 255);
  font-weight: bold;
  text-decoration: underline;
}
.comment-content-987987 div {
  display: inline;
}
</style>
