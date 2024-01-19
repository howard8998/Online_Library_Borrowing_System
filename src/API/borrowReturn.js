import axios from "axios";
import store from "@/store";

const API_BASE_URL = `${process.env.VUE_APP_API_ENDPOINT}/api`;

export default {
  async borrowBook(bookId, userId) {
    const token = store.state.userToken;
    // 確認 Token 是否存在
    if (!token) {
      console.error("Token 不存在");
      return;
    }

    try {
      const response = await axios.put(`${API_BASE_URL}/borrow`, null, {
        params: { bookId, userId },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      return response.data;
    } catch (error) {
      console.error("借書 API 請求失敗", error);
      throw error;
    }
  },

  async returnBook(bookId, userId) {
    const token = store.state.userToken;
  
    // 確認 Token 是否存在
    if (!token) {
      console.error("Token 不存在");
      return;
    }
    
    try {

        const response = await axios.delete(`${API_BASE_URL}/return`, {
            params: {bookId,userId },
            headers: {
              Authorization: `Bearer ${token}`,
            },
          });
      return response.data;
    } catch (error) {
      console.error("還書 API 請求失敗", error.response.data);
      throw error;
    }
  }
  
};
