import { pageFound, simpleFound, add, addBatch, del, deletes } from '@/api/${entity?lower_case}'

const actions = {
  pageFound({ commit }, param) {
    return new Promise((resolve, reject) => {
      pageFound(param).then(res => {
        resolve(res)
      }).catch(err => {
        reject(err)
      })
    })
  },
   simpleFound({ commit }, param) {
    return new Promise((resolve, reject) => {
      simpleFound(param).then(res => {
        resolve(res)
      }).catch(err => {
        reject(err)
      })
    })
  },
  add({ commit }, param) {
    return new Promise((resolve, reject) => {
      add(param).then(res => {
        resolve(res)
      }).catch(err => {
        reject(err)
      })
    })
  },
  addBatch({ commit }, jsonArr) {
    return new Promise((resolve, reject) => {
      addBatch(jsonArr).then(res => {
        resolve(res)
      }).catch(err => {
        reject(err)
      })
    })
  },
  del({ commit }, id) {
    return new Promise((resolve, reject) => {
      del(id).then(res => {
        resolve(res)
      }).catch(err => {
        reject(err)
      })
    })
  },
  deletes({ commit }, str) {
    return new Promise((resolve, reject) => {
      deletes(str).then(res => {
        resolve(res)
      }).catch(err => {
        reject(err)
      })
    })
  }
  
}

export default {
  namespaced: true,
  actions
}

