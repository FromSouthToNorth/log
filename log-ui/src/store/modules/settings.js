const state = {
  title: '',
  preloaderHidden: true
}

const mutations = {

}

const actions = {
  setTitle({ commit }, title) {
    state.title = title
  },

  setPreloaderHidden({ commit }, preloaderHidden) {
    state.preloaderHidden = preloaderHidden
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}