import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import VueAxios from 'vue-axios'
import mock from "./mock/mock"
import store from './store/store'

Vue.config.productionTip = false
Vue.prototype.$axios = axios
/* eslint-disable*/
export let client = axios.create({
  baseURL: "",
})

// モック起動
// if (JSON.parse(process.env.VUE_APP_USE_MOCK)) {
client = axios.create({
  baseURL: "",
})
// console.log("run mock")
mock.run(client)
// }


// interceptors
axios.interceptors.request.use(request => {
  store_setAlert({
    action: false,
    message: null,
    detail: null,
  }).then(function (change) {
  }).catch(function (error) {
  });

  return request
})


Vue.use(VueAxios, axios);
new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')

function store_setAlert({ action: action, message: message, detail: detail }) {
  return new Promise(function (resolve, reject) {
    store.dispatch("alert/setAlert", {
      action: action,
      message: message,
      detail: detail,
    });
    resolve();
  })
}