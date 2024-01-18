package com.example.backend.common;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SqlScriptRunner {

    private final JdbcTemplate jdbcTemplate;

    @Value("classpath:DB/schema.sql")
    private Resource schemaScript;

    @Value("classpath:DB/data.sql")
    private Resource dataScript;

    public SqlScriptRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void runScripts() {
        executeScript(schemaScript);
        executeScript(dataScript);
    }

    private void executeScript(Resource script) {
        try {
            String scriptContent = new String(FileCopyUtils.copyToByteArray(script.getInputStream()), StandardCharsets.UTF_8);
            jdbcTemplate.execute(scriptContent);
        } catch (IOException e) {
            e.printStackTrace();
            // 處理異常情況
        }
    }
}
