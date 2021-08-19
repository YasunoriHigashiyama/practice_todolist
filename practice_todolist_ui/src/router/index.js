import Vue from 'vue'
import VueRouter from 'vue-router'
import TodoList from "../views/TodoList"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'TodoList',
    component: TodoList
  },
]

const router = new VueRouter({
  mode: 'history',
  routes: routes,
})

export default router
