import request from '@/utils/request'

// 查询西游物资列表
export function listXy_item(query) {
  return request({
    url: '/system/xy_item/list',
    method: 'get',
    params: query
  })
}

// 查询西游物资详细
export function getXy_item(id) {
  return request({
    url: '/system/xy_item/' + id,
    method: 'get'
  })
}

// 新增西游物资
export function addXy_item(data) {
  return request({
    url: '/system/xy_item',
    method: 'post',
    data: data
  })
}

// 修改西游物资
export function updateXy_item(data) {
  return request({
    url: '/system/xy_item',
    method: 'put',
    data: data
  })
}

// 删除西游物资
export function delXy_item(id) {
  return request({
    url: '/system/xy_item/' + id,
    method: 'delete'
  })
}

// 导出西游物资
export function exportXy_item(query) {
  return request({
    url: '/system/xy_item/export',
    method: 'get',
    params: query
  })
}