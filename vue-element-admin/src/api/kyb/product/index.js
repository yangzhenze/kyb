import request from '@/utils/request'

// 获取列表
export function getList(page, params) {
  return request({
    url: '/api/product/page/' + page,
    method: 'get',
    params
  })
}

// 添加
export function addProduct(params) {
  request.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
  return request({
    url: '/api/product',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: params
  })
}

// 更新
export function updateProduct(params) {
  request.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
  return request({
    url: '/api/product',
    method: 'put',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    params
  })
}

// 获取详情
export function getProduct(id) {
  return request({
    url: '/api/product/' + id,
    method: 'get'
  })
}

// 删除
export function delProduct(id) {
  return request({
    url: '/api/product/' + id,
    method: 'delete'
  })
}
