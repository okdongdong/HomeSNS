<template>
  <div>
    <v-app-bar color="rgb(98,101,232)" dark>
      <v-spacer></v-spacer>
      <v-btn
        rounded
        text
        @click="feedCreate()"
      >추억담기
      </v-btn>

    <v-dialog
      v-model="dialog"
      scrollable
      max-width="400px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
            v-bind="attrs"
            v-on="on"
            icon
          >
            <v-icon>notifications_none</v-icon>
        </v-btn>
      </template>
      <v-card class="rounded-xl">
        <v-card-title>알람</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 500px;">
          <v-container>
              <v-hover>
                <template v-slot:default="{ hover }">
                  <v-card ripple max-width="380px" class="content-box mx-auto pa-3 transition-swing rounded-xl" :class="`elevation-${hover ? 20 : 6}`">
                    Lorem ipsum dolor sit amet, semper quis, sapien id natoque elit. Nostra urna at, magna at neque sed sed ante imperdiet, dolor mauris cursus velit, velit non, sem nec. Volutpat sem ridiculus placerat leo, augue in, duis erat proin condimentum in a eget, sed fermentum sed vestibulum varius ac, vestibulum volutpat orci ut elit eget tortor. Ultrices nascetur nulla gravida ante arcu. Pharetra rhoncus morbi ipsum, nunc tempor debitis, ipsum pellentesque, vitae id quam ut mauris dui tempor, aptent non. Quisque turpis. Phasellus quis lectus luctus orci eget rhoncus. Amet donec vestibulum mattis commodo, nulla aliquet, nibh praesent, elementum nulla. Sit lacus pharetra tempus magna neque pellentesque, nulla vel erat.
                    Justo ex quisque nulla accusamus venenatis, sed quis. N
                  </v-card>
                </template>
              </v-hover>
            </v-container>
            <v-container>
              <v-hover>
                <template v-slot:default="{ hover }">
                  <v-card ripple  max-width="380px" class="mx-auto pa-3 transition-swing rounded-xl" :class="`elevation-${hover ? 20 : 5}`">
                    Lorem ipsum dolor sit amet, semper quis, sapien id natoque elit. Nostra urna at, magna at neque sed sed ante imperdiet, dolor mauris cursus velit, velit non, sem nec. 
                    Justo ex quisque nulla accusamus venenatis, sed quis. N
                  </v-card>
                </template>
              </v-hover>
            </v-container>
            <v-container>
              <v-hover>
                <template v-slot:default="{ hover }">
                  <v-card ripple max-width="380px" class="mx-auto pa-3 transition-swing rounded-xl" :class="`elevation-${hover ? 20 : 5}`">
                    Lorem ipsum dolor sit amet, semper quis, sapien id natoque elit. Nostra urna at, magna at neque sed sed ante imperdiet, 
                  </v-card>
                </template>
              </v-hover>
            </v-container>
            <v-container>
              <v-hover>
                <template v-slot:default="{ hover }">
                  <v-card ripple max-width="380px" class="mx-auto pa-3 transition-swing rounded-xl" :class="`elevation-${hover ? 20 : 5}`">
                    Lorem ipsum dolor sit amet, semper quis, sapien id natoque elit. Nostra urna at, magna at neque sed sed ante imperdiet, 
                  </v-card>
                </template>
              </v-hover>
            </v-container>
            <v-container>
              <v-hover>
                <template v-slot:default="{ hover }">
                  <v-card ripple max-width="380px" class="mx-auto pa-3 transition-swing rounded-xl" :class="`elevation-${hover ? 20 : 5}`">
                    Lorem ipsum dolor sit amet, semper quis, sapien id natoque elit. Nostra urna at, magna at neque sed sed ante imperdiet, 
                  </v-card>
                </template>
              </v-hover>
            </v-container>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
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
          <v-list-item @click.stop="logout()">
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-title>그룹관리</v-list-item-title>
          </v-list-item>
          <hr style="margin: auto;  border-top: 1px solid #f0f0f0" />
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "FrontendNavbar",

  data: () => ({
    dialog: false,
    drawer: false,
    group: null,
  }),

  watch: {
    group() {
      this.drawer = false;
    },
  },

  mounted() {},

  methods: {
    feedCreate: function() {
      this.$router.push({name : 'FeedCreate'});
    }
    ...mapActions(["logout"]),
    feedCreate: function () {
      this.$router.push({ name: "FeedCreate" });
    },
    move(page) {
      this.$router.push({ name: page });
    },
  },
};
</script>

<style scoped>
.drawer-position {
  right: calc((100vw - 480px) / 2);
}

</style>