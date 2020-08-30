import request from '@/utils/request'

const BASE_URI = '/news/link'

// 查询友情链接列表
export function listLink (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 查询友情链接详细
export function getLink (linqId) {
  return request({
    url: `${BASE_URI}/${linqId}`,
    method: 'get'
  })
}

// 新增友情链接
export function addLink (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改友情链接
export function updateLink (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除友情链接
export function delLink (linqId) {
  return request({
    url: `${BASE_URI}/${linqId}`,
    method: 'delete'
  })
}
