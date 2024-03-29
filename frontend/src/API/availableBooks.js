// api/availableBooks.js

import axios from 'axios';

const availableBooks = {
  // 獲取可借書籍列表的 API 請求
  async getAvailableBooks() {
    try {
      const response = await axios.get(`${process.env.VUE_APP_API_ENDPOINT}/api/available-books`);
      return response.data;
    } catch (error) {
      console.error('獲取可借書籍列表失敗', error);
      throw error;
    }
  },

};

export default availableBooks;
