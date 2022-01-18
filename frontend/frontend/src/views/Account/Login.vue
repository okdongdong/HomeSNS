<template>
  <v-app class="my-background">
    <v-spacer></v-spacer>
    <div class="container">
      <div class="d-flex justify-center">
        <img src="@/assets/logo2.png" alt="logo" />
      </div>
      <v-form ref="form" v-model="valid" lazy-validation>
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
          <v-btn text dark large rounded @click="move('FindId')">
            아이디 찾기
          </v-btn>
          <v-btn text dark large disabled> | </v-btn>
          <v-btn text dark large rounded @click="move('FindPassword')">
            비밀번호 찾기
          </v-btn>
        </div>
      </v-form>
    </div>
    <v-spacer></v-spacer>
    <v-spacer></v-spacer>
  </v-app>
</template>

<script>
export default {
  name: "Login",
  data: () => ({
    valid: true,
    credentials: {
      id: null,
      password: null,
    },
    rules: {
      idRules: [(v) => !!v || " 아이디를 입력해주세요."],

      passwordRules: [(v) => !!v || " 비밀번호를 입력해주세요."],
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
</style>
