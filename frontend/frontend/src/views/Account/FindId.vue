<template>
  <v-app class="my-background">
    <div class="container">
      <v-card
        flat
        color="rgb(98, 101, 232)"
      >
        <div class="d-flex justify-center">
          <v-img
            src="@/assets/logo2.png" alt="logo"
            max-width="200"
            max-height="200"
          >
          </v-img>
        </div>
        <v-card-title class="main-text justify-center text-h4 font-weight-bold">아아디 찾기</v-card-title>
        <br />
        <v-card-title class="main-text justify-left text-h4 font-weight-bold">가입시 <br />사용합 이메일로<br />아이디가<br />발송됩니다.</v-card-title>
      </v-card>
    </div>
    <br />
    
    <div class="container">
      <v-row justify="center">
        <v-form class="form-data" ref="form" v-model="valid" lazy-validation>
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
            @click="login()"
            width="100%"
          >
            아이디 찾기
          </v-btn>
        <a href=""><v-card-title class="main-text justify-center">뒤로</v-card-title></a>
        </v-form>
      </v-row>
    </div>
  </v-app>
</template>

<script>
export default {
  name: "FindId",
  data: () => ({
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
  },
};
</script>

<style scoped>
  .my-background {
    background-color: rgb(98, 101, 232);
  }

  .form-data {
    width: 80%;
  }

  .main-text {
    color: #fff;
  }
</style>
