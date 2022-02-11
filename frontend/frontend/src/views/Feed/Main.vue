<template>
  <v-app>
    <v-card  v-for="(feeds,idx) in feedList" :key="idx" style="background-color:rgb(240, 240, 240);">
      <v-card-title>{{feeds[0].feedEventDate}}</v-card-title>
      <div class="feeds-group" v-for="(feed,idx2) in feeds" :key="idx2">
      <h3>{{feed.feedTitle}}</h3>
      <h4 v-if="feed.feedLocation !== ''">{{feed.feedLocation}}</h4>
      <v-container>
        <v-row>
          <v-col v-for="(file,idx3) in feed.fileList" :key="idx3" class="d-flex child-flex" cols="4">
            <!-- :src="require(`@/uploadImg/${file.fileUrl}`)" -->
            <v-img
              :src= getImg(file.fileUrl)
              :lazy-src="`https://picsum.photos/200/300`"
              aspect-ratio="1"
              class="grey lighten-2"
              @click.stop="
                $router.push({ name: 'Detail', params: { feedId: feed.feedId } })
              "
            >
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular
                    indeterminate
                    color="grey lighten-5"
                  ></v-progress-circular>
                </v-row>
              </template>
            </v-img>
          </v-col>
        </v-row>
      </v-container>
      </div>
      <v-divider></v-divider>
    </v-card>
    <infinite-loading @infinite="getMain"></infinite-loading>
  </v-app>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  name: "Main",
  data: () => ({
    // feedId : 1,
    // feedData:{
    //   feedId : null,
    //   feedEventDate : '1900.01.01',
    //   feedLocation : null,
    //   feedTitle : null,
    //   fileList : [],
    // },
    offset:0,
    feedList:[]
  }),
  created() {
    // this.getMain();
  },
  methods: {
    getMain($state) {
      const token = localStorage.getItem("jwt");
      const data ={
        groupId : this.nowGroup.groupId,
        limit : 10,
        offset: this.offset,
      }
      axios({
        method: "GET",
        url: `${process.env.VUE_APP_MCS_URL}/main`,
        params : data,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log(res.data);
          if(res.data.mainFeedDtoList.length){
            let feedByDateList=[]
            for(let i=0;i<res.data.mainFeedDtoList.length;i++){
              
              let feedData={
                feedId : null,
                feedEventDate : '1900.01.01',
                feedLocation : null,
                feedTitle : null,
                fileList : [],
              }
              let currFeed = res.data.mainFeedDtoList[i]
              feedData.feedId = currFeed.feedId
              feedData.feedEventDate=currFeed.feedEventDate.year+'년'+currFeed.feedEventDate.month+'월'+currFeed.feedEventDate.day+'일'
              feedData.feedLocation=currFeed.feedLocation // 등록안된 곳은 빈값''
              feedData.feedTitle=currFeed.feedTitle
              feedData.fileList=currFeed.fileList
              if(i===0){
                feedByDateList.push(feedData)
              }
              else if(feedByDateList[0].feedEventDate === feedData.feedEventDate){
                feedByDateList.push(feedData)
              }
              else{ // 날짜 다르면 날짜별로 모아놓은거 feedList에 push
                this.feedList.push(feedByDateList)
                feedByDateList=[] // 초기화
                feedByDateList.push(feedData)
              }
            }
            if(feedByDateList.length>0){
                this.feedList.push(feedByDateList)
              }
            console.log('==================')
            console.log(this.feedList)
            this.offset+=10
            $state.loaded();
          }else{
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getImg(){
    // getImg(url){
    //   const tmpUrl = '@assets/'+url
    //   return require(tmpUrl)
      return require('@/assets/upload/logo1.png')
    }
  },
  computed: {
    ...mapState("account", ["nowGroup"]),

  },
};
</script>

<style scoped>
h3{
  display: flex;
  padding: 10px 10px 0px 15px;
  color: rgb(92, 92, 92);
}
.feeds-group{
  background-color:white;
  margin : 10px 20px 20px 5px; 
  box-shadow: 12px 12px 2px 1px rgb(173, 173, 255);
  /* 위 오른 아래 왼 */
  /* border-style: solid dotted dotted solid; 
  border-style: dotted solid solid dotted; */
  border-color: rgb(240, 240, 240) rgb(192, 192, 192) rgb(192, 192, 192) rgb(240, 240, 240);
  border-width: 2px 6px 6px 2px;
  border-style: solid none none solid;
  /* border-style: dotted none none dotted; */
  /* border-style: solid; */
  
}
</style>
