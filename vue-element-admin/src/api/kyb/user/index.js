import request from '@/utils/request'

// 获取列表
export function getList(page, params) {
  return request({
    url: '/api/user/page/' + page,
    method: 'get',
    params
  })
}

// 审核
export function frozen(id, status) {
  request.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
  return request({
    url: '/api/user/' + id + '/' + status,
    method: 'put',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' }
  })
}

