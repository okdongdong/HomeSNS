<template>
  <v-app>
    <v-card>
      <v-card-title>2021.12.21</v-card-title>
      <v-card-subtitle>내용 미쳤어</v-card-subtitle>
      <v-container>
        <v-row>
          <v-col v-for="n in 9" :key="n" class="d-flex child-flex" cols="4">
            <v-img
              :src="`https://picsum.photos/200/300`"
              :lazy-src="`https://picsum.photos/200/300`"
              aspect-ratio="1"
              class="grey lighten-2"
              @click.stop="
                $router.push({ name: 'Detail', params: { feedId: feedId } })
              "
            >
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
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
      <v-divider></v-divider>
    </v-card>
    <!-- <v-app class="container">
      <div class="">
        <a v-for="i in feeds.length" :key="i" :name="'tab-' + i">
          <v-card flat>
            <v-card-title class="text-h5">
              {{ feeds[i - 1].feedEventDate }} {{ feeds[i - 1].feedTitle }}
            </v-card-title>
            <v-card-text class="container">
              <div class="row">
                <v-img
                  :src="fileUrl"
                  alt="피드사진"
                  aspect-ratio="1"
                  class="col-4"
                  v-for="fileUrl in feeds[i - 1].feedFileUrls"
                  :key="fileUrl"
                ></v-img>
              </div>
            </v-card-text>
          </v-card>
        </a>
      </div>
    </v-app> -->
  </v-app>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
export default {
  name: "Main",
  data: () => ({}),
  created() {
    this.getMain();
  },
  methods: {
    getMain() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "GET",
        url: `${process.env.VUE_APP_MCS_URL}/main/${this.nowGroup.groupId}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    ...mapState("account", ["nowGroup"]),
  },
};
</script>

<style scoped></style>
