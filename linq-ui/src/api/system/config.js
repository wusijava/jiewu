import request from '@/utils/request'

const BASE_URI = '/system/config'

// 查询参数列表
export function listConfig (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 查询参数详细
export function getConfig (configId) {
  return request({
    url: `${BASE_URI}/${configId}`,
    method: 'get'
  })
}

// 根据参数键名查询参数值
export function getConfigKey (configKey) {
  return request({
    url: `${BASE_URI}/configKey/${configKey}`,
    method: 'get'
  })
}

// 新增参数配置
export function addConfig (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改参数配置
export function updateConfig (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除参数配置
export function delConfig (configId) {
  return request({
    url: `${BASE_URI}/${configId}`,
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
