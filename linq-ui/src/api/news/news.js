import request from '@/utils/request'

const BASE_URI = '/news/news'

// 审核新闻
export function changeNewsStatus (data) {
  return request({
    url: `${BASE_URI}/change/status`,
    method: 'put',
    data: data
  })
}


// 设置新闻公开私有
export function changeNewsIsPublic (newsId, isPublic) {
  const data = {
    newsId,
    isPublic
  }
  return request({
    url: `${BASE_URI}/change/isPublic`,
    method: 'put',
    data: data
  })
}


// 查询新闻列表
export function listNews (page, size, query) {
  return request({
    url: `${BASE_URI}/list/${page}/${size}`,
    method: 'get',
    params: query
  })
}

// 查询新闻详细
export function getNews (newsId) {
  return request({
    url: `${BASE_URI}/${newsId}`,
    method: 'get'
  })
}

// 新增新闻
export function addNews (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'post',
    data: data
  })
}

// 修改新闻
export function updateNews (data) {
  return request({
    url: `${BASE_URI}`,
    method: 'put',
    data: data
  })
}

// 删除新闻
export function delNews (newsId) {
  return request({
    url: `${BASE_URI}/${newsId}`,
    method: 'delete'
  })
}
