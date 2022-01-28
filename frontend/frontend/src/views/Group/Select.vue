<template>
  <v-app class="box" style="background-color: rgba(0, 0, 0, 0)">
    <br />
    <div class="container">
      <v-card flat color="rgb(0,0,0,0)">
        <div class="d-flex justify-center">
          <v-img
            class="logo-img"
            src="@/assets/logo3.png"
            alt="logo"
            max-width="200"
            max-height="200"
          >
          </v-img>
        </div>
        <v-card-title class="main-text justify-center text-h4 font-weight-bold"
          >우리집SNS</v-card-title
        >
        <br />
      </v-card>
    </div>
    <div class="container d-flex">
      <!-- 그룹 추가하는 곳 -->
      <div class="row">
        <div class="col-6">
          <v-img
            aspect-ratio="1"
            src="@/assets/group_add.png"
            alt="그룹 사진입니다."
            class="group-img"
            @click="groupCreate"
          />
          <h3>그룹 추가</h3>
        </div>
        <!-- data받아오면 for문으로 돌리기 -->
        <div class="col-6" v-for="group in groups" :key="group">
          <v-img
            aspect-ratio="1"
            :src="group.groupImgUrl"
            alt="그룹 사진입니다."
            class="group-img"
            @click="groupMainPage(group.groupId)"
          />
          <h3 style="color: white">{{ group.groupName }}</h3>
        </div>
      </div>
    </div>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: "Select",

  data() {
    return {
      groups: [
        {
          groupId: 1,
          groupName: "호박고구마",
          groupImgUrl:
            "https://img3.yna.co.kr/etc/inner/KR/2021/08/17/AKR20210817124900005_01_i_P4.jpg",
        },
        {
          groupId: 2,
          groupName: "부리부리",
          groupImgUrl:
            "https://mblogthumb-phinf.pstatic.net/20151011_285/samadare_144450208049276Fwd_PNG/%C2%AF%B1%B8%BF%E8.png?type=w2",
        },
      ],
    };
  },

  methods: {
    getGroup() {
      console.log("12333");
      const token = localStorage.getItem("jwt");
      axios({
        method: "GET",
        url: `${process.env.VUE_APP_MCS_URL}/group`,
        headers: {
          Authorization: token,
        },
      })
        .then((res) => {
          console.log(res);
          console.log("123");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 그룹 사진 클릭 시, 해당 그룹 메인페이지로 이동
    groupMainPage: function (groupId) {
      this.$store.dispatch("getMain/getMain", groupId);
      this.$router.push({ name: "Main", params: { groupId: groupId } });
    },
    groupCreate: function () {
      this.$router.push({ name: "GroupCreate" });
    },
  },
  created() {
    this.getGroup();
  },
};
</script>

<style scoped>
.box {
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}
.group-img {
  object-fit: cover;
  border: 4px solid white;
  border-radius: 20%;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.329);
}
.main-text {
  color: #fff;
}
h1 {
  color: white;
}
h3 {
  color: white;
}
</style>
