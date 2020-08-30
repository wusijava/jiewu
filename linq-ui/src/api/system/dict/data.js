import request from '@/utils/request'

const BASE_URI = '/system/dict/data'

// 查询字典数据列表
export function listData (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 查询字典数据详细
export function getData (dictCode) {
  return request({
    url: `${BASE_URI}/${dictCode}`,
    method: 'get'
  })
}

// 根据字典类型查询字典数据信息
export function getDicts (dictType) {
  return request({
    url: `${BASE_URI}/type/${dictType}`,
    method: 'get'
  })
}

// 新增字典数据
export function addData (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改字典数据
export function updateData (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除字典数据
export function delData (dictCode) {
  return request({
    url: `${BASE_URI}/${dictCode}`,
    method: 'delete'
  })
}

