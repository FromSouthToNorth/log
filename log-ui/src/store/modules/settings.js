import {blackAndWhite} from "@/api/config";

const state = {
  title: '',
  preloaderHidden: true,
  isBlackAndWhite: undefined
}

const mutations = {
  SET_IS_BLACK_AND_WHITE: (state, isBlackAndWhite) => {
    state.isBlackAndWhite = isBlackAndWhite
  }
}

const actions = {
  setTitle({ commit }, title) {
    state.title = title
  },

  setPreloaderHidden({ commit }, preloaderHidden) {
    state.preloaderHidden = preloaderHidden
  },

  // 向后段获取网页是否是黑白风格
  getBlackAndWhite({ commit }) {
    blackAndWhite().then(result => {
      commit('SET_IS_BLACK_AND_WHITE', result.data)
      if (result.data) {
        document.querySelector('html').setAttribute('class', 'BlackAndWhite')
      }
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}