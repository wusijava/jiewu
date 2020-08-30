import request from '@/utils/request'

const BASE_URI = '/system/dict/type'




// 查询字典类型列表
export function listType (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 查询字典类型详细
export function getType (dictId) {
  return request({
    url: `${BASE_URI}/${dictId}`,
    method: 'get'
  })
}

// 新增字典类型
export function addType (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改字典类型
export function updateType (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除字典类型
export function delType (dictId) {
  return request({
    url: `${BASE_URI}/${dictId}`,
    method: 'delete'
  })
}

// 清理参数缓存
export function clearCache () {
  return request({
    url: `${BASE_URI}/clear/cache`,
    method: 'delete'
  })
}

// 获取字典选择框列表
export function optionSelect () {
  return request({
    url: `${BASE_URI}/option/select`,
    method: 'get'
  })
}
