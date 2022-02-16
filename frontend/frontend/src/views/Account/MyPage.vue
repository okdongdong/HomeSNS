<template>
  <v-app class="container">
    <h2 class="my-3 py-3 content-box text-center">개인 정보 수정</h2>
    <div class="content-box py-5 justify-center d-flex">
        <v-form class="form-data my-5" ref="form" v-model="valid" lazy-validation>
          <div class="border">
            <div v-if="image" class="d-flex align-items-center">
              <profile-photo :size="160" :imgUrl="image" :name="userName" />
            </div>
            <div v-else>
              <profile-photo :size="160" :imgUrl="'@/assets/emptyImg.png'" :name="userName" />
            </div>
          </div>
          <v-file-input
            type="file"
            accept="image/*"
            @change="selectFile"
            class="form-control-file"
            id="profile_path"
            enctype="multipart/form-data"
          ></v-file-input>
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
            label="휴대전화"
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
    </div>
  </v-app>
</template>

<script>
import { mapState, mapActions } from "vuex";
import axios from "axios";
import ProfilePhoto from "../../components/ProfilePhoto.vue";
export default {
  components: { ProfilePhoto },
  name: "MyPage",
  data: () => ({
    valid: true,
    image: null,
    previewImage: undefined,
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
    selectFile: function (file) {
      this.image = file;
      this.previewImage = URL.createObjectURL(this.image);
    },
    updateProfile() {
      let data = new FormData();
      data.append("userEmail", this.credentials.email);
      data.append("userPassword", this.credentials.password);
      data.append("userName", this.credentials.name);
      data.append("userPhone", this.credentials.phone);
      data.append("userBod", this.credentials.bod);
      data.append("profileImage", this.image);
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/register`,
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: data,
      })
        .then((res) => {
          console.log(res);
          console.log(res.data);
          this.$router.push("Login");
        })
        .catch((err) => {
          this.$store.commit(
            "snackbar/SET_SNACKBAR",
            "양식을 다시 확인해주세요."
          );
          console.log(err);
          console.log(err.response);
        });
    },

    validate() {
      this.$refs.form.validate();
    },
  },
  computed: {
    ...mapState("account", ["userSeq", "userName"]),
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
  /* color: #fff !important; */
  font-size: 30px;
}
.profile-img {
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
.content-box {
  /* border: solid 2px black; */
  border-radius: 5px;
  background-color: rgba(255, 255, 255, 0.5);
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329);
}
</style>
