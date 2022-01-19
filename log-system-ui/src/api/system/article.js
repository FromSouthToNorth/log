import request from "@/utils/request";
import { praseStrEmpty } from "@/utils/ruoyi";

export function adminListArticle(query) {
  return request({
    url: '/system/article/list',
    method: 'get',
    params: query
  })
}

export function adminGetArticleInfo(articleId) {
  return request({
    url: '/system/article/' + articleId,
    method: 'get'
  })
}
