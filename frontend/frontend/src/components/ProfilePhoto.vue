<template>
  <div>
    <div class="justify-center d-flex">
      <v-avatar :size="size + 'px'" elevation="12" color="#846543">
        <div v-if="imgUrl">
          <img
            :src="imgUrl"
            :style="'width:' + size + 'px;height:' + size + 'px'"
            style="overflow: hidden; object-fit: cover"
            alt="유저프로필사진"
          />
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
    size: Number,
    imgUrl: String,
    name: String,
  },
  mounted() {},

  methods: {},
};
</script>

<style scoped></style>
