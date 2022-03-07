import request from '@/utils/request'

/** 获取置顶文章 */
export function topArticle() {
  return request({
    url: '/home/article/top',
    method: 'get'
  })
}

/** 获取文章列表 */
export function articleList(query) {
  return request({
    url: '/home/article',
    method: 'get',
    params: query
  })
}

/** 根据文章编号查询文章 */
export function getArticleIngo(articleId) {
  return request({
    url: '/home/article/' + articleId,
    method: 'get'
  })
}

/** 搜索文章 */
export function searchArticles(keywords) {
  return request({
    url: '/home/article/search/' + keywords,
    method: 'get'
  })
}

/** 文章归档 */
export function articleArchive() {
  return request({
    url: '/home/article/archive',
    method: 'get'
  })
}