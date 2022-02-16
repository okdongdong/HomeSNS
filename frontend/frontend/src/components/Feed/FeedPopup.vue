<template>
  <v-dialog v-model="dialog" scrollable max-width="400px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on" icon>
        <v-icon>more_vert</v-icon>
      </v-btn>
    </template>
    <v-card class="rounded-xl">
      <div>
        <v-card-title class="justify-center card-title red--text" @click="deleteFeed()">삭제</v-card-title>
      </div>
      <v-divider></v-divider>
      <div @click.stop="$router.push({ name: 'FeedCreate', params: { feedId: feedId }})">
        <v-card-title class="justify-center card-title">수정</v-card-title>
      </div>
      <v-divider></v-divider>
      <div>
        <v-card-title class="justify-center card-title" @click="dialog = false">취소</v-card-title>
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapState } from "vuex";
import axios from 'axios';
export default {
  name: "FeedPopup",
  props:{
    feedId : Number,
  },
  data: () => ({
    dialog:false,
  }),
  methods:{
    deleteFeed(){
      const token = localStorage.getItem("jwt");
      axios({
        method:"DELETE",
        url: `${process.env.VUE_APP_MCS_URL}/feed/${this.feedId}`,
        headers:{
          Authorization: token,
        }
      })
      .then(()=>{
        console.log('피드삭제 완료')
        this.$router.push({name:'Main', params: {groupId : this.nowGroup.groupId}})
      })
      .catch((err)=>{
        console.log('피드삭제 실패')
        console.log(err)
      })
    }
  },
  computed:{
    ...mapState("account",["nowGroup","userSeq"])
  }
};
</script>

<style scoped>
 .card-title{
   font-size:1.2rem;
 }
</style>
