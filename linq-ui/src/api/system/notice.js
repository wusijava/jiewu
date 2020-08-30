import request from '@/utils/request'

const BASE_URI = '/system/notice'

// 查询公告列表
export function listNotice (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 查询公告详细
export function getNotice (noticeId) {
  return request({
    url: `${BASE_URI}/${noticeId}`,
    method: 'get'
  })
}

// 新增公告
export function addNotice (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改公告
export function updateNotice (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除公告
export function delNotice (noticeId) {
  return request({
    url: `${BASE_URI}/${noticeId}`,
    method: 'delete'
  })
}
