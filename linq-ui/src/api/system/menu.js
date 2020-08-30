import request from '@/utils/request'

const BASE_URI = '/system/menu'

// 查询菜单列表
export function listMenu (query) {
  return request({
    url: `${BASE_URI}/list`,
    method: 'get',
    params: query
  })
}

// 查询菜单详细
export function getMenu (menuId) {
  return request({
    url: `${BASE_URI}/${menuId}`,
    method: 'get'
  })
}

// 查询菜单下拉树结构
export function treeSelect () {
  return request({
    url: `${BASE_URI}/tree/select`,
    method: 'get'
  })
}

// 根据角色ID查询菜单下拉树结构
export function roleMenuTreeSelect (roleId) {
  return request({
    url: `${BASE_URI}/role/menu/tree/select/${roleId}`,
    method: 'get'
  })
}

// 新增菜单
export function addMenu (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改菜单
export function updateMenu (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除菜单
export function delMenu (menuId) {
  return request({
    url: `${BASE_URI}/${menuId}`,
    method: 'delete'
  })
}
