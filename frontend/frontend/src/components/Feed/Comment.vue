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
      <v-col class="comment-content-987987 pr-3" :id="`comment${comment.commentId}`" cols="7" v-if="comment.commentAuthorSeq == userSeq">
        <div>{{ comment.uploadDate }}</div>
        <!-- <span class="hashtag"> @{{ comment.commentContent }} </span>
        <span> {{ comment.commentContent }} </span> -->
      </v-col>
      <v-col class="comment-content pr-4" :id="`comment${comment.commentId}`" cols="9" v-else>
        <div>{{ comment.uploadDate }}</div>
        <!-- <span class="hashtag"> @{{ comment.commentContent }} </span> -->
        <!-- <span> {{ comment.commentContent }} </span> -->
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
    ...mapState("comments",["comments"])
  },
  mounted () {
    console.log('현재댓글')

    const commentList = this.comment.commentContent.split(" ");
    let commentTag = document.querySelector('#comment'+this.comment.commentId)
    console.log('선택한 태그')
    console.log(commentTag)
    console.log(commentList)
    for(let i=0; i<commentList.length;i++){
      if(commentList[i].substring(0,1)=='@'){
        let tagSpanTag = document.createElement('div')
        tagSpanTag.innerText = commentList[i]+" "
        console.log('댓글xorm=======================')
        console.log(tagSpanTag)
        commentTag.appendChild(tagSpanTag)
      }else{
        let commentSpanTag = document.createElement('span')
        commentSpanTag.innerText = commentList[i]+" "
        console.log('댓글=======================')
        console.log(commentSpanTag)
        commentTag.appendChild(commentSpanTag)
      }
    }
  },
  // updated(){

  // }
};

</script>

<style>
.comment-box-987987{
  background-color: rgba(251, 251, 251, 0.705);
}
/* .hashtag {
  color: rgb(110, 110, 252);
} */
.comment-content-987987 div{
  display: inline;
  font-weight: bold;
  text-decoration: underline;
  color: rgb(87, 75, 255);
}
</style>
