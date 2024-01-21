package com.example.backend;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.backend.common.SqlScriptRunner;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
    private final SqlScriptRunner sqlScriptRunner;

    public BackendApplication(SqlScriptRunner sqlScriptRunner) {
        this.sqlScriptRunner = sqlScriptRunner;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
    //載入資料庫設定
    @Override
    public void run(String... args) throws Exception {
        sqlScriptRunner.runScripts();
    }
}
