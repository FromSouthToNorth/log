import Vue from "vue";
import Router from "vue-router";

Vue.use(Router)

export const constantRoutes = [
  {
    path: '/',
    component: (resolve) => require(["@/views/Home"], resolve),
    hidden: true
  },
  {
    path: '/tag',
    component: (resolve) => require(["@/views/tag/Tag"], resolve),
    hidden: true
  },
  {
    path: '/article/:articleId(\\w+)',
    component: (resolve) => require(["@/views/article"], resolve),
    hidden: true
  },
  {
    path: '*',
    redirect: '/404',
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(["@/views/exception/404"], resolve)
  }
]

export default new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})