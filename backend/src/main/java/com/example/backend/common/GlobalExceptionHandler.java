package com.example.backend.common;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException e) {
        // 在此處理自定義異常，將錯誤消息返回到前端
        String errorMessage = e.getMessage();
        return ResponseEntity.status(Objects.requireNonNull(e.getStatusCode())).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // 在此处理所有未被特定ExceptionHandler捕捉到的異常，将错误消息返回到前端
        String errorMessage = "發生了一個錯誤：" + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}

