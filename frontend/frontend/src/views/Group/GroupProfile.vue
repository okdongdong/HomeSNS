<template>
  <v-app class="text-center container">
    <h1>그룹프로필</h1>
    <div class="justify-center d-flex">
      <v-img
        aspect-ratio="1"
        :src="nowGroup.groupProfileImageUrl"
        alt="그룹 사진입니다."
        class="group-img"
        max-width="200"
      />
    </div>
    <h2 style="color: white; margin-top: 8px">{{ nowGroup.groupName }}</h2>

    <div class=" content-box my-5">
      <h2>그룹원</h2>

      <div class="justify-space-around d-flex flex-wrap">
        <ProfilePhoto
          class="ma-3"
          v-for="member in members"
          :key="member.userSeq"
          :name="member.userName"
          :size="100"
          :imgUrl="member.userImgUrl"
          :userSeq="member.userSeq"
        />

        <!-- 3의 배수가 아닐 경우 왼쪽으로 밀기용 -->
        <div class="ma-3" style="width: 100px"></div>
        <div class="ma-3" style="width: 100px"></div>
      </div>
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
.content-box {
  /* border: solid 2px black; */
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.6);
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329);
}

.group-img {
  object-fit: cover;
  border: 4px solid white;
  border-radius: 20%;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.329);
}
</style>
