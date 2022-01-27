<template>
  <v-app class="box" style="background-color: rgba(98, 101, 232)">
    <br />
    <div class="container">
      <v-card flat color="rgb(98, 101, 232)">
        <div class="d-flex justify-center">
          <v-img
            class="logo-img"
            src="@/assets/logo1.png"
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
          <img
            src="@/assets/test1.png"
            alt="로고 사진입니다."
            class="group-img"
            @click="groupCreate"
          />
          <h3>그룹 추가</h3>
        </div>
        <!-- data받아오면 for문으로 돌리기 -->
        <!-- <div class="col-6" v-for="" :key="">
                    <img :src="${}" alt="그룹 사진입니다." class="group-img" @click="groupMainPage(그룹아이디넣기)">
                    <h3 style="color:white">{{}}</h3>
                </div> -->
        <div class="col-6">
          <img
            src="@/assets/logo2.png"
            alt="그룹 사진입니다."
            class="group-img"
            @click.stop="$router.push({ name: 'Main' })"
          />
          <h3>우가우가</h3>
        </div>
        <div class="col-6">
          <img
            src="@/assets/logo2.png"
            alt="그룹 사진입니다."
            class="group-img"
            @click.stop="$router.push({ name: 'Main' })"
          />
          <h3>우가우가</h3>
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
          groupId: null,
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
          Authorization: `Bearer ${token}`,
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
      this.$router.push({ name: "Main" });
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
  height: 80%;
  width: 70%;
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
