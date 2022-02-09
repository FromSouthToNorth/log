import Vue from "vue";
import Router from "vue-router";

Vue.use(Router)

export const constantRoutes = [
  {
    path: '/',
    component: (resolve) => require(["@/views/Home"], resolve),
  },
  {
    path: '/tag',
    component: (resolve) => require(["@/views/tag/Tag"], resolve),
    meta: {
      title: '标签'
    }
  },
  {
    path: '/article/:articleId(\\w+)',
    component: (resolve) => require(["@/views/article"], resolve),
  },
  {
    path: '*',
    redirect: '/404',
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