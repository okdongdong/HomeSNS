<template>
  <v-card class="mx-auto overflow-hidden">

    <v-app-bar color="deep-purple accent-4" dark prominent>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-filter</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" absolute right temporary>
      <v-list nav dense>
        <v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
          <v-list-item>
            <v-list-item-title>내 정보</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>그룹원보기</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>그룹 목록</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>투표/미니게임</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>그룹관리</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
  name: "FrontendNavbar",

  data: () => ({
      drawer: false,
      group: null,
    }),

    watch: {
      group () {
        this.drawer = false
      },
    },

  mounted() {},

  methods: {},
};
</script>

<style></style>
