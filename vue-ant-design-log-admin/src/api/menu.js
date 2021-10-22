import { axios } from "@/util/request";

/**
 * 获取路由信息
 * @returns {AxiosPromise}
 */
export const getRouters = () => {
    return axios({
        url: "/getRouters",
        method: "get"
    })
}