import Vue from 'vue'
import Vuetify from 'vuetify/lib' // vuetify
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import 'material-design-icons-iconfont/dist/material-design-icons.css' // google meterial
import './registerServiceWorker'
import * as VueGoogleMaps from "vue2-google-maps"
import InfiniteLoading from 'vue-infinite-loading';



Vue.config.productionTip = false
Vue.use(Vuetify)
Vue.use(VueGoogleMaps,
  { load: { key: process.env.VUE_APP_GOOGLE_MAP_API_KEY, libraries: "places", region: "KR" } }

);
Vue.use(require('vue-moment'));


// 인피니티 스크롤 기초작업
Vue.use(InfiniteLoading,{ 
  props: {
    spinner: 'spiral',  // 로딩중임을 알리는 아이콘 모양
  },
});


export default new Vuetify({
  icons: {
    iconfont: 'md',
  },
})

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')