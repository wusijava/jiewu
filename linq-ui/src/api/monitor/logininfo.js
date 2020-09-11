import request from '@/utils/request'

const BASE_URI = '/monitor/logininfo'

// 查询登录日志列表
export function list (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 删除登录日志
export function delLogininfo (infoId) {
  return request({
    url: `${BASE_URI}/${infoId}`,
    method: 'delete'
  })
}

// 清空登录日志
export function cleanLogininfo () {
  return request({
    url: `${BASE_URI}/clean`,
    method: 'delete'
  })
}

