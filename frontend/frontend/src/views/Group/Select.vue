<template>
  <v-app class="box" style="background-color: rgba(0, 0, 0, 0)">
    <v-btn
      absolute
      left
      class="mt-5"
      color="white"
      text
      large
      @click.stop="logout()"
      ><v-icon>arrow_back</v-icon>
      <h3>로그아웃</h3></v-btn
    >
    <div style="height: 80px"></div>
    <div class="container">
      <v-card flat color="rgb(0,0,0,0)">
        <v-row>
          <v-col offset="3" cols="6">
            <v-img
              class="logo-img"
              src="@/assets/logo3.png"
              alt="logo"
              aspect-ratio="1"
            >
            </v-img>
          </v-col>
        </v-row>
        <v-card-title class="main-text justify-center font-weight-bold"
          >우리집SNS</v-card-title
        >
        <v-spacer></v-spacer>
      </v-card>
    </div>
    <div class="container d-flex">
      <!-- 그룹 추가하는 곳 -->
      <v-row class="ma-1" style="">
        <v-col class="pa-5" cols="6">
          <group-create-popup />

          <h3 style="margin-top: 8px">그룹 추가</h3>
        </v-col>
        <!-- data받아오면 for문으로 돌리기 -->
        <v-col
          class="pa-5"
          v-for="group in groups"
          :key="group.groupId"
          cols="6"
        >
          <v-img
            aspect-ratio="1"
            :src="group.groupProfileImageUrl"
            alt="그룹 사진입니다."
            class="group-img"
            @click="groupMainPage(group)"
          />
          <h3 style="color: white; margin-top: 8px">{{ group.groupName }}</h3>
        </v-col>
      </v-row>
    </div>
    <v-spacer></v-spacer>
  </v-app>
</template>

<script>
import axios from "axios";
import GroupCreatePopup from "../../components/GroupCreatePopup.vue";
import { mapActions } from "vuex";
export default {
  components: { GroupCreatePopup },
  name: "Select",

  data() {
    return {
      groups: [],
    };
  },

  methods: {
    ...mapActions("account", ["logout"]),
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
          this.groups = res.data.groupDtoList;
          console.log(res);
          console.log("123");
        })
        .catch((err) => {
          console.log(err);
          console.log("234");
        });
    },
    // 그룹 사진 클릭 시, 해당 그룹 메인페이지로 이동
    groupMainPage: function (groupInfo) {
      console.log("groupInfo", groupInfo);
      this.$store.dispatch("account/setNowGroup", groupInfo);
      this.$router.push({
        name: "Main",
        params: { groupId: groupInfo.groupId },
      });
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
  font-size: 30px;
}
h1 {
  color: white;
}
h3 {
  color: white;
}
.group-box {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.group-box::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>
