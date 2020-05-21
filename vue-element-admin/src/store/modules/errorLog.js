const errorLog = {
  state: {
    logs: []
  },
  // 你不能直接改变 store 中的状态。改变 store 中的状态的唯一途径就是显式地提交 (commit) mutation。这样使得我们可以方便地跟踪每一个状态的变化，从而让我们能够实现一些工具帮助我们更好地了解我们的应用。
  mutations: {
    // state为vuex返回的对象
    ADD_ERROR_LOG: (state, log) => {
      state.logs.push(log)
    }
  },
  actions: {
    // commit是vuex返回store对象中的一个属性
    addErrorLog({ commit }, log) {
      // 提交mutations对象的方法
      commit('ADD_ERROR_LOG', log)
    }
  }
}

export default errorLog
