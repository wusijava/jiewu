import request from '@/utils/request'

const BASE_URI = '/monitor/online'

// 查询在线用户列表
export function list (query) {
  return request({
    url: `${BASE_URI}/list`,
    method: 'get',
    params: query
  })
}

// 强退用户
export function forceLogout (tokenId) {
  return request({
    url: `${BASE_URI}/${tokenId}`,
    method: 'delete'
  })
}
