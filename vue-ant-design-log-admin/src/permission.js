import Vue from "vue";
import router from "@/router";
import store from "@/store";
import {ACCESS_TOKEN} from "@/store/mutation-types";

const whiteList = ["/sign-in", "/sign-up"]
router.beforeEach((to, from, next) => {
    if (Vue.ls.get(ACCESS_TOKEN)) {
        if (to.path === "/sign-in") {
            next({ path: "/" })
        }
        else {
            if (store.getters.roles.length === 0) {
                // 判断当前用户是否已拉取完user_info信息
                store.dispatch("GetUserInfo").then(() => {
                    store.dispatch("GenerateRoutes").then(accessRoutes => {
                        router.addRoutes(accessRoutes);
                        next( {...to, replace: true});
                    })
                }).catch(err => {
                    // store.dispatch("Logout").then(() => {
                    //     next({path: "/"});
                    // })
                })
            }
            else {
                next();
            }
        }
    }
    else {
        if (whiteList.indexOf(to.path) !== -1) {
            next();
        }
        else {
            next("/sign-in")
        }
    }
})

router.afterEach(() => {
})