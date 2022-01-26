<template>
  <div>
    <v-avatar :size="size + 'px'" elevation="12" color="#846543">
      <div v-if="imgUrl">
        <img :src="imgUrl" alt="유저프로필사진" class="profile-img" />
      </div>
      <div v-else>
        <v-icon :size="size * 0.7" color="#CBCBCB">person</v-icon>
      </div>
    </v-avatar>
  </div>
</template>

<script>
export default {
  name: "FrontendProfilephoto",
  props: {
    size: Number,
    imgUrl: String,
  },
  mounted() {},

  methods: {},
};
</script>

<style scoped>
.profile-img {
  overflow: hidden;
  width: 150px;
  height: 150px;
  border: 2px solid white;
  object-fit: cover;
}</style>
