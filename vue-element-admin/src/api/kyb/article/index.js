import request from '@/utils/request'


// 获取列表
export function getList(page, params) {
  return request({
    url: '/api/article/page/' + page,
    method: 'get',
    params
  })
}

// 添加
export function addArticle(params) {
  request.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
  return request({
    url: '/api/article',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: params
  })
}

// 更新
export function updateArticle(params) {
  request.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
  return request({
    url: '/api/article',
    method: 'put',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    params
  })
}



// 获取详情
export function getArticle(id) {
  return request({
    url: '/api/article/' + id,
    method: 'get'
  })
}

// 删除
export function delArticle(id) {
  return request({
    url: '/api/article/' + id,
    method: 'delete'
  })
}
