import request from "@/utils/request";
import { praseStrEmpty } from "@/utils/ruoyi";

/** 获取文章列表 */
export function adminListArticle(query) {
  return request({
    url: '/system/article/list',
    method: 'get',
    params: query
  })
}

/** 根据文章ID获取文章 */
export function adminGetArticleInfo(articleId) {
  return request({
    url: '/system/article/' + articleId,
    method: 'get'
  })
}

/** 上传文章图片 */
export function adminArticleUploadImg(formData) {
  return request({
    url: '/system/article/image',
    method: 'post',
    data: formData
  })
}

/** 新增文章 */
export function addArticle(data) {
  return request({
    url: '/system/article',
    method: 'post',
    data: data
  })
}

/** 编辑文章 */
export function editArticle(data) {
  return request({
    url: '/system/article',
    method: 'put',
    data: data
  })
}

/** 设置文章置顶 */
export function changArticleTop(articleId, isTop) {
  const data = {
    articleId,
    isTop
  }
  return request({
    url: '/system/article/changeTop',
    method: 'put',
    data: data
  })
}

/** 删除文章 */
export function delArticle(articleIds) {
  return request({
    url: '/system/article/' + articleIds,
    method: 'delete'
  })
}
