<template>
  <v-dialog v-model="dialog" scrollable max-width="400px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on" icon>
        <v-icon>more_vert</v-icon>
      </v-btn>
    </template>
    <v-card class="rounded-xl">
      <div>
        <v-card-title class="justify-center card-title red--text" >삭제</v-card-title>
      </div>
      <v-divider></v-divider>
      <div @click.stop="$router.push({ name: 'FeedCreate', params: { feedId: feedId }})">
        <v-card-title class="justify-center card-title">수정</v-card-title>
      </div>
      <v-divider></v-divider>
      <div>
        <v-card-title class="justify-center card-title">취소</v-card-title>
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "FeedPopup",
  props:{
    feedId : Number,
  },
  data: () => ({
    dialog:false,
  }),
};
</script>

<style scoped>
 .card-title{
   font-size:1.2rem;
 }
</style>
