<template>
  <div class='comment-box'>
    <div class="d-inline-flex">
      <div>
        <ProfilePhoto
          :size="50"
          :imgUrl="comment.userImgUrl"
          :name="comment.author"
        />
      </div>
      <div>
        <div>{{ comment.uploadDate }}</div>
        <span class="hashtag"> @{{ comment.tag }} </span>
        <span> {{ comment.content }} </span>
      </div>
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
  },
  components: {
    ProfilePhoto,
    Emotion,
  },
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
