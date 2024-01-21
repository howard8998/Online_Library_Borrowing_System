# 線上圖書借閱系統

歡迎使用線上圖書借閱系統！這個項目結合了現代化的前端框架Vue.js和強大的後端框架Spring Boot，提供了一個完整而高效的圖書管理和借閱解決方案。

## 功能介紹

這個系統旨在簡化圖書管理和借閱流程，使圖書館或個人圖書收藏者更容易追蹤和管理他們的書籍。以下是系統的一些主要功能和特點：

### 直觀的前端界面

使用Vue.js搭建的前端界面，簡潔而直觀，讓使用者輕鬆瀏覽圖書庫和執行相關操作。

### 高效的後端處理

基於Spring Boot構建的後端處理系統，提供強大的性能和穩定性，同時支援RESTful API，以實現前後端的無縫通信。

### 用戶身份驗證

系統支援安全的用戶身份驗證機制，確保只有授權用戶可以執行特定的操作，保障圖書資料的安全性。

## 如何開始

為了方便您快速啟動並運行這個系統，請按照以下步驟進行：

### 前端(Vue.js)

#### 1. git clone

Clone這個專案到本地環境：
git clone <https://github.com/howard8998/Online_Library_Borrowing_System.git>

#### 2. 進入前端目錄

cd frontend

#### 3. 安裝相依套件

npm install

#### 4. 啟動前端開發伺服器

npm run serve

#### 現在，您可以通過訪問 <http://localhost:8080> 來訪問前端應用程式

### 後端(Spring boot)

#### 1.進入後端目錄

cd backend

#### 2. 開啟專案，例如使用IDEA或Eclipse

#### 3.啟動Spring Boot應用程式

#### 現在，您的後端應用程式應該已經在 http://localhost:8081 上運行

## 技術堆疊

### 前端： Vue.js, Vuex, Vue Router, AXIOS

### 後端： Spring Boot, Spring Data JPA, Spring Security

### 資料庫: PostgreSQL

## 相依套件

### Spring Boot Starter Web

### Spring Boot Starter Data JPA

### Dotenv for Java

### Spring Boot Starter Security

### JJWT (JSON Web Token)

### PostgreSQL Driver

### Spring Boot Starter Test

## 檔案架構

```bash


+---backend 後端
|   |   .env
|   |   .gitignore
|   |   mvnw
|   |   mvnw.cmd
|   |   pom.xml
|   |   README.md
|   |   
|   +---.mvn 包含 Maven Wrapper 相關的檔案
|   |   \---wrapper
|   |           maven-wrapper.jar
|   |           maven-wrapper.properties
|   |           
|   +---src
|   |   +---main
|   |   |   +---java
|   |   |   |   \---com
|   |   |   |       \---example
|   |   |   |           \---backend
|   |   |   |               |   BackendApplication.java  程式進入點
|   |   |   |               |   SecurityConfig.java request是否攔截設定
|   |   |   |               |   
|   |   |   |               +---common  通用檔案
|   |   |   |               |       AuthorizationCheckFilter.java 檢查攜帶token是否正確
|   |   |   |               |       CustomException.java 處理錯誤訊息
|   |   |   |               |       GlobalExceptionHandler.java 接收錯誤訊息
|   |   |   |               |       JwtAuthenticationToken.java 確認token是否正確
|   |   |   |               |       SqlScriptRunner.java  執行SQL
|   |   |   |               |       
|   |   |   |               +---controller 控制層
|   |   |   |               |       BorrowReturnController.java 接收借還書動作request 
|   |   |   |               |       LoginController.java 接收登入動作request
|   |   |   |               |       RegistrationController.java 註冊request
|   |   |   |               |       
|   |   |   |               +---model 物件模型
|   |   |   |               |       Book.java 書籍
|   |   |   |               |       BorrowingRecord.java 借閱紀錄
|   |   |   |               |       
|   |   |   |               +---repository 資料庫操作
|   |   |   |               |       BookRepository.java 執行與書籍相關操作
|   |   |   |               |       BorrowingRecordRepository.java 執行與借閱紀錄相關操作
|   |   |   |               |       UserRepository.java 執行與用戶相關操作
|   |   |   |               |       
|   |   |   |               \---service 服務層
|   |   |   |                       BookService.java 提供書籍相關服務
|   |   |   |                       BorrowingRecordService.java 提供借閱紀錄相關服務
|   |   |   |                       UserService.java 提供用戶相關服務
|   |   |   |                       
|   |   |   \---resources 靜態資源
|   |   |       |   application.properties 配置文件
|   |   |       |   
|   |   |       \---DB 資料庫操作
|   |   |               data.sql 創建測資
|   |   |               schema.sql 資料庫結構定義檔
|   |   |               
|   |   \---test 測試程式
|   |       \---java
|   |           \---com
|   |               \---example
|   |                   \---backend
|   |                           BackendApplicationTests.java
|   |                           
|   
\---frontend 前端
    |   .env
    |   .gitignore
    |   babel.config.js
    |   jsconfig.json
    |   package-lock.json
    |   package.json
    |   README.md
    |   vue.config.js
    |   
    +---public 靜態資料
    |       favicon.ico
    |       index.html
    |       
    \---src
        |   App.vue 主要組件
        |   main.js 程式進入點
        |   
        +---API 後端API接口
        |       authService.js 登入認證服務
        |       availableBooks.js 取得可借閱書籍
        |       borrowReturn.js 借還書功能
        |       getUserBorrowHistory.js 取得用戶借閱資料
        |       
        +---assets 靜態資源
        |       logo.png
        |       
        +---router 路徑設置
        |       index.js
        |       
        +---store vuex
        |       index.js
        |       
        \---views 頁面
                HomePage.vue 主要頁面(登入後)
                LoginPage.vue 登入頁面
                
```

## 貢獻

歡迎提出問題、改進或新增功能

## 授權

這個專案採用 MIT 授權

