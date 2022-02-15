<template>
  <v-app class="my-background">
    <v-overlay :value="nowLoading">
      <v-progress-circular
        :size="100"
        :width="10"
        color="white"
        indeterminate
      ></v-progress-circular>
    </v-overlay>

    <v-spacer></v-spacer>
    <div class="container">
      <v-card flat color="rgb(0,0,0,0)">
        <div class="d-flex justify-center">
          <v-img
            src="@/assets/logo3.png"
            alt="logo"
            max-width="200"
            max-height="200"
          >
          </v-img>
        </div>
        <div class="text-left">
          <v-card-title class="main-text justify-center font-weight-bold"
            >비밀번호 찾기</v-card-title
          >
          <br />
          <v-card-text class="main-text justify-left font-weight-bold"
            >가입 시</v-card-text
          >
          <v-card-text class="main-text justify-left font-weight-bold"
            >사용한 이메일로</v-card-text
          >
          <v-card-text class="main-text justify-left font-weight-bold"
            >비밀번호가</v-card-text
          >
          <v-card-text class="main-text justify-left font-weight-bold"
            >발송됩니다.</v-card-text
          >
        </div>
      </v-card>
    </div>
    <br />
    <br />

    <div class="container">
      <v-row justify="center">
        <v-form class="form-data" ref="form" v-model="valid" lazy-validation>
          <!-- 아이디 -->
          <v-text-field
            clearable
            background-color="white"
            solo
            v-model="credentials.id"
            :rules="rules.idRules"
            label="아이디"
            required
          ></v-text-field>
          <!-- 이메일 -->
          <v-text-field
            clearable
            solo
            background-color="white"
            v-model="credentials.email"
            :rules="rules.emailRules"
            label="이메일"
            required
          ></v-text-field>
          <br />
          <v-btn
            rounded
            dark
            large
            :disabled="!valid"
            color="black"
            class="mr-4"
            @click="findPassword()"
            width="100%"
          >
            비밀번호 찾기
          </v-btn>
          <v-btn
            text
            dark
            large
            rounded
            class="mr-4 my-2"
            @click.stop="move('Login')"
            width="100%"
          >
            뒤로가기
          </v-btn>
        </v-form>
      </v-row>
    </div>
    <v-spacer></v-spacer>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: "FindPassword",
  data: () => ({
    nowLoading: false,
    valid: true,
    credentials: {
      email: null,
      id: null,
    },
    rules: {
      idRules: [
        (v) => !!v || " 아이디를 입력해주세요.",
        (v) =>
          /^[a-zA-Z0-9]*$/.test(v) || "아이디는 영문+숫자만 입력 가능합니다.",
        (v) =>
          !(v && v.length > 20) || "아이디는 20자 이상 입력할 수 없습니다.",
      ],

      emailRules: [
        (v) => !!v || "이메일을 입력해주세요.",
        (v) => /.+@.+\..+/.test(v) || "유효하지 않은 이메일입니다.",
      ],
    },
  }),

  methods: {
    move(name) {
      this.$router.push({ name: name });
    },
    findPassword() {
      this.nowLoading = true;
      axios({
        method: "GET",
        url: `${process.env.VUE_APP_MCS_URL}/user/find/pw/`,
        params: {
          userEmail: this.credentials.email,
          userId: this.credentials.id,
        },
      })
        .then((res) => {
          this.nowLoading = false;
          console.log(res);
          console.log(res.data);
          this.$router.push("Login");
        })
        .catch((err) => {
          this.$store.commit(
            "snackbar/SET_SNACKBAR",
            "아이디 또는 이메일을 다시 확인해주세요."
          );
          this.nowLoading = false;
          console.log(err);
          console.log(err.response);
        });
    },
  },
};
</script>

<style scoped>
.my-background {
  background-color: rgba(0, 0, 0, 0);
}

.form-data {
  width: 80%;
}

.main-text {
  color: #fff !important;
  font-size: 30px;
}

.text-back {
  text-decoration: none;
  color: #fff;
  display: block;
}
</style>
