<template>
  <v-app class="my-background">
    <v-spacer></v-spacer>
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
          >그룹 입장</v-card-title
        >
        <v-spacer></v-spacer>
      </v-card>
    </div>
    <div class="container">
      <v-row justify="center">
        <v-form class="form-data" ref="form" v-model="valid" lazy-validation>
          <div class="border">
            <template v-if="image" class="d-flex align-items-center">
              <v-img :src="previewImage" class="group-img" />
              <!-- <p class="mb-1">file name: {{ image.name }}</p>
                        <p class="mb-1">size: {{ image.size/1024 }}KB</p> -->
            </template>
            <template v-else> </template>
          </div>

          <v-text-field
            clearable
            background-color="white"
            :counter="20"
            maxlength="20"
            :rules="rules.groupNameRules"
            solo
            v-model="groupId"
            label="그룹 아이디"
            required
          ></v-text-field>
          <!--  비밀번호 -->
          <v-text-field
            clearable
            solo
            background-color="white"
            :rules="rules.passwordRules"
            v-model="password"
            maxlength="20"
            label="비밀번호"
            type="password"
            required
          ></v-text-field>
          <v-btn
            rounded
            dark
            large
            :disabled="!valid"
            color="black"
            class="mr-4 my-3"
            @click="enterGroup()"
            width="100%"
          >
            입장하기
          </v-btn>
          <v-btn
            text
            rounded
            dark
            large
            color="white"
            class="mr-4 my-3"
            @click="back()"
            width="100%"
          >
            뒤로가기
          </v-btn>
        </v-form>
      </v-row>
    </div>
    <v-spacer></v-spacer>
    <v-spacer></v-spacer>
  </v-app>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";

export default {
  name: "GroupEnter",
  data() {
    return {
      valid: true,
      groupId: null,
      password: null,
      rules: {
        groupNameRules: [
          (v) => !!v || "그룹아이디를 입력해주세요.",
          (v) =>
            !(v && v.length >= 20) || "그룹명은 20자 이상 입력할 수 없습니다.",
        ],
        passwordRules: [
          (v) => !!v || " 비밀번호를 입력해주세요.",
          (v) =>
            !(v && v.length >= 20) ||
            "패스워드는 20자 이상 입력할 수 없습니다.",
        ],
      },
    };
  },
  methods: {
    ...mapActions("notice", ["connect"]),
    back: function () {
      this.$router.push({ name: "Select" });
    },

    enterGroup: function () {
      //연동할때 주석 풀기
      const token = localStorage.getItem("jwt");
      axios({
        method: "post",
        url: `${process.env.VUE_APP_MCS_URL}/group/member`,
        headers: {
          Authorization: token,
        },
        data: {
          groupId: this.groupId,
          groupPassword: this.password,
        },
      })
        .then((res) => {
          const groupInfo = res.data.groupDto;
          this.$store.dispatch("account/setNowGroup", groupInfo);
          this.$router.push({
            name: "Main",
            params: { groupId: this.groupId },
          });
        })
        .then(() => {
          this.connect({
            targetUserSeq: -1,
            noticeType: "member",
            noticeContentId: this.userSeq,
          });
        })
        .catch(() => {
          this.$store.commit(
            "snackbar/SET_SNACKBAR",
            "그룹아이디와 비밀번호를 다시 확인해주세요."
          );
        });
    },
  },
  computed: {
    ...mapState("account", ["userSeq"]),
  },
};
</script>

<style scoped>
.my-background {
  background-color: rgba(0, 0, 0, 0);
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}
.form-data {
  width: 80%;
}
.main-text {
  color: #fff;
}
.group-img {
  display: block;
  margin: 0px auto;
  /* height: 70%; */
  width: 50%;
  object-fit: cover;
  border: 4px solid white;
  border-radius: 20%;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.329);
  padding-bottom: 5%;
}
</style>
