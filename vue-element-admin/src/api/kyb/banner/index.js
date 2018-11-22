import request from '@/utils/request'

// 文件上传
export function upload(params) {
  request.defaults.headers['Content-Type'] = 'multipart/form-data'
  return request({
    url: '/api/banner/upload',
    method: 'post',
    data: params
  })
}

// 获取列表
export function getList(page, params) {
  return request({
    url: '/api/banner/page/' + page,
    method: 'get',
    params
  })
}

// 添加
export function addBanner(params) {
  request.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
  return request({
    url: '/api/banner',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: params
  })
}

// 更新
export function updateBanner(params) {
  request.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
  return request({
    url: '/api/banner',
    method: 'put',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    params
  })
}

// 获取详情
export function getBanner(id) {
  return request({
    url: '/api/banner/' + id,
    method: 'get'
  })
}

// 删除
export function delBanner(id) {
  return request({
    url: '/api/banner/' + id,
    method: 'delete'
  })
}
