import request from '@/utils/request'

const BASE_URI = '/monitor/operlog'

// 查询操作日志列表
export function list (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 删除操作日志
export function delOperlog (operId) {
  return request({
    url: `${BASE_URI}/${operId}`,
    method: 'delete'
  })
}

// 清空操作日志
export function cleanOperlog () {
  return request({
    url: `${BASE_URI}/clean`,
    method: 'delete'
  })
}
