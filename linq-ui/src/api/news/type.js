import request from '@/utils/request'

const BASE_URI = '/news/type'


// 新闻类别下拉列表
export function optionSelect () {
  return request({
    url: `${BASE_URI}/list`,
    method: 'get'
  })
}

// 分页查询新闻类型列表
export function listType (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 查询新闻类型详细
export function getType (newsTypeId) {
  return request({
    url: `${BASE_URI}/${newsTypeId}`,
    method: 'get'
  })
}

// 新增新闻类型
export function addType (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改新闻类型
export function updateType (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除新闻类型
export function delType (newsTypeId) {
  return request({
    url: `${BASE_URI}/${newsTypeId}`,
    method: 'delete'
  })
}
