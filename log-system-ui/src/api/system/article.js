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
  console.log("上传文章图片 --> ", formData);
  return request({
    url: '/system/article/image',
    method: 'post',
    data: formData
  })
}
