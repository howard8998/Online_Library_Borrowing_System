// store/index.js
import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  plugins: [createPersistedState()],
  state: {
    userToken: null,
  },
  mutations: {
    setToken(state, token) {
      state.userToken = token;
    },
  },
});
