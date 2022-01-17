<template>
  <v-bottom-navigation v-model="value">
    <v-btn value="recent">
      <span>게시물</span>

      <v-icon>mdi-history</v-icon>
    </v-btn>

    <v-btn value="favorites">
      <span>추억여행</span>

      <v-icon>mdi-heart</v-icon>
    </v-btn>

    <v-btn value="nearby">
      <span>일정표</span>

      <v-icon>mdi-map-marker</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
    name: 'FrontendBottomnavbar',

    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style lang="scss" scoped>

</style>