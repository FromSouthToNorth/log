// 查询是否开始网页黑白模式
import request from "@/utils/request";

export function blackAndWhite() {
  return request({
    url: '/home/config/blackAndWhite',
    method: 'get'
  })
}