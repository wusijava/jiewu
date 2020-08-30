import request from '@/utils/request'

const BASE_URI = '/system/role'

// 查询角色列表
export function listRole (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 查询角色详细
export function getRole (roleId) {
  return request({
    url: `${BASE_URI}/${roleId}`,
    method: 'get'
  })
}

// 新增角色
export function addRole (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改角色
export function updateRole (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 角色数据权限
export function dataScope (data) {
  return request({
    url: `${BASE_URI}/data/scope`,
    method: 'put',
    data: data
  })
}

// 角色状态修改
export function changeRoleStatus (roleId, status) {
  const data = {
    roleId,
    status
  }
  return request({
    url: `${BASE_URI}/change/status`,
    method: 'put',
    data: data
  })
}

// 删除角色
export function delRole (roleId) {
  return request({
    url: `${BASE_URI}/${roleId}`,
    method: 'delete'
  })
}
