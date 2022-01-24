import request from "@/utils/request";

/** 获取文章标签列表 */
export function adminListArticleTag(query) {
  return request({
    url: '/system/tag/list',
    method: 'get',
    params: query
  })
}

/** 根据文章编号查询类型信息 */
export function getArticleTagInfo(typeId) {
  return request({
    url: '/system/tag/' + typeId,
    method: 'get'
  })
}

/** 新增文章类型 */
export function addArticleTag(data) {
  return request({
    url: '/system/tag',
    method: 'post',
    data: data
  })
}

/** 修改文章类型 */
export function updateArticleTag(data) {
  return request({
    url: '/system/tag',
    method: 'put',
    data: data
  })
}

/** 删除文章类型 */
export function delArticleTag(typeIds) {
  return request({
    url: '/system/tag/' + typeIds,
    method: 'delete'
  })
}
