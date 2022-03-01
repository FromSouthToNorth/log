import Vue from "vue";
import Router from "vue-router";

Vue.use(Router)

export const constantRoutes = [
  {
    path: '/',
    component: (resolve) => require(["@/views/Home"], resolve),
    meta: {
      title: '首页'
    }
  },
  {
    path: '/tag',
    component: (resolve) => require(["@/views/tag"], resolve),
    meta: {
      title: '标签'
    }
  },
  {
    path: '/aboutMe',
    component: (resolve) => require(["@/views/aboutMe"], resolve),
    meta: {
      title: '关于我'
    }
  },
  {
    path: '/archive',
    component: (resolve) => require(["@/views/archive"], resolve),
    meta: {
      title: '归档'
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