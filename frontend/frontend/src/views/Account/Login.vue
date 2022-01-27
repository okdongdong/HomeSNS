<template>
  <v-app class="my-background">
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
/* background: linear-gradient(137deg, #fff6b7, #f77c99, #6578f2, #c2ffd8);
background-size: 800% 800%;
-webkit-animation: AnimationName 8s ease infinite;
-moz-animation: AnimationName 8s ease infinite;
-o-animation: AnimationName 8s ease infinite;
animation: AnimationName 2s ease infinite; */
@-webkit-keyframes AnimationName {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
@-moz-keyframes AnimationName {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
@-o-keyframes AnimationName {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
@keyframes AnimationName {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
.my-background {
  color: #fff;
  background: linear-gradient(-45deg, #ee7752,#1d94c0, #c276f5, #23d5ab);
  background-size: 800% 800%;
  animation: AnimationName 8s ease infinite;
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  top: 35%;
  text-align: center;
}

/* .my-background   { */
  /* background-color: rgb(98, 101, 232); */
  /* background-image: linear-gradient(-225deg, #2CD8D5 0%, #C5C1FF 56%, #FFBAC3 100%); */
  /* background: linear-gradient(-225deg, #7873f5 0%, #ec77ab 100%); */
/* } */

.form-data {
  width: 80%;
}

.main-text {
  color: #fff !important;
  font-size: 30px;
}
</style>
