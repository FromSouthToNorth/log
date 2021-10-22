import Vue from "vue";
import Vuex from "vuex";
import user from "@/store/modules/user";
import getters from "@/store/getters";
import permissionMenu from "@/store/modules/permissionMenu";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        user,
        permissionMenu,
    },
    getters,
});
