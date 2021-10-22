import {login, getUserInfo, logout} from "@/api/login";
import {ACCESS_TOKEN} from "@/store/mutation-types";
import Vue from "vue";

const user = {
    state: {
        token: "",
        name: "",
        avatar: "",
        roles: [],
        permissions: []
    },

    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token;
        },
        SET_NAME: (state, name) => {
            state.name = name;
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar;
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles;
        },
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions;
        }
    },

    actions: {
        /** 登录 */
        Login({ commit }, userInfo) {
            const username = userInfo.username.trim();
            const password = userInfo.password;
            const code = userInfo.code;
            const uuid = userInfo.uuid;
            return new Promise((resolve, reject) => {
                login(username, password, code, uuid).then(result => {
                    Vue.ls.set(ACCESS_TOKEN, result.token);
                    commit("SET_TOKEN", result.token);
                    resolve();
                })
                .catch(error => {
                    reject(error);
                })
            })
        },

        /** 获取用户信息 */
        GetUserInfo({commit, state}) {
            return new Promise((resolve, reject) => {
                getUserInfo().then(result => {
                    let user = result.user;
                    console.log("获取用户信息 --> ", result);
                    if (result.roles && result.roles.length > 0) {
                        commit("SET_ROLES", result.roles);
                        commit('SET_PERMISSIONS', result.permissions);
                    }
                    else {
                        commit("SET_ROLES", ["ROLE_DEFAULT"]);
                    }
                    commit("SET_NAME", user.userName);
                    resolve(result);
                })
                .catch(error => {
                    reject(error);
                })
            })
        },

        /** 注销登录 */
        Logout({ commit, state }) {
            return new Promise((resolve, reject) => [
                logout(state.token).then(() => {
                    commit("SET_TOKEN", "");
                    commit("SET_ROLES", []);
                    commit("SET_PERMISSIONS", []);
                    Vue.ls.remove(ACCESS_TOKEN);
                    resolve();
                })
                    .catch(error => {
                    reject(error);
                })
            ])
        }
    }

}

export default user;