<template>
  <v-app class="container">
    <!-- 그룹정보 변경 -->
    <div class="container justify-center content-box">
      <v-chip
        color="rgba(255, 255, 255, 0.6)"
        style="position: absolute; left: 1rem"
        ><h3>그룹ID : {{ nowGroup.groupId }}</h3></v-chip
      >
      <h2 class="text-center">그룹관리</h2>
      <div class="mt-3 d-flex justify-center">
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
            :src="nowGroup.groupProfileImageUrl"
            alt="그룹 사진입니다."
            class="group-img"
            width="150"
          ></v-img>
        </p>
      </div>
      <h2 class="text-center mt-2">{{ nowGroup.groupName }}</h2>

      <v-form
        class="form-data container"
        ref="form"
        v-model="valid"
        lazy-validation
      >
        <v-text-field
          clearable
          background-color="white"
          :counter="20"
          maxlength="20"
          :rules="rules.groupNameRules"
          solo
          v-model="groupName"
          label="그룹 이름"
          required
        ></v-text-field>
        <!--  비밀번호 -->
        <v-text-field
          clearable
          solo
          background-color="white"
          :rules="rules.passwordRules"
          v-model="password"
          maxlength="20"
          label="비밀번호"
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
          label="비밀번호 확인"
          type="password"
          required
        ></v-text-field>
        <div class="d-flex justify-space-around pa-3">
          <v-btn
            class="d-flex justify-space-around"
            @click.stop="$router.go(-1)"
            ><v-icon>arrow_back</v-icon>뒤로가기
          </v-btn>
          <v-btn
          :disabled="!nowManager"
            color="rgba(98, 101, 232)"
            dark
            class="d-flex justify-space-around"
            >그룹수정 <v-icon>edit</v-icon></v-btn
          >
        </div>
      </v-form>
    </div>

    <div class="content-box my-3">
      <h2 class="text-center mb-5 mt-3">그룹원 관리</h2>
      <v-row class="mx-5 mb-5">
        <v-col
          cols="6"
          class="align-center d-flex justify-space-around py-1 m-1"
          v-for="member in members"
          :key="member.userSeq"
        >
          <div class="content-box" style="width: 100%">
            <div>
              <ProfilePhoto
                class="ma-3"
                :name="member.userName"
                :size="64"
                :imgUrl="member.userProfileImageUrl"
                :userSeq="member.userSeq"
              />
            </div>
            <div class="px-3 pb-3">
              <v-btn
                v-if="member.userSeq == nowGroup.groupLeaderSeq"
                class="mb-3 d-flex justify-space-around"
                block
                :disabled="nowGroup.groupLeaderSeq != userSeq"
                color="rgba(255, 247, 137)"
                >그룹리더<v-icon>family_restroom</v-icon></v-btn
              >
              <v-btn
                v-else
                class="mb-3 d-flex justify-space-around"
                block
                :disabled="nowGroup.groupLeaderSeq != userSeq"
                :color="
                  member.managerYn == 'Y' || member.managerYn == 'y'
                    ? 'rgba(98, 101, 232)'
                    : 'rgba(255, 255, 255, 0.5)'
                "
                :dark="(member.managerYn == 'Y' || member.managerYn == 'y') &&  nowGroup.groupLeaderSeq == userSeq"
                @click.stop="
                  member.managerYn == 'Y' || member.managerYn == 'y'
                    ? deleteManager(member)
                    : setManager(member)
                "
                >매니저권한<v-icon
                  v-if="member.managerYn == 'Y' || member.managerYn == 'y'"
                  >check_circle_outline</v-icon
                >
                <v-icon v-else>radio_button_unchecked</v-icon></v-btn
              >
              <v-btn color="red" :dark="nowManager &&!(member.userSeq == nowGroup.groupLeaderSeq)" block class="d-flex justify-space-around" :disabled="!nowManager ||member.userSeq == nowGroup.groupLeaderSeq"
               @click.stop="deleteMember(member)" >내보내기 <v-icon>do_not_disturb_on</v-icon></v-btn
              >
            </div>
          </div>
        </v-col>
      </v-row>
    </div>
    <div class="content-box d-flex justify-space-around pa-3">
      <v-btn class="d-flex justify-space-around" @click.stop="$router.go(-1)"
        ><v-icon>arrow_back</v-icon>뒤로가기
      </v-btn>
      <v-btn
        :disabled="members.length > 1"
        :dark="members.length <= 1"
        color="red"
        class="d-flex justify-space-around"
        >그룹삭제 <v-icon>delete_forever</v-icon></v-btn
      >
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
    members: [],
    nowManager: false,
    valid: true,
    groupName: null,
    image: null,
    previewImage: undefined,
    password: null,
    passwordConfirmation: null,
    rules: {
      groupNameRules: [
        (v) => !!v || "그룹명을 입력해주세요.",
        (v) =>
          !(v && v.length >= 20) || "그룹명은 20자 이상 입력할 수 없습니다.",
      ],
      passwordRules: [
        (v) => !!v || " 비밀번호를 입력해주세요.",
        (v) =>
          !(v && v.length >= 20) || "패스워드는 20자 이상 입력할 수 없습니다.",
      ],
      passwordConfirmationRules: [
        (v) => v === this.password || "패스워드가 일치하지 않습니다.",
      ],
    },
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
      let data = new FormData();
      data.append("profileImage", this.image);
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/mypage/profileImage`,
        headers: {
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
    getMembers() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/group/member/${this.nowGroup.groupId}`,
        headers: { Authorization: token },
        // params: params,
      })
        .then((res) => {
          console.log(res);
          this.members = res.data.userDtoList;
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
    updateGroupProfile() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "put",
        url: `${process.env.VUE_APP_MCS_URL}/group`,
        headers: { Authorization: token },
        data: {
          groupName: this.groupName,
          groupPassword: this.password,
          groupId: this.nowGroup.groupId,
        },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setManager(memberInfo) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "put",
        url: `${process.env.VUE_APP_MCS_URL}/group/memberManagerAuthApprove`,
        headers: { Authorization: token },
        data: {
          groupId: this.nowGroup.groupId,
          userSeq: memberInfo.userSeq,
          userName: memberInfo.userName,
          managerYn: memberInfo.managerYn,
        },
      })
        .then((res) => {
          console.log(res);
          this.getMembers();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteManager(memberInfo) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "put",
        url: `${process.env.VUE_APP_MCS_URL}/group/memberManagerAuthDecline`,
        headers: { Authorization: token },
        data: {
          groupId: this.nowGroup.groupId,
          userSeq: memberInfo.userSeq,
          userName: memberInfo.userName,
          managerYn: memberInfo.managerYn,
        },
      })
        .then((res) => {
          console.log("매니저뺏기 성공");
          console.log(res);
          this.getMembers();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteGroup(memberInfo) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "put",
        url: `${process.env.VUE_APP_MCS_URL}/group/${this.nowGroup.groupId}`,
        headers: { Authorization: token },
        data: {
          groupId: this.nowGroup.groupId,
          userSeq: memberInfo.userSeq,
          userName: memberInfo.userName,
          managerYn: memberInfo.managerYn,
        },
      })
        .then((res) => {
          console.log("매니저뺏기 성공");
          console.log(res);
          this.getMembers();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteMember(memberInfo){
      const token = localStorage.getItem("jwt");
      axios({
        method: "delete",
        url: `${process.env.VUE_APP_MCS_URL}/group/member`,
        headers: { Authorization: token },
        data: {
          groupId: this.nowGroup.groupId,
          userSeq: memberInfo.userSeq,
        },
      })
        .then((res) => {
          console.log("멤버삭제 성공");
          console.log(res);
          this.getMembers();
        })
        .catch((err) => {
          console.log(err);
        });
    }
  },
  computed: {
    ...mapState("account", ["nowGroup", "userSeq"]),
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
