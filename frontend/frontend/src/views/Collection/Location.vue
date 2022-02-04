<template>
  <v-app>
    <GmapMap class="gmap-marker-clustering" :center="center" :zoom="12">
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
            url: m.markerImg,
            scaledSize: { width: 100, height: 100 },
            position: m.position,
          }"
        />
      </GmapCluster>

    </GmapMap>
  </v-app>
</template>

<script>
// import {gmapApi} from 'vue2-google-maps'
import GmapCluster from 'vue2-google-maps/dist/components/cluster';
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
      markers: [
        
          {
          position: { lat: -31.56391, lng: 147.154312 },
          markerImg: "https://cdn.iworldtoday.com/news/photo/201603/188668_62908_1934.png",
        },
        {
          position: { lat: -33.718234, lng: 150.363181 },
          markerImg:
            "https://images.chosun.com/resizer/KRM2YgbS9kVVz7QMgCMhH5eUaro=/596x795/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/B6DG6T572KT44ELDCUB5IYGTKU.jpg",
        },
        {
          position: { lat: -33.727111, lng: 150.371124 },
          markerImg: "http://image.newsis.com/2021/07/05/NISI20210705_0000781122_web.jpg",
        },
        {
          position: { lat: -33.848588, lng: 151.209834 },
          markerImg: "https://news.imaeil.com/photos/2021/03/05/2021030509243023923_l.jpg",
        },
        {
          position: { lat: -33.851702, lng: 151.216968 },
          markerImg:
            "https://w.namu.la/s/76fae914da2d988202808b9f68f39d49631dd668dd31403910ed389d13db136fda62fe2be3b7a2d88168359f60d1f4fa780ffcb475c4cccafac35b703cc15a718a59320897465d17bb376dc67a8842601c0b1120428fa86d75ad2f8fce154bae",
        },
        {
          position: { lat: -34.671264, lng: 150.863657 },
          markerImg: "http://img.segye.com/content/image/2019/04/12/20190412504845.jpg",
        },
        {
          position: { lat: -35.304724, lng: 148.662905 },
          markerImg: "https://t1.daumcdn.net/cfile/tistory/998E2C4B5E17DD5C32",
        },
        {
          position: { lat: -36.817685, lng: 175.699196 },
          markerImg: "https://photo.newsen.com/mphoto/2019/07/18/201907181902081910_1.jpg",
        },

        
      ],
    };
  },
  created() {
    this.$gmapApiPromiseLazy().then(() => {
      this.initialize(); //init once  library has been loaded
    });
  },

  methods: {
       checkcluster(data) {
      console.log(data.getMarkers());
      }
  },
};
</script>

<style scoped>
.gmap-marker-clustering {
  width: 100%;
  height: calc(100vh - 160px);
}


</style>
