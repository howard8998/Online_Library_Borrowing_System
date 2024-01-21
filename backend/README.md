# 注意事項

## .env 檔案配置

原始檔中的為隨機生成字串，為了保護 JWT_SECRET，請使用較安全且嚴格的方式生成和管理。

### 生成安全的 JWT_SECRET

JWT_SECRET 是用於 JSON Web Token (JWT) 的密鑰，應該使用足夠的隨機性和複雜性。以下是一個示例：

```plaintext
# .env

# 請使用一個足夠複雜和安全的密鑰
JWT_SECRET=your_secure_and_complex_key_here

```

## 跨來源資源共用 (CORS) 設定

為了方便測試，所有位於 controller 底下的檔案都已設定允許跨來源資源共用 (CORS)。

```java
@CrossOrigin(origins = "http://localhost:8080")
```

### 如何設定

在您的控制器檔案中，使用 @CrossOrigin 註解，指定允許的來源。

```java
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class YourController {
    // Controller 內容
}
```

### 注意事項

跨來源資源共用 (CORS) 是一項安全機制，確保只有特定來源的請求可以訪問您的 API。請在生產環境中嚴格配置 CORS，只允許必要的來源。

如果您的應用程式在不同的埠口運行，請確保在 origins 中指定正確的埠口。

在生產環境中，建議根據實際需求進行更嚴格的 CORS 設定，並使用安全的方式處理跨來源請求。

``` java
@CrossOrigin(origins = {"http://allowed-origin-1", "http://allowed-origin-2"})
```

## 初始化資料庫

資料庫初始化腳本存放於 <backend\src\main\resources\DB> 目錄。這些腳本用於創建資料庫表格、填充初始資料，或進行其他資料庫相關的初始化操作。

### 資料庫初始化腳本列表

schema.sql: 用於定義資料庫的結構，包括表格和索引的創建。
data.sql: 生成初始測資，預設為十本隨機書籍放置資料庫中

### 執行初始化腳本

執行程式時會自動運行一次 `<backend\src\main\java\com\example\backend\common\SqlScriptRunner.java>` 
來載入資料庫格式以及測試資料，請依照時機資料庫情形來修改初始化腳本