// main.js

import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from './views/LoginPage.vue';
import App from './App.vue';  // 引入根組件

const routes = [
  {
    path: '/',
    name: 'LoginPage',
    component: LoginPage,
  },
  // 其他路由配置
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

const app = createApp(App);  // 將根組件傳遞給 createApp 函數
app.use(router); // 使用 router
app.mount('#app');
