<template>
  <!-- <v-row class='comment-box d-flex justify-space-between align-center'> -->
  <div class="comment-box">
    <v-row class="justify-space-between align-center pt-2">
      <v-col cols="3" class="pl-3">
        <ProfilePhoto
          :size="40"
          :imgUrl="comment.userImgUrl"
          :name="comment.commentAuthor"
          :userSeq="comment.commentAuthorSeq"
        />
      </v-col>
      <v-col cols="7" class="pr-3" v-if="comment.commentAuthorSeq == userSeq">
        <div>{{ comment.uploadDate }}</div>
        <span class="hashtag"> @{{ comment.commentContent }} </span>
        <span> {{ comment.commentContent }} </span>
      </v-col>
      <v-col cols="9" class="pr-4" v-else>
        <div>{{ comment.uploadDate }}</div>
        <span class="hashtag"> @{{ comment.commentContent }} </span>
        <span> {{ comment.commentContent }} </span>
      </v-col>
      <v-col cols="2" class="d-inline-flex pr-3" v-if="comment.commentAuthorSeq == userSeq">
        <v-btn icon @click="deleteComment(comment.commentId)">
          <v-icon size="32">delete_forever</v-icon>
        </v-btn>  
      </v-col>
    </v-row>
    <hr>
  </div>
    <!-- <Emotion /> -->
  <!-- </v-row> -->
</template>

<script>
import {mapState} from "vuex";
import ProfilePhoto from "../ProfilePhoto.vue";
// import Emotion from "./Emotion.vue";
export default {
  name: "Comment",
  props: {
    comment: Object,
    feedId : Number,
  },
  components: {
    ProfilePhoto,
    // Emotion,
  },
  methods:{
    deleteComment(commentId){
      console.log(this.feedId)
      let data ={
        // feedId : this.feedId,
        commentId : commentId,
      }
      this.$store.dispatch('comments/deleteComment',data)
    }
  },
  computed:{
    ...mapState("account",["userSeq"]),
  }
};
</script>

<style scoped>
.comment-box{
  background-color: rgba(251, 251, 251, 0.705);
}
.hashtag {
  color: rgb(110, 110, 252);
}
</style>
