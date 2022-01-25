import request from '@/utils/request'

/** 获取置顶文章 */
export function topArticle() {
  return request({
    url: '/home/article/top',
    method: 'get'
  })
}

/** 获取文章列表 */
export function articleList() {
  return request({
    url: '/home/article',
    method: 'get'
  })
}