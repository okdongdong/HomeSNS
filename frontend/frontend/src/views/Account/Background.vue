<template>
  <div class="base-background" >
    <transition name="fade">
      <router-view style="width:100%; height:100%" ></router-view>
    </transition>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
/* background: linear-gradient(137deg, #fff6b7, #f77c99, #6578f2, #c2ffd8);
background-size: 800% 800%;
-webkit-animation: AnimationName 8s ease infinite;
-moz-animation: AnimationName 8s ease infinite;
-o-animation: AnimationName 8s ease infinite;
animation: AnimationName 2s ease infinite; */
@-webkit-keyframes AnimationName {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
@-moz-keyframes AnimationName {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
@-o-keyframes AnimationName {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
@keyframes AnimationName {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
.base-background {
  color: #fff;
  background: linear-gradient(
    -45deg,
    #1d94c0,
    #ab67d8,
    #6265e8,
    #1d94c0,
    #ab67d8,
    #23d5ab
  );
  background-size: 400% 400%;
  animation: AnimationName 16s ease infinite;
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  top: 35%;
  text-align: center;
}

/* .my-background   { */
/* background-color: rgb(98, 101, 232); */
/* background-image: linear-gradient(-225deg, #2CD8D5 0%, #C5C1FF 56%, #FFBAC3 100%); */
/* background: linear-gradient(-225deg, #7873f5 0%, #ec77ab 100%); */
/* } */

.fade-enter-active {
  transition: opacity;
  transition-duration: .75s;
  transition-delay: .75s;
}

.fade-leave-active {
  transition: opacity;
  transition-duration: .75s;
}
.fade-enter, .fade-leave-to {
  position: absolute;
  opacity: 0;
}
</style>
