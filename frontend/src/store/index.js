// store/index.js
import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  plugins: [createPersistedState()],
  state: {
    userToken: null,
    userID: null,
    books: [],
    borrowHistory: [],
  },
  mutations: {
    setToken(state, token) {
      state.userToken = token;
    },
    setUserID(state, userID) {
      state.userID = userID;
    },
    setBooks(state, books) {
      state.books = books;
    },
    setBorrowHistory(state,borrowHistory){
      state.borrowHistory = borrowHistory;
    },
    clearStore(state) {
      state.userToken = null;
      state.books = [];
      state.borrowHistory = [];
      state.userID = null;
    },
  },
});
