import request from "@/utils/request";

/** 获取文章类型列表 */
export function adminListArticleType(query) {
  return request({
    url: '/system/type/list',
    method: 'get',
    params: query
  })
}

/** 根据文章编号查询类型信息 */
export function getArticleTypeInfo(typeId) {
  return request({
    url: '/system/type/' + typeId,
    method: 'get'
  })
}

/** 新增文章类型 */
export function addArticleType(data) {
  return request({
    url: '/system/type',
    method: 'post',
    data: data
  })
}

/** 修改文章类型 */
export function updateArticleType(data) {
  return request({
    url: '/system/type',
    method: 'put',
    data: data
  })
}

/** 删除文章类型 */
export function delArticleType(typeIds) {
  return request({
    url: '/system/type/' + typeIds,
    method: 'delete'
  })
}
