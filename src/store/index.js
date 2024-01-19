import { createStore } from 'vuex'; 

export default createStore({
  state: {
    userToken: null,
  },
  mutations: {
    setToken(state, token) {
      state.userToken = token;
    },
  },
});
