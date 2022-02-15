<template>
  <v-app class="container">
    <!-- 유저 프로필사진 수정 팝업 -->
    <v-dialog v-model="dialog" max-width="400px">
      <v-card class="rounded-xl pa-3">
        <div class="justify-center d-flex align-center">
          <v-card-title>정말 삭제하시겠습니까?</v-card-title>
        </div>

        <v-form
          class="form-data my-5"
          ref="form"
          v-model="valid"
          lazy-validation
        >
          <div class="justify-center d-flex">
               <v-avatar size="160px" elevation="12" color="#846543">
            <div v-if="image" class="d-flex align-items-center">
          <v-img
            :src="previewImage"
            size=160
            aspect-ratio=1
            style="overflow: hidden; object-fit: cover"
            alt=""
          ></v-img>
              <profile-photo :size="160" :imgUrl="previewImage" :name="userName" />
            </div>
            <div v-else>
               
          <v-img
            :src="`https://i6e205.p.ssafy.io/img/emptyImg.png`"
            size=160
            aspect-ratio=1
            style="overflow: hidden; object-fit: cover"
            alt=""
          ></v-img>
            </div>
      </v-avatar>

          </div>
          <v-file-input
            type="file"
            accept="image/*"
            @change="selectFile"
            class="form-control-file"
            id="profile_path"
            enctype="multipart/form-data"
          ></v-file-input
        ></v-form>
        <v-card-text class="d-flex">
          <v-btn
            class="mx-auto pa-3 transition-swing d-flex align-center"
            @click="updateProfileImage(), (dialog = false)"
            color="rgba(98, 101, 232)"
            width="100"
            dark
          >
            <h3>변경</h3>
          </v-btn>
          <v-btn
            width="100"
            class="mx-auto pa-3 transition-swing d-flex align-center"
            @click="dialog = false"
          >
            <h3>취소</h3>
          </v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>

    <div class="content-box mb-3 pb-5">
      <div class="d-flex justify-center ma-5">
        <p style="position: relative" class="ma-0" @click.stop="dialog = true">
          <v-icon
            class="pa-2"
            @click.stop="dialog = true"
            v-if="mySeq == userSeq"
            style="
              position: absolute;
              z-index: 5;
              right: -15px;
              bottom: -10px;
              background-color: rgba(255, 255, 255, 0.8);
              border-radius: 50%;
            "
            color="black"
            size="32"
            >edit</v-icon
          >
          <ProfilePhoto :size="120" :imgUrl="userProfileImageUrl" />
        </p>
        <v-btn
          color="rgba(255, 255, 255, 0.5)"
          absolute
          right
          v-if="mySeq == userSeq"
          @click.stop="$router.push({ name: 'MyPage' })"
          >정보수정<v-icon>edit</v-icon>
        </v-btn>
      </div>
      <h2 class="text-center">{{ userName }}</h2>
    </div>

    <div class="content-box mb-3 px-5">
      <v-row class="my-2">
        <v-col>
          <v-btn
            :color="
              $router.app._route.name == 'UserPage'
                ? 'rgba(98, 101, 232)'
                : 'rgba(255, 255, 255, 0.5)'
            "
            :dark="$router.app._route.name == 'UserPage'"
            block
            @click.stop="
              $router.app._route.name != 'UserPage'
                ? $router.push({
                    name: 'UserPage',
                    params: { UserSeq: UserSeq },
                  })
                : ''
            "
          >
            게시물
          </v-btn>
        </v-col>
        <v-col>
          <v-btn
            :color="
              $router.app._route.name == 'UserPageScrap'
                ? 'rgba(98, 101, 232)'
                : 'rgba(255, 255, 255, 0.5)'
            "
            :dark="$router.app._route.name == 'UserPageScrap'"
            block
            @click.stop="
              $router.app._route.name != 'UserPageScrap'
                ? $router.push({
                    name: 'UserPageScrap',
                    params: { UserSeq: UserSeq },
                  })
                : ''
            "
          >
            스크랩
          </v-btn>
        </v-col></v-row
      >
    </div>

    <router-view></router-view>
  </v-app>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";
import ProfilePhoto from "../../components/ProfilePhoto.vue";

export default {
  name: "UserPage",
  props: {
    userSeq: Number,
  },
  components: {
    ProfilePhoto,
  },
  data: function () {
    return {
      dialog: false,
      userInfo: {},
      image: null,
      previewImage: undefined,
    };
  },
  created() {
    this.getProfile(this.userSeq);
  },
  methods: {
    ...mapActions("group", ["getProfile"]),
    selectFile: function (file) {
      this.image = file;
      this.previewImage = URL.createObjectURL(this.image);
    },
    updateProfileImage() {
      const token = localStorage.getItem("jwt");
      let data = new FormData();
      data.append("profileImage", this.image);
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/mypage/profileImage`,
        headers: {
          Authorization: token,
          "Content-Type": "multipart/form-data",
        },
        data: data,
      })
        .then((res) => {
          console.log(res);
          console.log(res.data);
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
  },
  computed: {
    ...mapState("account", { mySeq: (state) => state.userSeq }),
    ...mapState("group", ["userProfileImageUrl", "userName"]),
  },
};
</script>

<style scoped>
.content-box {
  border-radius: 5px;
  background-color: rgba(255, 255, 255, 0.6);
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329);
}
</style>
