import Vue from "vue";
import axios from "axios";
import store from "@/store";
import { VueAxios } from "@/util/axios";
import { Modal, notification } from "ant-design-vue";
import { ACCESS_TOKEN } from "@/store/mutation-types";

const errorCode = {
    '401': '认证失败，无法访问系统资源',
    '403': '当前操作没有权限',
    '404': '访问资源不存在',
    'default': '系统未知错误，请反馈给管理员'
}

const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API,
    timeout: 90000
})

service.interceptors.request.use(config => {
    const token = Vue.ls.get(ACCESS_TOKEN);
    if (token) {
        config.headers["Authorization"] = token;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
})

service.interceptors.response.use((response) => {
    const code = response.data.code || 200;
    const message = errorCode[code] || response.data.msg || errorCode["default"];
    if (code === 401) {
        Modal.error({
            title: "登录已过期",
            content: "很抱歉，登录已过期，请重新登录",
            okText: "重新登录",
            mask: false,
            onOk: () => {
                store.dispatch("Logout").then(() => {
                    Vue.ls.remove(ACCESS_TOKEN);
                    location.href = "/index";
                })
            }
        })
    }
    else if (code === 500) {
        notification.error({message: "系统提示", description: message, duration: 5});
    }
    else if (code !== 200) {
        notification.error({message: "系统提示", description: message, duration: 5});
        return Promise.reject("error");
    }
    else {
        return response.data;
    }
}, error => {
    console.log("error" + error);
    let { message } = error;
    if (message === "Network Error") {
        message = "后端接口连接异常";
    }
    else if (message.includes("timeout")) {
        message = "系统接口请求超时";
    }
    else if (message.includes("Request failed with status code")) {
        message = "系统接口" + message.substr(message.length - 3) + "异常";
    }
    notification.error({message: "系统提示",description: message, duration: 5 * 1000})
    return Promise.reject(error);
});

const installer = {
    vm: {},
    install(Vue, router = {}) {
        Vue.use(VueAxios, router, service);
    }
}

export {
    installer as VueAxios,
    service as axios
}

