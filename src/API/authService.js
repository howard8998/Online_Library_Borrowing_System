import axios from "axios";
import store from "@/store";
import router from "@/router";
const authService = {
  async login(phoneNumber, password) {
    try {
      const response = await axios.post(
        `${process.env.VUE_APP_API_ENDPOINT}/public/login`,
        {
          phoneNumber,
          password,
        }
      );

      if (response.data.status === "success") {
        const token = response.data.token;
        const userId = response.data.userId;
        store.commit("setToken", token);
        store.commit("setUserID", userId);
        return { success: true, token, userId };
      } else {
        return { success: false, message: "Authentication failed" };
      }
    } catch (error) {
      console.error("Error during login:", error);
      return { success: false, message: "Error during login" };
    }
  },
  async logout() {
    try {
      // 清除 Vuex 中的 token
      localStorage.removeItem("vuex");
      store.commit("clearStoore");
      return { success: true };
    } catch (error) {
      console.error("Error during logout:", error);
      return { success: false, message: "Error during logout" };
    }
  },
  async register(phoneNumber, password, userName) {
    try {
      const response = await axios.post(
        `${process.env.VUE_APP_API_ENDPOINT}/public/register`,
        {
          phoneNumber,
          password,
          userName,
        }
      );

      if (response.data.status === "success") {
        // 註冊成功
        return { success: true };
      } else {
        // 註冊失敗
        return { success: false, message: response.data.message };
      }
    } catch (error) {
      console.error("Error during registration:", error);
      return { success: false, message: "Error during registration" };
    }
  },
  setupAxiosInterceptors() {
    // 設定請求攔截器
    axios.interceptors.request.use(
      (config) => {
        const token = store.state.userToken;
        if (token) {
          config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
      },
      (error) => {
        return Promise.reject(error);
      }
    );

    // 設定回應攔截器
    axios.interceptors.response.use(
      (response) => {
        return response;
      },
      (error) => {
        // 檢查是否為 token 過期錯誤 (示例，實際應根據後端返回的錯誤進行判斷)
        if (error.response && error.response.status === 401) {
          // token 過期，執行登出操作
          store.commit("setToken", null);
          router.push("/login"); // 導向登入頁面
        }
        return Promise.reject(error);
      }
    );
  },
};

export default authService;
