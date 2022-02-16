<template>
  <v-app class="container">
    <!-- 그룹 프로필사진 수정 팝업 -->
    <v-dialog v-model="dialog" max-width="400px">
      <v-card class="rounded-xl pa-3">
        <div class="justify-center d-flex align-center">
          <v-card-title>그룹 프로필사진 변경</v-card-title>
        </div>
        <v-form
          class="form-data my-5"
          ref="form"
          v-model="valid"
          lazy-validation
        >
          <div class="justify-center d-flex">
            <v-avatar size="160px" elevation="12" color="#846543">
                <v-img
                  v-if="image" 
                  :src="previewImage"
                  size="160"
                  aspect-ratio="1"
                  style="overflow: hidden; object-fit: cover"
                  alt=""
                ></v-img>
                <v-img
                  v-else
                  :src="`https://i6e205.p.ssafy.io/img/emptyImg.png`"
                  size="160"
                  aspect-ratio="1"
                  style="overflow: hidden; object-fit: cover"
                  alt=""
                ></v-img>
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

    <!-- 프로필 페이지 -->
    <div class="container d-flex justify-center content-box">
      <v-chip
        color="rgba(255, 255, 255, 0.6)"
        style="position: absolute; left: 1rem"
        ><h3>그룹ID : {{ nowGroup.groupId }}</h3></v-chip
      >
      <v-btn
        class="px-2"
        color="rgba(255, 255, 255, 0.5)"
        absolute
        right
        v-if="nowManager"
        @click.stop="$router.push({ name: 'Management' })"
        >그룹관리<v-icon>edit</v-icon>
      </v-btn>
      <div class="mt-3">
        <p
          style="position: relative"
          class="ma-0 mt-5"
          @click.stop="dialog = true"
        >
          <v-icon
            class="pa-2"
            @click.stop="dialog = true"
            v-if="nowManager"
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
          <v-img
            aspect-ratio="1"
            :src="`https://i6e205.p.ssafy.io/${nowGroup.groupProfileImageUrl}`"
            alt="그룹 사진입니다."
            class="group-img"
            width="150"
          ></v-img>
        </p>
        <h2 class="text-center mt-2">{{ nowGroup.groupName }}</h2>
      </div>
    </div>

    <div class="content-box my-3">
      <h2 class="text-center">그룹원</h2>
      <div class="justify-space-around d-flex flex-wrap">
        <v-row class="px-5">
          <v-col cols="4" v-for="member in members" :key="member.userSeq">
            <ProfilePhoto
              class="ma-3"
              :name="member.userName"
              :size="80"
              :imgUrl="member.userProfileImageUrl"
              :userSeq="member.userSeq"
            />
          </v-col>
        </v-row>
      </div>
    </div>
  </v-app>
</template>

<script>
import axios from "axios";
import ProfilePhoto from "../../components/ProfilePhoto.vue";
import { mapState } from "vuex";

export default {
  components: { ProfilePhoto },
  name: "GroupProfile",
  data: () => ({
    dialog: false,
    valid: null,
    image : null,
    members: [],
    nowManager: false,
  }),
  created() {
    this.getMembers();
  },
  methods: {
    selectFile: function (file) {
      this.image = file;
      this.previewImage = URL.createObjectURL(this.image);
    },
    updateProfileImage() {
      const token = localStorage.getItem("jwt");
      let data = new FormData();
      data.append("profileImage", this.image);
      data.append("groupId", this.nowGroup.groupId);
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/group/profileImage`,
        headers: {
          Authorization: token,
          "Content-Type": "multipart/form-data",
        },
        data: data,
      })
        .then((res) => {
          console.log(res);
          console.log(res.data);
          this.getProfile(this.userSeq);
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
    getMembers() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/group/member/${this.nowGroup.groupId}`,
        headers: { Authorization: token },
        // params: params,
      })
        .then((res) => {
          console.log('그룹원들')
          console.log(res);
          this.members = res.data.userDtoList;
          for (let i = 0; i < this.members.length; i++) {
            if (this.members[i].userSeq == this.userSeq) {
              if (
                this.members[i].managerYn == "Y" ||
                this.members[i].managerYn == "y"
              ) {
                this.nowManager = true;
              }
              break;
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    ...mapState("account", ["nowGroup", 'userSeq']),
  },
};
</script>

<style scoped>
.content-box {
  border-radius: 5px;
  background-color: rgba(255, 255, 255, 0.6);
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329);
}

.group-img {
  object-fit: cover;
  border: 4px solid white;
  border-radius: 20%;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.329);
}
</style>
