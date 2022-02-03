<template>
  <v-app class="text-center container">
    <h1>그룹원 보기</h1>
    <hr />
    <div class="container justify-space-around d-flex flex-wrap">
      <ProfilePhoto
        v-for="member in members"
        :key="member.id"
        :name="member.memberName"
        :size="100"
        :imgUrl="member.memberImgUrl"
        :userSeq="member.memberId"
      />

      <!-- 3의 배수가 아닐 경우 왼쪽으로 밀기용 -->
      <div style="width: 100px"></div>
      <div style="width: 100px"></div>
    </div>
  </v-app>
</template>

<script>
import axios from "axios";
// import {mapState} from 'vuex'
import ProfilePhoto from "../../components/ProfilePhoto.vue";
export default {
  components: { ProfilePhoto },
  name: "Member",
  data: () => ({
    members: [
      {
        memberId: 1,
        memberName: "아아아",
        memberImgUrl:
          "https://t1.daumcdn.net/cfile/blog/210DC14053A3908119?original",
      },
      {
        memberId: 2,
        memberName: "아아아",
        memberImgUrl:
          "https://t1.daumcdn.net/cfile/blog/210DC14053A3908119?original",
      },
      {
        memberId: 3,
        memberName: "아아아",
        memberImgUrl:
          "https://t1.daumcdn.net/cfile/blog/210DC14053A3908119?original",
      },
      {
        memberId: 4,
        memberName: "아아아",
        memberImgUrl:
          "https://t1.daumcdn.net/cfile/blog/210DC14053A3908119?original",
      },
      {
        memberId: 5,
        memberName: "아아아",
        memberImgUrl:
          "https://t1.daumcdn.net/cfile/blog/210DC14053A3908119?original",
      },
      {
        memberId: 6,
        memberName: "아아아",
        memberImgUrl:
          "https://t1.daumcdn.net/cfile/blog/210DC14053A3908119?original",
      },
      {
        memberId: 7,
        memberName: "아아아",
        memberImgUrl:
          "https://t1.daumcdn.net/cfile/blog/210DC14053A3908119?original",
      },
    ],
  }),
  methods: {
    getMembers() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/group`,
        headers: { Authorization: token },
        // params: params,
      })
        .then((res) => {
          console.log(res);
          // this.members = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
