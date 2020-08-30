import request from '@/utils/request'
import { praseStrEmpty } from '@/utils/linq'

const BASE_URI = '/system/user'

// 查询用户列表
export function listUser (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 新增用户
export function addUser (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateUser (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除用户
export function delUser (userIds) {
  return request({
    url: `${BASE_URI}/${userIds}`,
    method: 'delete'
  })
}

// 用户密码重置
export function resetUserPwd (userId, password) {
  const data = {
    userId,
    password
  }
  return request({
    url: `${BASE_URI}/reset/pwd`,
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeUserStatus (userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: `${BASE_URI}/change/status`,
    method: 'put',
    data: data
  })
}


// 查询用户详细
export function getUser (userId) {
  return request({
    url: `${BASE_URI}/${praseStrEmpty(userId)}`,
    method: 'get'
  })
}


// 查询用户个人信息
export function getUserProfile () {
  return request({
    url: `${BASE_URI}/profile`,
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile (data) {
  return request({
    url: `${BASE_URI}/profile`,
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd (oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: `${BASE_URI}/profile/update/pwd`,
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar (data) {
  return request({
    url: `${BASE_URI}/profile/avatar`,
    method: 'post',
    data: data
  })
}
