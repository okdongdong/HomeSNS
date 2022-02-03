<template>
  <v-app>
    <GmapMap class="map-style" :center="center" :zoom="12"  @click="mark">
      <gmap-cluster
        :zoomOnClick="true"
        :enableRetinaIcons="true"
        :minimumClusterSize="3"
      >
      <GmapMarker
        :key="index"
        v-for="(m, index) in markers"
        :position="m.position"
        @click="center = m.position"
        :clickable="true"
        :draggable="true"
        @drag="updateMarker(index, $event.latLng)"
      />
      </gmap-cluster>
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
      center: { lat: 37.5642135, lng: 127.0016985 }, // 처음 센터 값 (서울)
      currentPlace: null, // 현재위치
      markers: [
        
          {position : { lat: -31.56391, lng: 147.154312 }},
          {position : { lat: -33.718234, lng: 150.363181 }},
          {position : { lat: -33.727111, lng: 150.371124 }},
          {position : { lat: -33.848588, lng: 151.209834 }},
          {position : { lat: -33.851702, lng: 151.216968 }},
          {position : { lat: -34.671264, lng: 150.863657 }},
          {position : { lat: -35.304724, lng: 148.662905 }},
          {position : { lat: -36.817685, lng: 175.699196 }},
        
      ],
    };
  },

  mounted() {
    // google: gmapApi,
  },

  methods: {
    getCurrLocation() {
      navigator.geolocation.getCurrentPosition((position) => {
        const currLocation = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
        this.center = currLocation;
        this.markers.push({ position: currLocation });
      });
    },
    setPlace(place) {
      this.currentPlace = place;
    },
    addMarker() {
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng(),
        };
        this.markers.push({ position: marker });
        // this.places = [this.currentPlace]
        this.center = marker;
        // this.currentPlace = null;
      }
    },
    mark(event) {
      const marker = {
        lat: event.latLng.lat(),
        lng: event.latLng.lng(),
      };
      this.markers.push({ position: marker });
      // this.places = [this.currentPlace]
      this.center = marker;
    },
    updateMarker(index, location) {
      const marker = {
        lat: location.lat(),
        lng: location.lng(),
      };
      this.markers.push({ position: marker });
      // this.places = [this.currentPlace]
    },
  },
};
</script>

<style scoped>
  .map-style{
    width: 100%; 
    height: calc(100% - 160px)
  }

</style>
