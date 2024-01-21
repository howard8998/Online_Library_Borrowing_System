// router/index.js
import { createRouter, createWebHashHistory } from "vue-router";
import store from "@/store";
import authService from "@/API/authService";
import LoginPage from "@/views/LoginPage.vue";
import HomePage from "@/views/HomePage.vue";

const routes = [
  {
    path: "/",
    redirect: '/home',
  },
  {
    path: "/login",
    name: "LoginPage",
    component: LoginPage,
  },
  {
    path: "/home",
    name: "HomePage",
    component: HomePage,
    meta: { requiresAuth: true },
  },
  // 其他路由配置
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

router.beforeEach(async (to, from, next) => {
  const requiresAuth = to.matched.some((record) => record.meta.requiresAuth);

  // 如果需要驗證且沒有 token，導向登入頁面
  if (requiresAuth && !store.state.userToken) {
    next({ name: "LoginPage" });
  } else {
    // 否則，允許路由切換
    next();
  }
});

authService.setupAxiosInterceptors(); // 設定 Axios 攔截器

export default router;
