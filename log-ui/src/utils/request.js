import axios from "axios";
import store from "@/store"
import cache from "@/plugins/cache";

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: process.env.VUE_APP_BASE_API,
  // 超时
  timeout: 10000
})