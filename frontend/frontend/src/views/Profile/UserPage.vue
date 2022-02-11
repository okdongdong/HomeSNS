<template>
  <v-app class="container">
    <div class="content-box mb-3 pb-5">
      <div class="d-flex justify-center ma-5">
        <ProfilePhoto :size="120" :imgUrl="userProfileImageUrl" />
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
          <div></div>
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
      userInfo: {
        imgUrl:
          "https://image.ajunews.com/content/image/2016/12/26/20161226142046950664.jpg",
        name: "김영철",
      },
    };
  },
  created() {
    this.getProfile(this.userSeq);
  },
  methods: {
    ...mapActions("group", ["getProfile"]),
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
}
</style>
