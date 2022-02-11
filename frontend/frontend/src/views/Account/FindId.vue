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
        <v-card-title class="main-text justify-center font-weight-bold"
          >아이디 찾기</v-card-title
        >
        <br />
        <div class="text-left">
          <v-card-text class="main-text justify-left font-weight-bold"
            >가입 시</v-card-text
          >
          <v-card-text class="main-text justify-left font-weight-bold"
            >사용한 이메일로</v-card-text
          >
          <v-card-text class="main-text justify-left font-weight-bold"
            >아이디가</v-card-text
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
        <v-form
          class="form-data"
          ref="form"
          v-model="valid"
          lazy-validation
          @keyup.enter="findId()"
        >
          <!-- 이메일 -->
          <v-text-field
            clearable
            solo
            background-color="white"
            v-model="credentials.email"
            :rules="rules.emailRules"
            label="이메일"
            required
            @keyup.enter="findId()"
          ></v-text-field>
          <br />
          <v-btn
            rounded
            dark
            large
            :disabled="!valid"
            color="black"
            class="mr-4"
            @click="findId()"
            width="100%"
          >
            아이디 찾기
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
  name: "FindId",
  data: () => ({
    nowLoading: false,
    valid: true,
    credentials: {
      email: null,
    },
    rules: {
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
    findId() {
      this.nowLoading = true;
      axios({
        method: "GET",
        url: `${process.env.VUE_APP_MCS_URL}/user/find/id/${this.credentials.email}`,
      })
        .then((res) => {
          console.log(res);
          console.log(res.data);
          this.$router.push("Login");
          this.nowLoading = false;
        })
        .catch((err) => {
          console.log(err);
          console.log(err.response);
          this.nowLoading = false;
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
