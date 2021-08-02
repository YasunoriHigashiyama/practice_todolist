import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import VueAxios from 'vue-axios'
import mock from "./mock/mock"


Vue.config.productionTip = false
Vue.prototype.$axios = axios

export let client = axios.create({
  baseURL: "",
})

// モック起動
if (JSON.parse(process.env.VUE_APP_USE_MOCK)) {
  client = axios.create({
    baseURL: "",
  })
  console.log("run mock")
  mock.run(client)
}
Vue.use(VueAxios, axios);
new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
