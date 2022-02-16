<template>
  <v-app class="container">
    <h2 class="my-3 py-3 content-box text-center">개인 정보 수정</h2>
    <div class="content-box py-5 justify-center d-flex">
      <div style="width:100%">

      <div class="mb-5">
        <profile-photo :size="80" :imgUrl="userProfileUrl" />
      </div>
      <v-row class="mx-2 mb-3">
        <v-col>
          <v-btn :dark="step == 1" block @click.stop="step = 1">
            유저정보 변경
          </v-btn>
        </v-col>
        <v-col>
          <v-btn :dark="step == 2" block @click.stop="step = 2">
            비밀번호 변경
          </v-btn>
        </v-col></v-row
      >

      <v-stepper v-model="step" flat style="background-color: rgba(0, 0, 0, 0)">
        <v-stepper-items>
          <!-- 페이지 1 : 플레이어 수 입력 -->
          <v-stepper-content step="1">
            <!-- <div class="border">
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
          ></v-file-input> -->

            <v-form
              class="form-data"
              ref="form"
              v-model="valid"
              lazy-validation
              style="width: 100%"
            >
              <!-- 이메일 -->
              <v-text-field
                clearable
                background-color="white"
                solo
                v-model="userInfo.userEmail"
                :rules="rules.emailRules"
                label="E-mail"
                required
              ></v-text-field>

              <!-- 이름 -->
              <v-text-field
                clearable
                background-color="white"
                solo
                v-model="userInfo.userName"
                :rules="rules.nameRules"
                label="이름"
                required
              ></v-text-field>

              <!-- 연락처 -->
              <v-text-field
                clearable
                background-color="white"
                solo
                v-model="userInfo.userPhone"
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
                v-model="userInfo.userBod"
                label="생년월일"
                type="date"
              ></v-text-field>
              <v-btn
                rounded
                dark
                large
                :disabled="!valid"
                color="black"
                class="my-4"
                @click.stop="updateProfile()"
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
                  $router.push({
                    name: 'UserPage',
                    params: { userSeq: userSeq },
                  })
                "
                width="100%"
              >
                뒤로가기
              </v-btn>
            </v-form>
          </v-stepper-content>
          <v-stepper-content step="2">
            <v-form
              class="form-data"
              ref="form"
              v-model="valid2"
              lazy-validation
              style="width: 100%"
            >
            <!-- 비밀번호 -->

              <v-text-field
                clearable
                background-color="white"
                solo
                v-model="password"
                :rules="rules.passwordRules"
                label="변경할 비밀번호"
                type="password"
                required
              ></v-text-field>

              <!-- 비밀번호 확인 -->
              <v-text-field
                clearable
                background-color="white"
                solo
                v-model="passwordConfirmation"
                :rules="rules.passwordConfirmationRules"
                label="변경할 비밀번호 확인"
                type="password"
                required
              ></v-text-field>
              <v-btn
                rounded
                dark
                large
                :disabled="!valid2"
                color="black"
                class="my-4"
                @click.stop="updatePassword()"
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
                  $router.push({
                    name: 'UserPage',
                    params: { userSeq: userSeq },
                  })
                "
                width="100%"
              >
                뒤로가기
              </v-btn>
            </v-form>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>
    </div>
      </div>

  </v-app>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import axios from "axios";
import ProfilePhoto from "../../components/ProfilePhoto.vue";
export default {
  components: { ProfilePhoto },
  name: "MyPage",
  data: () => ({
    step: 1,
    valid: true,
    valid2: true,
    image: null,
    previewImage: undefined,
    password: null,
    passwordConfirmation: null,
    userInfo: {},
    rules: {
      passwordRules: [
        (v) => !!v || " 비밀번호를 입력해주세요.",
        (v) =>
          !(v && v.length >= 20) || "패스워드는 20자 이상 입력할 수 없습니다.",
      ],
      passwordConfirmationRules: [
        (v) =>
          v === this.password || "패스워드가 일치하지 않습니다.",
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
  created() {
    this.getProfile();
  },
  methods: {
    ...mapMutations("account", ["LOGIN"]),
    selectFile: function (file) {
      this.image = file;
      this.previewImage = URL.createObjectURL(this.image);
    },
    getProfile() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/mypage`,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log(res);
          this.userInfo = res.data.userDto;
        })
        .catch((err) => {
          this.$store.commit(
            "snackbar/SET_SNACKBAR",
            "유저정보 조회에 실패했습니다."
          );
          console.log(err);
          console.log(err.response);
        });
    },
    updateProfile() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "put",
        url: `${process.env.VUE_APP_MCS_URL}/mypage/info`,
        headers: { Authorization: token },
        data: this.userInfo,
      })
        .then((res) => {
          console.log(res);
          this.LOGIN(this.userInfo);
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
    updatePassword() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "put",
        url: `${process.env.VUE_APP_MCS_URL}/mypage/password`,
        headers: { Authorization: token },
        data: {userPassword:this.password},
      })
        .then((res) => {
          console.log(res);
          this.password = null;
        })
        .catch((err) => {
          this.$store.commit(
            "snackbar/SET_SNACKBAR",
            "비밀번호 변경에 실패했습니다.."
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
    ...mapState("account", ["userSeq", "userName", "userProfileUrl"]),
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
