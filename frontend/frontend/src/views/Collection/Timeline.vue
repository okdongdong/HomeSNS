<template>
  <v-app class="container">
    <div class="content-box pe-3 pt-5">
      <div v-if="!feeds.length">
        <div class="justify-center d-flex">
          <h3 class="mt-5">아직 저장된 피드가 없습니다.</h3>
        </div>
      </div>
      <v-timeline v-else dense align-top>
        <div v-for="(feed, idx) in feeds" :key="idx">
          <v-timeline-item>
            <template v-slot:icon>
              <v-avatar>
                <v-img :src="feed.userProfileImageUrl"></v-img>
              </v-avatar>
            </template>
            <v-card
              @click="
                expandFeedId == feed.feedId
                  ? (expandFeedId = -1)
                  : (expandFeedId = feed.feedId)
              "
              ><div class="container d-flex align-center justify around">
                <div class="me-3">
                  <v-img
                    alt="피드사진"
                    aspect-ratio="1"
                    width="100px"
                    :src="feed.fileList[0].fileUrl"
                  ></v-img>
                </div>
                <div>
                  <h2 class="align-center d-flex">
                    <v-icon class="me-2">event</v-icon
                    >{{ feed.feedEventDate.year }}-{{
                      feed.feedEventDate.month
                    }}-{{ feed.feedEventDate.day }}
                  </h2>
                  <h3>
                    <p>
                      {{ feed.feedTitle }}
                    </p>
                  </h3>
                  <v-chip v-if="feed.feedLocation">
                    <v-icon>place</v-icon>{{ feed.feedLocation }}
                  </v-chip>
                  <v-chip
                    v-for="hashtag in feed.hashtagDtoList"
                    :key="hashtag"
                    class="me-1"
                    color="white"
                  >
                    #{{ hashtag }}
                  </v-chip>
                  <v-chip
                    class="me-1"
                    v-for="user in feed.userDtoList"
                    :key="user.userSeq"
                  >
                    <v-icon>person</v-icon>{{ user.userName }}
                  </v-chip>
                </div>
              </div>
            </v-card>
            <!-- 누르면 열리면서 나오는 부분 -->
            <v-expand-transition>
              <div class="content-box" v-show="expandFeedId == feed.feedId">
                <v-container
                  @click="
                    $router.push({
                      name: 'Detail',
                      params: { feedId: feed.feedId },
                    })
                  "
                >
                  <v-row class="pa-3">
                    <v-col
                      class="pa-1"
                      cols="4"
                      v-for="(file, idx) in feed.fileList"
                      :key="idx"
                    >
                      <v-img :src="file.fileUrl" aspect-ratio="1" width="120px">
                        <template v-slot:placeholder>
                          <v-row
                            class="fill-height ma-0"
                            align="center"
                            justify="center"
                          >
                            <v-progress-circular
                              indeterminate
                              color="grey lighten-5"
                            ></v-progress-circular>
                          </v-row>
                        </template>
                      </v-img>
                    </v-col>
                  </v-row>
                </v-container>
              </div>
            </v-expand-transition>
          </v-timeline-item>
        </div>
      </v-timeline>
      <infinite-loading @infinite="getFeed"></infinite-loading>
    </div>
  </v-app>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  name: "Timeline",

  data: () => ({
    expandFeedId: false,
    offset: 0,
    feeds: [],
  }),
  methods: {
    getFeed($state) {
      console.log("일단실행됨");
      const token = localStorage.getItem("jwt");
      axios({
        method: "GET",
        url: `${process.env.VUE_APP_MCS_URL}/feed/timeline`,
        headers: {
          Authorization: token,
        },
        params: {
          groupId: this.nowGroup.groupId,
          offset: this.offset,
          limit: 10,
        },
      })
        .then((res) => {
          console.log("피드받음", res);
          if (res.data.timelineDtoList.length) {
            res.data.timelineDtoList.forEach((feed) => {
              this.feeds.push({ ...feed });
            });
            this.offset += 10;
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log("피드못받음", err);
        });
    },
  },
  computed: {
    ...mapState("account", ["nowGroup"]),
  },
};
</script>

<style scoped>
.content-box {
  /* border: solid 2px black; */
  border-radius: 5px;
  background-color: rgba(255, 255, 255, 0.5);
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329);
}
</style>
