<template>
  <div class="gmap-marker-clustering" id="부엉부엉시부엉">
    <GmapMap  style="height:100%" :center="center" :zoom="15">
      <GmapCluster
        :zoomOnClick="true"
        :enableRetinaIcons="true"
        :minimumClusterSize="3"
        @click="checkcluster($event)"
        :styles="clusterStyles"
      >
        <GmapMarker
          :key="index"
          v-for="(m, index) in markers"
          :position="m.position"
          @click="getPhotos(m)"
          :clickable="true"
          :icon="{
            url: 'https://i6e205.p.ssafy.io/'+m.files[0].markerImg,
            scaledSize: { width: 100, height: 100 },
            position: m.position,
          }"
        />
        <v-dialog v-model="show" scrollable max-width="400px">
          <v-card>
            <div class="justify-space-between d-flex align-center">
              <v-btn color="" text @click="show = !show">
                <v-icon>close</v-icon>
              </v-btn>
            </div>
            <v-divider></v-divider>
            <v-container>
            <v-row>
              <v-col
                v-for="(file, i) in currPhotos[0]"
                :key="i"
                class="pa-0"
                cols="4"
              >
              <!-- :src="`https://i6e205.p.ssafy.io/${file.markerImg}`" -->
              <v-img
                  v-if="file.type == 'img'" 
                  src="'http://image.yes24.com/momo/TopCate282/MidCate005/28147902.jpg'"
                  aspect-ratio="1"
                  class="grey lighten-2"
                  @click="$router.push({name : 'Detail', params : {feedId : file.feedId}})"
                >
                  <template v-slot:placeholder>
                    <v-row
                      class="fill-height ma-0"
                      align="center"
                      justify="center"
                    >
                      <v-progress-circular
                        indeterminate
                        color="grey lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>
                </v-img>
                <!-- :src="`https://i6e205.p.ssafy.io/${photo.markerImg}`" -->
                <v-video
                  v-else
                  :src="`https://i6e205.p.ssafy.io/${photo.markerImg}`" 
                  aspect-ratio="1"
                  class="grey lighten-2"
                >
                <template v-slot:placeholder>
                    <v-row
                      class="fill-height ma-0"
                      align="center"
                      justify="center"
                    >
                      <v-progress-circular
                        indeterminate
                        color="grey lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>
                </v-video>
              </v-col>
            </v-row>
            </v-container>
          </v-card>
        </v-dialog>
      </GmapCluster>
    </GmapMap>
  </div>
</template>

<script>
import axios from 'axios';
import {gmapApi} from 'vue2-google-maps'
import GmapCluster from 'vue2-google-maps/dist/components/cluster';
import { mapState } from "vuex";
export default {
  name: "Location",
  components: {
    GmapCluster,
  },
  data() {
    return {
      show : false,
      currPhotos : [],
      clusterStyles:[
        {
          textColor : 'white',
          url : require('@/assets/pin1.png'),
          anchorIcon:[70,0],
          textsize:10
        },
        {
          textColor : 'white',
          url : require('@/assets/pin1.png'),
          anchorIcon:[70,0],
          textsize:10
        },
        {
          textColor : 'white',
          url : require('@/assets/pin1.png'),
          anchorIcon:[70,0],
          textsize:10
        },
      ],
      center: { lat: 37.501250, lng: 127.039523 }, // 처음 센터 값
      currentPlace: null, // 현재위치
      markers: [],
    };
  },
  methods :{
    getLocation(){
      const token = localStorage.getItem("jwt");
      axios({
        method : "GET",
        url : `${process.env.VUE_APP_MCS_URL}/location/${this.nowGroup.groupId}`,
        headers: { Authorization: token },
      })
      .then((res)=>{
        console.log('들고온파일')
        console.log(res.data)
        for(let i=0;i<res.data.groupFeedList.length;i++){
          if(this.markers.length == 0){
            this.markers.push({
              position:{
                lat : res.data.groupFeedList[i].lat,
                lng : res.data.groupFeedList[i].lng
              },
              files : [],
            })
          }else{
            let flag = 0
            for(let j=0;j<this.markers.length;j++){
              if(res.data.groupFeedList[i].lat == this.markers[j].position.lat && res.data.groupFeedList[i].lng == this.markers[j].position.lng){
                flag = 1
                break
              }
            }
            if(!flag){
              this.markers.push({
              position:{
                lat : res.data.groupFeedList[i].lat,
                lng : res.data.groupFeedList[i].lng
                },
                files : [],
              })
            }
          }
        }
        // console.log('==================')
        // console.log(this.markers)
        return res
      })
      .then((res)=>{
        for(let k=0;k<res.data.groupFeedList.length;k++){
          console.log('시부레')
          for(let m=0;m<this.markers.length;m++){
            console.log('시부레')
            if(this.markers[m].position.lat == res.data.groupFeedList[k].lat && this.markers[m].position.lng == res.data.groupFeedList[k].lng){
              for(let n=0;n<res.data.groupFeedList[k].fileDto.length;n++){
                if(res.data.groupFeedList[k].fileDto[n].fileContentType.includes('image')){
                  this.markers[m].files.push({
                    markerImg : res.data.groupFeedList[k].fileDto[n].fileUrl,
                    feedId : res.data.groupFeedList[k].feedId,
                    type : 'img'
                  })
                }else{
                  this.markers[m].files.push({
                    markerImg : res.data.groupFeedList[k].fileDto[n].fileUrl,
                    feedId : res.data.groupFeedList[k].feedId,
                    type : 'video'
                  })
                }
                
                }
              }
            }
          }
        // console.log('마커에 들어가죠!!!!!!!!!!!!!!!!')
        // console.log(this.markers)
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    checkcluster(data) {
      console.log(data.getMarkers());
    },
    getPhotos(marker){
      this.currPhotos = []
      this.show = true
      console.log(this.show)
      this.center = marker.position
      this.currPhotos.push(marker.files)
      console.log('=============currPhotos====================')
      console.log(this.currPhotos)
    },
  },
  created() {
    this.getLocation();
  },
  computed : {
    ...mapState("account",["nowGroup"]),
    google : gmapApi,
  },
};
</script>

<style>
.gmap-marker-clustering {
  top:80px;
  width:100%;
  height: calc(100vh - 160px);
}

.gm-style>div>div>div>div> img {
  /* border :white 4px solid !important; */
  /* box-shadow: 0 0 5px 5px black !important; */
  /* border-color :black; */
  border-radius : 25px !important;
}
</style>
