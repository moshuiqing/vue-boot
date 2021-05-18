import request from '@/utils/request'

const URL = '<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>'
// 分页 查询
export function pageFound(param) {
  return request({
    url: URL + '/pageFound',
    method: 'post',
    params: param
  })
}
// 普通查询
export function simpleFound(param) {
  return request({
    url: URL + '/simpleFound',
    method: 'post',
    params: param
  })
}
// 新增数据
export function add(param) {
  return request({
    url: URL + '/add',
    method: 'post',
    params: param
  })
}
//批量新增
export function addBatch(jsonArr){
  return request({
    url: URL + '/addBatch',
    method: 'post',
    jsonArr
  })
}

//删除数据
export function del(id){
  return request({
    url: URL + '/addBatch',
    method: 'post',
    id
  })
}
//批量删除
export function deletes(str){
  return request({
    url: URL + '/deletes',
    method: 'post',
    str
  })
}







