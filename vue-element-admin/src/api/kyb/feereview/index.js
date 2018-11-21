import request from '@/utils/request'

// 获取列表
export function getList(page, params) {
  return request({
    url: '/api/fee/page/' + page,
    method: 'get',
    params
  })
}

// 审核
export function review(id, status, params) {
  request.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
  return request({
    url: '/api/fee/' + id + '/' + status,
    method: 'put',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    params
  })
}

// 获取详情
export function getFeeReview(id) {
  return request({
    url: '/api/fee/' + id,
    method: 'get'
  })
}