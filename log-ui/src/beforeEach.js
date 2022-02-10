import router from "@/router";
import NProgress from "nprogress";
import 'nprogress/nprogress.css'

router.beforeEach((to, from, next) => {
  NProgress.start()
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

router.afterEach(() => {
  NProgress.done()
})