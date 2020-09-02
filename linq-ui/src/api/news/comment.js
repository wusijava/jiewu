import request from '@/utils/request'

const BASE_API = '/news/comment'

// 查询新闻评论列表
export function listComment(page, size, query) {
  return request({
    url: `${BASE_API}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 查询新闻评论详细
export function getComment(commentId) {
  return request({
    url: `${BASE_API}/${commentId}`,
    method: 'get'
  })
}

// 新增新闻评论
export function addComment(data) {
  return request({
    url:  `${BASE_API}`,
    method: 'post',
    data: data
  })
}

// 修改新闻评论
export function updateComment(data) {
  return request({
    url: `${BASE_API}`,
    method: 'put',
    data: data
  })
}

// 删除新闻评论
export function delComment(commentId) {
  return request({
    url: `${BASE_API}/${commentId}`,
    method: 'delete'
  })
}
