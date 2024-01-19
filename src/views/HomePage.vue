<template>
  <div>
    <h1>HomePage</h1>
    <div v-if="books.length > 0" class="book-list">
      <h2>可借書籍列表</h2>
      <ul>
        <li v-for="book in availableBooks" :key="book.ISBN" class="book-item">
          <div class="book-info">
            <span>{{ book.name }} by {{ book.author }}</span>
          </div>
          <div class="book-actions">
            <button @click="showBookDetails(book)">顯示詳細資訊</button>
            <button
              @click="borrowBook(book.ISBN)"
              :disabled="!canBorrowBook(book.ISBN)"
            >
              借閱
            </button>
          </div>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>目前沒有可借書籍。</p>
    </div>

    <!-- 書籍詳細資訊的彈窗 -->
    <div v-if="selectedBook" class="book-details">
      <h2>{{ selectedBook.Name }} by {{ selectedBook.Author }}</h2>
      <p>{{ selectedBook.Introduction }}</p>
      <div class="book-actions">
        <button
          @click="borrowBook(selectedBook.ISBN)"
          :disabled="!canBorrowBook(selectedBook.ISBN)"
        >
          借書
        </button>
        <button
          @click="returnBook(selectedBook.ISBN)"
          :disabled="!canReturnBook(selectedBook.ISBN)"
        >
          還書
        </button>
      </div>
    </div>

    <button @click="logout" class="logout-button">登出</button>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import availableBooks from "@/API/availableBooks";

export default {
  computed: {
    ...mapState(["books"]),
    availableBooks() {
      return this.books;
    },
  },
  methods: {
    ...mapMutations(["setBooks"]),
    async fetchAvailableBooks() {
      try {
        // 調用 API 並獲取可借書籍列表
        const books = await availableBooks.getAvailableBooks();
        // 將獲取到的書籍列表保存到 Vuex 中的 books 狀態中
        this.setBooks(books);
      } catch (error) {
        // 處理錯誤
        console.error("獲取可借書籍列表失敗", error);
      }
    },
    showBookDetails(book) {
      this.selectedBook = book;
    },
    borrowBook(/*ISBN*/) {
      // 這裡需要實現借書的邏輯，可以呼叫 API 或 Vuex 中的相應函數
      //console.log(`Borrowing book with ISBN: ${ISBN}`);
    },
    returnBook(/*ISBN*/) {
      // 這裡需要實現還書的邏輯，可以呼叫 API 或 Vuex 中的相應函數
      //console.log(`Returning book with ISBN: ${ISBN}`);
    },
    canBorrowBook(/*ISBN*/) {
      // 根據你的邏輯判斷是否可以借閱該書籍
      return true;
    },
    canReturnBook(/*ISBN*/) {
      // 根據你的邏輯判斷是否可以還書
      return true;
    },
    logout() {
      // 實現登出邏輯，可能需要清除 token 等
      console.log("Logging out...");
    },
  },
  created() {
    // 在組件創建時調用 fetchAvailableBooks 方法，以獲取可借書籍列表
    this.fetchAvailableBooks();
  },
};
</script>
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
/* 在你的樣式表中 */
.container {
  width: 100%; /* 使用百分比，讓容器隨著屏幕大小變化 */
  max-width: 1200px;
   /* 避免容器變得太寬 */
  min-width: 300px;
  margin: 0 auto; /* 在大屏幕上居中顯示 */
}

/* 將這些樣式應用到你的元素 */
.container {
  display: flex;
  justify-content: space-between;
}

/* 這是一個簡單的示例，你可能需要根據實際情況進行調整 */
</style>
