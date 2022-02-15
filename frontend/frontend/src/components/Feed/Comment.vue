<template>
  <div class='comment-box d-flex justify-space-between align-center'>
    <div class="d-inline-flex pt-3">
      <div class="pl-2">
        <ProfilePhoto
          :size="50"
          :imgUrl="comment.userImgUrl"
          :name="comment.author"
          :userSeq="comment.authorId"
        />
      </div>
      <div class="px-3">
        <div>{{ comment.uploadDate }}</div>
        <span class="hashtag"> @{{ comment.tag }} </span>
        <span> {{ comment.content }} </span>
      </div>
    </div>
    <div class="d-inline-flex pr-3">
        <v-btn icon @click="deleteComment(comment.commentId)">
          <v-icon size="32">delete_forever</v-icon>
        </v-btn>  
    </div>
    <Emotion />
  </div>
</template>

<script>
import ProfilePhoto from "../ProfilePhoto.vue";
import Emotion from "./Emotion.vue";
export default {
  name: "Comment",
  props: {
    comment: Object,
    feedId : Number,
  },
  components: {
    ProfilePhoto,
    Emotion,
  },
  methods:{
    deleteComment(commentId){
      console.log(this.feedId)
      let data ={
        feedId : this.feedId,
        commentId : commentId,
      }
      this.$store.dispatch('comments/deleteComment',data)
    }
  }
};
</script>

<style scoped>
.comment-box{
  background-color: rgb(211, 211, 211);
}
.hashtag {
  color: rgb(110, 110, 252);
}
</style>
