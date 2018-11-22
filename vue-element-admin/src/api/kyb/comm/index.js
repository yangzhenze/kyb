import request from '@/utils/request'

// 获取列表
export function get() {
  return request({
    url: '/api/comm',
    method: 'get'
  })
}

// 审核
export function update(params) {
  request.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
  return request({
    url: '/api/comm',
    method: 'put',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    params
  })
}

