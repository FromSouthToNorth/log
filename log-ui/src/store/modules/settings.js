const state = {
  title: '',
}

const mutations = {

}

const actions = {
  setTitle({ commit }, title) {
    state.title = title
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}