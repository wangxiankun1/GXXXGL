import { createStore } from 'vuex'

export default createStore({
  state: {
    //动态路由初始
    asyncRoutestMark:false
  },
  mutations: {
    //修改动态路由标志
    setAsyncRoutestMark (state, n) {
      state.asyncRoutestMark = n
    }
  },
  actions: {
  },
  modules: {
  }
})