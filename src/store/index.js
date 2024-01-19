// store/index.js
import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  plugins: [createPersistedState()],
  state: {
    userToken: null,
    books: [],
  },
  mutations: {
    setToken(state, token) {
      state.userToken = token;
    },
    setBooks(state, books) {
      state.books = books;
    },
    clearStore(state) {
      state.userToken = null;
      state.books = [];
    },
  },
});
