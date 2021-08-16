import Vue from 'vue'
import Vuex from 'vuex'
import alert from './module/alert'

Vue.use(Vuex)

export default new Vuex.Store({
    strict: process.env.NODE_ENV !== 'production',
    modules: {
        alert,
    },
    state: {

    },
    mutations: {

    },
    actions: {

    }
})