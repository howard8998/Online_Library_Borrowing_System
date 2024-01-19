import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import createPersistedState from 'vuex-persistedstate';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import "bootstrap"

const app = createApp(App);

app.use(router);
app.use(store);
store.subscribeAction(() => {
  createPersistedState()(store);
});

app.mount("#app");
