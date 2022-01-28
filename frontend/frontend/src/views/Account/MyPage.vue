<template>
  <v-app>
    <p class="main-text ml-5 mt-5">개인 정보 수정</p>
    <div class="container">
      <v-row justify="center">
        <v-form class="form-data" ref="form" v-model="valid" lazy-validation>
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
            변경하기
          </v-btn>
          <v-btn
            dark
            large
            rounded
            color="black"
            class="mr-4 my-2"
            @click.stop="
              $router.push({ name: 'UserPage', params: { userSeq: userSeq } })
            "
            width="100%"
          >
            취소
          </v-btn>
        </v-form>
      </v-row>
    </div>
  </v-app>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "MyPage",
  data: () => ({
    valid: true,
    credentials: {
      email: null,
      password: null,
      name: null,
      phone: null,
      bod: null,
    },
    rules: {
      passwordRules: [
        (v) => !!v || " 비밀번호를 입력해주세요.",
        (v) =>
          !(v && v.length >= 20) || "패스워드는 20자 이상 입력할 수 없습니다.",
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
    ...mapActions(["signup"]),
    validate() {
      this.$refs.form.validate();
    },
  },
  computed: {
    ...mapState("account", ["userSeq"]),
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
  /* color: #fff !important; */
  font-size: 30px;
}
</style>
