<template>
  <div>
    <v-card class="rounded-xl justify-center d-flex">
      <v-alert
        v-show="noticeAlarm"
        transition="slide-y-transition"
        class="alert"
        close-icon
        elevation="24"
      >
        <div class="d-flex align-center">
          <ProfilePhoto
            :size="50"
            :imgUrl="recv.userProfileUrl"
            :userSeq="recv.userSeq"
          />
          <div class="mx-5">{{ recv.userName }} 님이 뭔가를 했습니다.</div>
        </div>
      </v-alert>
    </v-card>
    <v-app-bar
      color="rgb(98,101,232)"
      dark
      fixed
      hide-on-scroll
      max-width="450px"
      width="100%"
      class="align-space-center my-bar"
      height="80px"
      style="z-index: 7; left: calc(max(((100% - 450px) / 2), 0px))"
    >
      <img
        height="70"
        width="70"
        src="../../assets/logo2.png"
        alt="로고"
        @click.stop="move('Main')"
      />
      <v-spacer></v-spacer>
      <v-btn rounded text @click.stop="move('FeedCreate')">추억담기 </v-btn>

      <v-dialog v-model="dialog" scrollable max-width="400px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn v-bind="attrs" v-on="on" icon>
            <v-icon>notifications_none</v-icon>
          </v-btn>
        </template>
        <v-card class="rounded-xl">
          <v-card-title>
            <span>알람</span>
            <v-spacer></v-spacer>
            <v-btn text class="body-1" @click.stop="noticeReadAll()">
              모두읽기
            </v-btn>
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text style="height: 500px">
            <v-container
              v-for="(notice, $idx) in noticeList"
              :key="$idx"
              @click="dialog = false"
            >
              <v-hover>
                <template v-slot:default="{ hover }">
                  <v-card
                    ripple
                    max-width="380px"
                    class="content-box mx-auto pa-3 transition-swing rounded-xl align-center d-flex"
                    :class="`elevation-${hover ? 20 : 6}`"
                    :disabled="notice.noticeReadYn == 'y'"
                    :color="notice.noticeReadYn == 'y' ? '#DDDDDD' : ''"
                    @click.stop="
                      noticeRead(
                        notice.noticeId,
                        notice.noticeContentId,
                        notice.noticeType
                      ),
                        (notice.noticeReadYn = 'y'),
                        (dialog = false)
                    "
                  >
                    <ProfilePhoto
                      :size="50"
                      :imgUrl="notice.userProfileImageUrl"
                      :userSeq="notice.userSeq"
                      @clicked="dialog = false"
                    />
                    <div class="mx-3">
                      <div>
                        {{ notice.userName }} 님이 {{ notice.noticeType }}을
                        작성했슈
                      </div>
                      <div>
                        {{ notice.noticeContentTitle }}
                      </div>
                      <div>
                        {{ notice.noticeContentContent }}
                      </div>
                      <div>{{ notice.noticeTime }}</div>
                    </div>
                  </v-card>
                </template>
              </v-hover>
            </v-container>
            <infinite-loading @infinite="getNoticeList"></infinite-loading>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn color="blue darken-1" text @click="dialog = false">
              닫기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    </v-app-bar>

    <v-navigation-drawer
      v-model="drawer"
      fixed
      right
      temporary
      class="drawer-position"
    >
      <v-list nav dense>
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item class="my-5">
            <ProfilePhoto :size="100" :imgUrl="userImgUrl" :userSeq="userSeq" />
            <h1>{{ userName }}</h1>
          </v-list-item>
          <v-list-item
            @click.stop="
              $router.push({
                name: 'UserPage',
                params: { userSeq: userSeq },
              })
            "
          >
            <v-list-item-title class="d-flex align-center">
              <v-icon class="mx-3">account_circle</v-icon>
              <h1>내 정보</h1>
            </v-list-item-title>
          </v-list-item>
          <v-list-item @click.stop="move('GroupProfile')">
            <v-list-item-title class="d-flex align-center">
              <v-icon class="mx-3">groups</v-icon>
              <h1>그룹 프로필</h1>
            </v-list-item-title>
          </v-list-item>
          <v-list-item @click.stop="move('Select')">
            <v-list-item-title class="d-flex align-center">
              <v-icon class="mx-3">switch_account</v-icon>
              <h1>그룹 목록</h1>
            </v-list-item-title>
          </v-list-item>
          <v-list-item @click.stop="move('EntFeedList')">
            <v-list-item-title class="d-flex align-center">
              <v-icon class="mx-3">extension</v-icon>
              <h1>투표/미니게임</h1>
            </v-list-item-title>
          </v-list-item>

          <v-list-item @click.stop="move('Management')">
            <v-list-item-title class="d-flex align-center">
              <v-icon class="mx-3">manage_accounts</v-icon>
              <h1>그룹관리</h1>
            </v-list-item-title>
          </v-list-item>
          <hr style="margin: auto; border-top: 1px solid #f0f0f0" />
          <v-list-item @click.stop="logout()">
            <v-list-item-title class="d-flex align-center">
              <v-icon class="mx-3">logout</v-icon>
              <h1>로그아웃</h1>
            </v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
import axios from "axios";
import { mapState, mapActions } from "vuex";
import ProfilePhoto from "../ProfilePhoto.vue";
export default {
  name: "FrontendNavbar",

  data: () => ({
    dialog: false,
    drawer: false,
    group: null,
    unreadNoticeCount: 0,
    noticeList: [],
    start: 0,
  }),
  components: {
    ProfilePhoto,
  },
  watch: {
    group() {
      this.drawer = false;
    },
  },

  mounted() {
    this.connect();
  },
  beforeDestroy() {
    this.disconnect();
  },

  methods: {
    ...mapActions("account", ["logout"]),
    ...mapActions("notice", ["connect", "disconnect", "send"]),
    noticeReadAll() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "PUT",
        url: `${process.env.VUE_APP_MCS_URL}/noticelist/${this.nowGroup.groupId}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log(res);
          this.noticeList = [];
          this.start = 0;
          const token = localStorage.getItem("jwt");
          axios({
            method: "get",
            url: `${process.env.VUE_APP_MCS_URL}/noticelist`,
            headers: { Authorization: token },
            params: {
              groupId: this.nowGroup.groupId,
              start: this.start,
            },
          })
            .then((res) => {
              console.log(res);
              if (res.data.noticeResultDtoList.length) {
                // 20개씩 인피니티스크롤로 구현
                this.start += 20;
                this.noticeList.push(...res.data.noticeResultDtoList);
                this.readNoticeCount = res.data.count;
              }
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    noticeRead(noticeId, noticeContentId, noticeType) {
      if (
        noticeType == "feed" ||
        noticeType == "comment" ||
        noticeType == "emotion" ||
        noticeType == "share"
      ) {
        this.$router.push({
          name: "Detail",
          params: { feedId: noticeContentId },
        });
      } else if (noticeType == "vote") {
        this.$router.push({ name: "EntFeedList" });
      } else if (noticeType == "ghostleg") {
        this.$router.push({ name: "EntFeedList" });
      }

      const token = localStorage.getItem("jwt");
      axios({
        method: "PUT",
        url: `${process.env.VUE_APP_MCS_URL}/noticeread/${noticeId}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    feedCreate: function () {
      this.$router.push({ name: "FeedCreate" });
    },
    move(page) {
      this.$router.push({ name: page });
    },

    getNoticeList($state) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/noticelist`,
        headers: { Authorization: token },
        params: {
          groupId: this.nowGroup.groupId,
          start: this.start,
        },
      })
        .then((res) => {
          console.log(res);
          if (res.data.noticeResultDtoList.length) {
            // 20개씩 인피니티스크롤로 구현
            this.start += 20;
            this.noticeList.push(...res.data.noticeResultDtoList);
            this.readNoticeCount = res.data.count;
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    ...mapState("account", ["userSeq", "userImgUrl", "userName", "nowGroup"]),
    ...mapState("notice", ["noticeAlarm", "recv"]),
    nowMobile() {
      if (window.innerWidth < 450) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
.drawer-position {
  right: calc((100vw - 480px) / 2);
}
.my-bar {
  left: calc((100vw - 450px) / 2);
}
.alert {
  position: fixed;
  top: 10px;
  z-index: 20;
}
</style>
