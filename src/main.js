// main.js
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router"; // 導入你的 router 實例
import store from "./store"; // 導入你的 store 實例
import createPersistedState from 'vuex-persistedstate'; 
const app = createApp(App);

app.use(router); // 使用 router
app.use(store); // 使用 store
store.subscribeAction(() => {
  createPersistedState()(store); // 設定 vuex-persistedstate
});
app.mount("#app");
