import request from '@/utils/request'

const BASE_API = '/news/comment'

// 查询新闻评论列表
export function listComment (query) {
  return request({
    url: '/news/comment/list',
    method: 'get',
    params: query
  })
}

// 查询新闻评论详细
export function getComment (commentId) {
  return request({
    url: '/news/comment/' + commentId,
    method: 'get'
  })
}

// 新增新闻评论
export function addComment (data) {
  return request({
    url: '/news/comment',
    method: 'post',
    data: data
  })
}

// 修改新闻评论
export function updateComment (data) {
  return request({
    url: '/news/comment',
    method: 'put',
    data: data
  })
}

// 删除新闻评论
export function delComment (commentId) {
  return request({
    url: '/news/comment/' + commentId,
    method: 'delete'
  })
}

// 导出新闻评论
export function exportComment (query) {
  return request({
    url: '/news/comment/export',
    method: 'get',
    params: query
  })
}
