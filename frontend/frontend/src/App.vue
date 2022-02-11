<template>
  <v-app id="app">
    <div class="d-flex justify-center">
      <div class="curtain"></div>

      <!-- 상단 내비바 높이맞추기 위한 태그 -->
      <v-main style="max-width: 450px; width: 100%">
        <top-navbar v-if="noneNav"></top-navbar>
        <div class="base-background2">
          <div
            v-if="noneNav"
            style="
              height: 80px;
              width: 100%;
              background-color: rgba(255, 255, 255, 0.5);
            "
          ></div>

          <router-view />
          <!-- 하단 내비바 높이맞추기 위한 태그 -->
          <div
            v-if="noneNav"
            style="
              height: 80px;
              width: 100%;
              background-color: rgba(255, 255, 255, 0.5);
            "
          ></div>
        </div>
        <bottom-navbar v-if="noneNav"></bottom-navbar>
      </v-main>
      <div class="curtain"></div>
    </div>
  </v-app>
</template>

<script>
import BottomNavbar from "./components/Navbar/BottomNavbar.vue";
import TopNavbar from "./components/Navbar/TopNavbar.vue";
export default {
  name: "App",
  components: {
    BottomNavbar,
    TopNavbar,
  },
  data: () => ({
    noneNavPages: [
      "SignUp",
      "Login",
      "FindId",
      "FindPassword",
      "Select",
      "GroupCreate",
      "GroupEnter",
    ],
  }),
  computed: {
    noneNav: function () {
      if (this.noneNavPages.includes(this.$router.app._route.name)) {
        return false;
      }
      return true;
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap");

#app {
  font-family: "Gowun Dodum", sans-serif;
  background-color: rgba(255, 255, 255, 0.5);
}
@-webkit-keyframes AnimationName {
  0% {
    background-position: 0% 0%;
  }
  50% {
    background-position: 100% 100%;
  }
  100% {
    background-position: 0% 0%;
  }
}
@-moz-keyframes AnimationName {
  0% {
    background-position: 0% 0%;
  }
  50% {
    background-position: 100% 100%;
  }
  100% {
    background-position: 0% 0%;
  }
}
@-o-keyframes AnimationName {
  0% {
    background-position: 0% 0%;
  }
  50% {
    background-position: 100% 100%;
  }
  100% {
    background-position: 0% 0%;
  }
}
@keyframes AnimationName {
  0% {
    background-position: 0% 0%;
  }
  50% {
    background-position: 100% 100%;
  }
  100% {
    background-position: 0% 0%;
  }
}
.base-background2 {
  color: #fff;
  background: linear-gradient(
    -45deg,
    #7173c9,
    #df94c2,
    #70558e,
    #8398d1,
    #f2bdff,
    #7173c9,
    #70558e,
    #ce94df,
    #8398d1,
    #7173c9,
    #70558e,
    #df94c2
  );
  background-size: 1600% 1600%;
  animation: AnimationName 60s linear infinite;
  animation-fill-mode: forwards;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  top: 35%;
  text-align: center;
}

.curtain {
  width: calc((100vw - 450px) / 2);
  background-color: rgb(50, 52, 121);
  z-index: 99;
}
</style>
