<template>
  <v-app class="text-center container">
    <h1>그룹원 보기</h1>
    <hr />
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
  name: "Member",
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
        url: `${process.env.VUE_APP_MCS_URL}/group/member/${this.nowUserGroup}`,
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
    ...mapState("account", ["nowUserGroup"]),
  },
};
</script>

<style></style>
