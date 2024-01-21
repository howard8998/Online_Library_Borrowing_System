<template>
  <div>
    <h1>HomePage</h1>
    <button
      @click="showBorrowedBooksDialog"
      style="margin-left: 1000px; margin-bottom: 5px"
    >
      查看未還書籍
    </button>
    <div v-if="books.length > 0" class="book-list">
      <h2>可借書籍列表</h2>
      <ul>
        <li
          v-for="book in availableBooks"
          :key="book.inventoryID"
          class="book-item"
        >
          <div class="book-info">
            <span>{{ book.name }} by {{ book.author }}</span>
          </div>
          <div class="book-actions">
            <button @click="showBookDetails(book)">顯示詳細資訊</button>
            <button @click="borrowBook(book.inventoryID)">借閱</button>
          </div>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>目前沒有可借書籍。</p>
    </div>
    <!-- 借閱中書籍彈窗 -->
    <dialog
      v-if="borrowedBooksDialog"
      class="borrowed-books-dialog"
      open="borrowedBooksDialog"
    >
      <h2>已借書籍列表</h2>
      <ul>
        <li
          v-for="borrowHistory in borrowHistory"
          :key="borrowHistory.inventoryID"
        >
          {{ borrowHistory.name }} by {{ borrowHistory.author }}
          <button @click="returnBook(borrowHistory.inventoryId)">歸還</button>
        </li>
      </ul>
      <button @click="closeBorrowedBooksDialog">關閉</button>
    </dialog>
    <!-- 書籍詳細資訊的彈窗 -->
    <dialog
      v-if="selectedBook"
      class="book-details"
      open="selectedBook !== null"
    >
      <h2>{{ selectedBook.name }} by {{ selectedBook.author }}</h2>
      <p>{{ selectedBook.introduction }}</p>
      <div class="book-actions">
        <button @click="closeBookDetailsDialog">關閉對話框</button>
      </div>
    </dialog>

    <button @click="logout" class="logout-button">登出</button>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import availableBooks from "@/API/availableBooks";
import authService from "@/API/authService";
import borrowReturn from "@/API/borrowReturn";
import getUserBorrowHistory from "@/API/getUserBorrowHistory";

export default {
  data() {
    return {
      selectedBook: null,
      borrowedBooksDialog: false, // 新增 borrowedBooksDialog 状态
    };
  },
  computed: {
    ...mapState(["books", "borrowHistory"]),
    availableBooks() {
      return this.books;
    },
    getUserBorrowHistory() {
      return this.borrowHistory;
    },
  },
  methods: {
    ...mapMutations(["setBooks", "setBorrowHistory"]),
    async fetchAvailableBooks() {
      try {
        const books = await availableBooks.getAvailableBooks();
        this.setBooks(books);
      } catch (error) {
        console.error("獲取可借書籍列表失敗", error);
      }
    },
    async fetchBorrowHistory() {
      try {
        const borrowHistory = await getUserBorrowHistory.getUserBorrowHistory(
          this.$store.state.userID
        );
        this.setBorrowHistory(borrowHistory);
      } catch (error) {
        console.error("獲取借閱歷史記錄失敗", error);
      }
    },
    showBookDetails(book) {
      this.selectedBook = book;
    },
    closeBookDetailsDialog() {
      this.selectedBook = null;
    },
    async borrowBook(inventoryID) {
      try {
        const response = await borrowReturn.borrowBook(
          inventoryID,
          this.$store.state.userID
        );
        this.fetchAvailableBooks();
        this.fetchBorrowHistory(),
        console.log(response);
      } catch (error) {
        console.error("借書失敗", error);
      }
    },
    async returnBook(inventoryID) {
      try {
        const response = await borrowReturn.returnBook(
          inventoryID,
          this.$store.state.userID
        );
        this.fetchBorrowHistory();
        this.fetchAvailableBooks();
        console.log(response);
      } catch (error) {
        console.error("還書失敗", error);
      }
    },
    showBorrowedBooksDialog() {
      this.borrowedBooksDialog = true;
    },
    closeBorrowedBooksDialog() {
      this.borrowedBooksDialog = false;
    },

    async logout() {
      const logoutResult = await authService.logout();
      if (logoutResult.success) {
        console.log("Logout successful");
        this.$router.push("/login");
      } else {
        console.error("Logout failed:", logoutResult.message);
      }
    },
  },
  created() {
    this.fetchAvailableBooks();
    this.fetchBorrowHistory();
  },
};
</script>

<style></style>

<style>
.book-list {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 20px;
}

.book-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #eee;
  padding: 10px 0;
  margin-left: 110px;
}

.book-info {
  flex: 1;
}

.book-actions button {
  margin-left: 10px;
}

.book-details {
  border: 1px solid #ccc;
  padding: 10px;
  margin-top: 20px;
}

.logout-button {
  margin-top: 20px;
}

.container {
  width: 100%;
  max-width: 1200px;

  min-width: 300px;
  margin: 0 auto;
}

.container {
  display: flex;
  justify-content: space-between;
}
</style>
