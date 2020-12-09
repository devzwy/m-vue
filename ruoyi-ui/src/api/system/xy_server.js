import request from '@/utils/request'

//发公告
export function sendNotice(notice) {
  console.log(notice)
  return request({
    url: '/system/xy_server/sendNotice/' + notice,
    method: 'put'
  })


}

//全服补偿  {itemId}/{num}/{title}/{text}/{itemtype}  itemtype 1 物品  2 宠物
export function sendAllGift(itemId, num, title, text, itemtype) {
  return request({
    url: '/system/xy_server/sendAllGift/' + itemId+'/'+num+'/'+title+'/'+text+'/'+itemtype,
    method: 'put'
  })
}



export function sendItems(playerId,itemId, num,  itemtype) {
  console.log('playerId：'+playerId)
  console.log('itemId：'+itemId)
  console.log('num：'+num)
  console.log('itemtype：'+itemtype)

  return request({
    url: '/system/xy_server/sendItems/' +playerId+'/'+itemId+'/'+num+'/'+itemtype,
    method: 'put'
  })
}

