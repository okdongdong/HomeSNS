<template>
  <div
    @click.stop="$router.app._route.name != 'UserPage'?
      $router.push({
        name: 'UserPage',
        params: { userSeq: userSeq },
      }):'',
      clicked()
    "
  >
    <div class="justify-center d-flex">
      <v-avatar :size="size + 'px'" elevation="12" color="#846543">
        <div v-if="imgUrl && imgUrl!=null">
          <v-img
            :src="`https://i6e205.p.ssafy.io/${imgUrl}`"
            :style="'width:' + size + 'px;height:' + size + 'px'"
            style="overflow: hidden; object-fit: cover"
            alt="유저프로필사진"
          ></v-img>
        </div>
        <div v-else>
          <v-icon :size="size * 0.7" color="#CBCBCB">person</v-icon>
        </div>
      </v-avatar>
    </div>
    <div class="text-center">
      {{ name }}
    </div>
  </div>
</template>

<script>
export default {
  name: "FrontendProfilephoto",
  props: {
    userSeq:Number,
    size: Number,
    imgUrl: String,
    name: String,
  },
  mounted() {},

  methods: {
    clicked(){
      this.$emit('clicked')
    }
  },
};
</script>

<style scoped></style>
