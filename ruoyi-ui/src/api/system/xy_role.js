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
export function getXy_role(xyRoleId) {
  return request({
    url: '/system/xy_role/' + xyRoleId,
    method: 'get'
  })
}

// 新增西游角色
export function addXy_role(data) {
  return request({
    url: '/system/xy_role',
    method: 'post',
    data: data
  })
}

// 修改西游角色
export function updateXy_role(data) {
  return request({
    url: '/system/xy_role',
    method: 'put',
    data: data
  })
}

// 删除西游角色
export function delXy_role(xyRoleId) {
  return request({
    url: '/system/xy_role/' + xyRoleId,
    method: 'delete'
  })
}

// 禁言西游角色
export function ban_role_send_msg(xyRoleId,time) {
  return request({
    url: '/system/xy_role/banMsg/' + xyRoleId+"/"+time,
    method: 'put'
  })
}

// 禁言西游角色
export function ban_role_fh(xyRoleId,type) {
  return request({
    url: '/system/xy_role/ban_role_fh/' + xyRoleId+"/"+type,
    method: 'put'
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
