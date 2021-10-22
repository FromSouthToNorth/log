import {axios} from "@/util/request"

/** 获取验证码 */
export function getCodeImages() {
    return axios({
        url: "/captchaImage",
        method: "get",
        timeout: 20000
    })
}

/**
 * 登录方法
 * @param username
 * @param password
 * @param code
 * @param uuid
 */
export function login(username, password, code, uuid) {
    const data = {
        username,
        password,
        code,
        uuid
    }
    return axios({
        url: "/login",
        method: "post",
        data: data
    })
}

/**
 * 获取用户信息
 */
export function getUserInfo() {
    return axios({
        url: "/getInfo",
        method: "get"
    })
}

/**
 * 退出登录
 */
export function logout() {
    return axios({
        url: "/logout",
        method: "post"
    })
}