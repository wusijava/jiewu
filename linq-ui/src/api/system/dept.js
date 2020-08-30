import request from '@/utils/request'

const BASE_URI = '/system/dept'

// 查询部门列表
export function listDept (query) {
  return request({
    url: `${BASE_URI}/list`,
    method: 'get',
    params: query
  })
}

// 查询部门列表（排除节点）
export function listDeptExcludeChild (deptId) {
  return request({
    url: `${BASE_URI}/list/exclude/${deptId}`,
    method: 'get'
  })
}

// 查询部门详细
export function getDept (deptId) {
  return request({
    url: `${BASE_URI}/${deptId}`,
    method: 'get'
  })
}

// 查询部门下拉树结构
export function treeSelect () {
  return request({
    url: `${BASE_URI}/tree/select`,
    method: 'get'
  })
}

// 根据角色ID查询部门树结构
export function roleDeptTreeSelect (roleId) {
  return request({
    url: `${BASE_URI}/role/dept/tree/select/${roleId}`,
    method: 'get'
  })
}

// 新增部门
export function addDept (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改部门
export function updateDept (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除部门
export function delDept (deptId) {
  return request({
    url: `${BASE_URI}/${deptId}`,
    method: 'delete'
  })
}
