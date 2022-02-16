<template>
<div class="gmap-marker-clustering">

    <GmapMap id="부엉부엉시부엉" style="height:100%" :center="center" :zoom="12">
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
          @click="center = m.position"
          :clickable="true"
          :icon="{
            url: m.files[0].markerImg,
            scaledSize: { width: 100, height: 100 },
            position: m.position,
          }"
        />
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
    GmapCluster
  },
  data() {
    return {
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
      center: { lat: -31.56391, lng: 147.154312 }, // 처음 센터 값
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
        console.log('==================')
        console.log(this.markers)
        return res
      })
      .then((res)=>{
        for(let k=0;k<res.data.groupFeedList.length;k++){
          console.log('시부레')
          for(let m=0;m<this.markers.length;m++){
            if(this.markers[m].position.lat == res.data.groupFeedList[k].lat && this.markers[m].position.lng == res.data.groupFeedList[k].lng){
              for(let n=0;n<res.data.groupFeedList[k].fileDto.length;n++){
                this.markers[m].files.push({
                    markerImg : res.data.groupFeedList[k].fileDto[n].fileUrl,
                    feedId : res.data.groupFeedList[k].feedId
                  })
                }
              }
            }
          }
        console.log(this.markers)
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    checkcluster(data) {
      console.log(data.getMarkers());
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

<style scoped>
.gmap-marker-clustering {
  top:80px;
  width:100%;
  height: calc(100vh - 160px);
}


</style>
