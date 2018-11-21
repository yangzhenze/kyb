import request from '@/utils/request'

// 获取列表
export function getList(page, params) {
  return request({
    url: '/api/msg/page/' + page,
    method: 'get',
    params
  })
}

// 添加
export function addMsg(params) {
  request.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
  return request({
    url: '/api/msg',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: params
  })
}

// 获取详情
export function getMsg(id) {
  return request({
    url: '/api/msg/' + id,
    method: 'get'
  })
}

// 删除
export function delMsg(id) {
  return request({
    url: '/api/msg/' + id,
    method: 'delete'
  })
}
