<template>
  <v-app class="my-background" fluid>
    <v-spacer></v-spacer>
    <div class="container">
      <div class="d-flex justify-center">
        <v-card flat color="rgba(0,0,0,0)">
          <div class="d-flex justify-center">
            <v-img src="@/assets/logo3.png" alt="logo" max-width="200" max-height="200"> </v-img>
          </div>
          <v-card-title class="main-text justify-center font-weight-bold">우리집 SNS</v-card-title>
        </v-card>
      </div>
      <br />
      <v-row justify="center">
        <v-form class="form-data" ref="form" v-model="valid" lazy-validation>
          <!-- 아이디 -->
          <v-text-field
            clearable
            maxlength="20"
            background-color="white"
            solo
            v-model="credentials.userId"
            :rules="rules.idRules"
            label="아이디"
            required
          ></v-text-field>

          <!-- 비밀번호 -->
          <v-text-field
            clearable
            background-color="white"
            solo
            v-model="credentials.userPassword"
            :rules="rules.passwordRules"
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
            class="mr-4"
            @click="login(credentials)"
            width="100%"
          >
            로그인
          </v-btn>
          <v-btn
            rounded
            dark
            large
            color="black"
            class="mr-4 my-3"
            @click="move('SignUp')"
            width="100%"
          >
            회원가입
          </v-btn>
          <div class="d-flex justify-center">
            <v-btn text dark large rounded @click="move('FindId')"> 아이디 찾기 </v-btn>
            <v-btn text dark large disabled> | </v-btn>
            <v-btn text dark large rounded @click="move('FindPassword')"> 비밀번호 찾기 </v-btn>
          </div>
        </v-form>
      </v-row>
    </div>
    <v-spacer></v-spacer>
    <v-spacer></v-spacer>
  </v-app>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "Login",
  data: () => ({
    valid: true,
    credentials: {
      userId: null,
      userPassword: null,
    },
    rules: {
      idRules: [(v) => !!v || " 아이디를 입력해주세요."],

      passwordRules: [(v) => !!v || " 비밀번호를 입력해주세요."],
    },
  }),

  methods: {
    ...mapActions("account", ["login"]),
    move(name) {
      this.$router.push({ name: name });
    },
  },
};
</script>

<style scoped>
.my-background {
  background-color: rgba(0,0,0,0);
}
.form-data {
  width: 80%;
}

.main-text {
  color: #fff !important;
  font-size: 30px;
}
</style>
