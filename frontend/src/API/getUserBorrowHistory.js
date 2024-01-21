// api/user-borrow-history

import axios from 'axios';

const borrowHistory = {

  async getUserBorrowHistory(userId) {
    try {
      const response = await axios.get(`${process.env.VUE_APP_API_ENDPOINT}/api/user-borrow-history`, {
        params: {
          userId: userId
        }
      });
      return response.data;
    } catch (error) {
      console.error('獲取借書歷史失敗', error);
      throw error;
    }
  },
};

export default borrowHistory;
