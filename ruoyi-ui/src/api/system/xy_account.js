import request from '@/utils/request'

// 查询西游账号列表
export function listAccount(query) {
  return request({
    url: '/system/xy_account/list',
    method: 'get',
    params: query
  })
}

// 查询西游账号详细
export function getAccount(account) {
  return request({
    url: '/system/xy_account/' + account,
    method: 'get'
  })
}

// 新增西游账号
export function addAccount(data) {
  return request({
    url: '/system/xy_account',
    method: 'post',
    data: data
  })
}

// 修改西游账号
export function updateAccount(data) {
  return request({
    url: '/system/xy_account',
    method: 'put',
    data: data
  })
}

// 删除西游账号
export function delAccount(account) {
  return request({
    url: '/system/xy_account/' + account,
    method: 'delete'
  })
}



// 导出西游账号
export function exportAccount(query) {
  return request({
    url: '/system/xy_account/export',
    method: 'get',
    params: query
  })
}
