<template>
  <v-app class="text-center container">
    <h1>그룹프로필</h1>
        <div class="justify-center d-flex">

    <v-img
      aspect-ratio="1"
      :src="nowGroup.groupProfileImageUrl"
      alt="그룹 사진입니다."
      class="group-img"
      max-width=200
    />
        </div>
    <h3 style="color: white; margin-top: 8px">{{ nowGroup.groupName }}</h3>

    <h1>그룹원</h1>
    <div class="container justify-space-around d-flex flex-wrap">
      <ProfilePhoto
        v-for="member in members"
        :key="member.userId"
        :name="member.userName"
        :size="100"
        :imgUrl="member.userImgUrl"
        :userSeq="member.userId"
      />

      <!-- 3의 배수가 아닐 경우 왼쪽으로 밀기용 -->
      <div style="width: 100px"></div>
      <div style="width: 100px"></div>
    </div>
  </v-app>
</template>

<script>
import axios from "axios";
import ProfilePhoto from "../../components/ProfilePhoto.vue";
import { mapState } from "vuex";

export default {
  components: { ProfilePhoto },
  name: "GroupProfile",
  data: () => ({
    members: [],
  }),
  created() {
    this.getMembers();
  },
  methods: {
    getMembers() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/group/member/${this.nowGroup.groupId}`,
        headers: { Authorization: token },
        // params: params,
      })
        .then((res) => {
          console.log(res);
          this.members = res.data.userDtoList;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    ...mapState("account", ["nowGroup"]),
  },
};
</script>

<style scoped>
.group-img {
  object-fit: cover;
  border: 4px solid white;
  border-radius: 20%;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.329);
}
</style>
