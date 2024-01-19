// api/user-borrow-history

import axios from 'axios';

const availableBooks = {

  async getAvailableBooks() {
    try {
      const response = await axios.get(`${process.env.VUE_APP_API_ENDPOINT}/api/user-borrow-history`);
      return response.data;
    } catch (error) {
      console.error('獲取可借書籍列表失敗', error);
      throw error;
    }
  },
};

export default availableBooks;