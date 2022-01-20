import Vue from 'vue'
import Vuetify from 'vuetify/lib' // vuetify
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import 'material-design-icons-iconfont/dist/material-design-icons.css' // google meterial
import './registerServiceWorker'


Vue.config.productionTip = false
Vue.use(Vuetify)

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