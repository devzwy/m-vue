import request from '@/utils/request'

// 查询西游角色列表
export function listXy_role(query) {
  return request({
    url: '/system/xy_role/list',
    method: 'get',
    params: query
  })
}

// 查询西游角色详细
export function getXy_role(account) {
  return request({
    url: '/system/xy_role/' + account,
    method: 'get'
  })
}

// 删除西游角色
export function delXy_role(account) {
  return request({
    url: '/system/xy_role/' + account,
    method: 'delete'
  })
}

// 导出西游角色
export function exportXy_role(query) {
  return request({
    url: '/system/xy_role/export',
    method: 'get',
    params: query
  })
}
