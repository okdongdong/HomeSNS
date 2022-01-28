<template>
  <v-app class="my-background">
    <v-spacer></v-spacer>

    <div class="container">
      <div class="d-flex justify-center">
        <v-card flat color="rgb(0,0,0,0)">
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
            background-color="white"
            solo
            v-model="credentials.id"
            :counter="20"
            :rules="rules.idRules"
            label="아이디"
            required
          ></v-text-field>

          <!-- 이메일 -->
          <v-text-field
            clearable
            background-color="white"
            solo
            v-model="credentials.email"
            :rules="rules.emailRules"
            label="E-mail"
            required
          ></v-text-field>

          <!-- 비밀번호 -->
          <v-text-field
            clearable
            background-color="white"
            solo
            v-model="credentials.password"
            :rules="rules.passwordRules"
            label="비밀번호"
            type="password"
            required
          ></v-text-field>

          <!-- 비밀번호 확인 -->
          <v-text-field
            clearable
            background-color="white"
            solo
            v-model="credentials.passwordConfirmation"
            :rules="rules.passwordConfirmationRules"
            label="비밀번호 확인"
            type="password"
            required
          ></v-text-field>

          <!-- 이름 -->
          <v-text-field
            clearable
            background-color="white"
            solo
            v-model="credentials.name"
            :rules="rules.nameRules"
            label="이름"
            required
          ></v-text-field>

          <!-- 연락처 -->
          <v-text-field
            clearable
            background-color="white"
            solo
            v-model="credentials.phone"
            :rules="rules.phoneRules"
            label="연락처"
            type="tel"
            required
          ></v-text-field>

          <!-- 생년월일 -->
          <v-text-field
            clearable
            background-color="white"
            solo
            v-model="credentials.bod"
            label="생년월일"
            type="date"
          ></v-text-field>
          <v-btn
            rounded
            dark
            large
            :disabled="!valid"
            color="black"
            class="mr-4"
            @click.stop="signup(credentials)"
            width="100%"
          >
            회원가입
          </v-btn>
          <v-btn text dark large rounded class="mr-4 my-2" @click.stop="move('Login')" width="100%">
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

export default {
  name: "SignUp",
  data: () => ({
    valid: true,
    credentials: {
      id: null,
      email: null,
      password: null,
      passwordConfirmation: null,
      name: null,
      phone: null,
      bod: null,
    },
    rules: {
      idRules: [
        (v) => !!v || " 아이디를 입력해주세요.",
        (v) => /^[a-zA-Z0-9]*$/.test(v) || "아이디는 영문+숫자만 입력 가능합니다.",
        (v) => !(v && v.length > 20) || "아이디는 20자 이상 입력할 수 없습니다.",
      ],

      passwordRules: [
        (v) => !!v || " 비밀번호를 입력해주세요.",
        (v) => !(v && v.length >= 20) || "패스워드는 20자 이상 입력할 수 없습니다.",
      ],

      passwordConfirmationRules: [
        (v) => v === this.credentials.password || "패스워드가 일치하지 않습니다.",
      ],

      nameRules: [
        (v) => !!v || "이름을 입력해주세요.",
        (v) => !(v && v.length >= 20) || "이름은 20자 이상 입력할 수 없습니다.",
      ],

      phoneRules: [(v) => !!v || "전화번호를 입력해주세요"],

      emailRules: [
        (v) => !!v || "이메일을 입력해주세요.",
        (v) => /.+@.+\..+/.test(v) || "유효하지 않은 이메일입니다.",
      ],
    },
  }),

  methods: {
    signup() {
      console.log("userId");
      console.log(this.credentials.userId);
      console.log("userName");
      console.log(this.credentials.userName);

      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/register`,
        data: {
          userId: this.credentials.id,
          userEmail: this.credentials.email,
          userPassword: this.credentials.password,
          userName: this.credentials.name,
          userPhone: this.credentials.phone,
          userBod: this.credentials.bod,
        },
      })
        .then((res) => {
          console.log(res);
          console.log(res.data);
          this.$router.push("Login");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    validate() {
      this.$refs.form.validate();
    },
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
