import router from "@/router";
import NProgress from "nprogress";
import 'nprogress/nprogress.css'
import store from './store'

router.beforeEach((to, from, next) => {
  NProgress.start()
  to.meta.title && store.dispatch('settings/setTitle', to.meta.title)
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

router.afterEach(() => {
  NProgress.done()
})