const state = {
    //アラートのコンポーネントを表示するかどうか true:表示 false:非表示
    action: false,
    //メッセージ
    message: null,
    //詳細メッセージ
    detail: null
}


const getters = {
    action: state => state.action,
    message: state => state.message,
    detail: state => state.detail
}

const actions = {
    setAlert({ commit }, { action, message, detail }) {
        commit('setAlert', { action: action, message: message, detail: detail })
    },
}

const mutations = {
    setAlert(state, { action, message, detail }) {
        if (action) {
            state.action = action
            state.message = message
            state.detail = detail
        } else {
            state.action = false
            state.message = null
            state.detail = detail
        }
    },
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}