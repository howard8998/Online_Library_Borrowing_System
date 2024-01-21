package com.example.backend.common;

import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthorizationCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull jakarta.servlet.http.HttpServletRequest req,
            @NonNull jakarta.servlet.http.HttpServletResponse res, @NonNull jakarta.servlet.FilterChain chain)
            throws jakarta.servlet.ServletException, IOException {
        // 載入環境變數
        Dotenv dotenv = Dotenv.configure().load();

        // 檢查是否為公開路徑
        if (!req.getServletPath().startsWith("/public/")) {
            // 取得 Authorization Header
            String authorHeader = req.getHeader(AUTHORIZATION);
            String bearer = "Bearer ";

            // 以 jjwt 驗證 token，只要驗證成功就放行
            // 驗證失敗會拋出 exception，直接將錯誤訊息傳回
            if (authorHeader != null && authorHeader.startsWith(bearer)) {
                try {
                    // 提取 token
                    String token = authorHeader.substring(bearer.length());

                    // 使用 JWT_SECRET 創建密鑰
                    Key key = Keys.hmacShaKeyFor(dotenv.get("JWT_SECRET").getBytes());

                    // 解析 token 並取得內容
                    Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

                    // 創建 JwtAuthenticationToken
                    JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(claims.getSubject(), null);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    // 輸出成功訊息
                    System.out.println("合法!\n" + "JWT 內容:" + claims.toString());

                    // 放行
                    chain.doFilter(req, res);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("錯誤 : " + e);

                    // 設定 HTTP 狀態為 FORBIDDEN
                    res.setStatus(FORBIDDEN.value());

                    // 準備錯誤訊息
                    Map<String, String> err = new HashMap<>();
                    err.put("jwt_err", e.getMessage());

                    // 設定回應內容為 JSON 格式
                    res.setContentType(APPLICATION_JSON_VALUE);

                    // 寫入錯誤訊息到回應流
                    new ObjectMapper().writeValue(res.getOutputStream(), err);
                }

            } else {
                // 若沒有 Authorization Header 則設定 HTTP 狀態為 UNAUTHORIZED
                res.setStatus(UNAUTHORIZED.value());
            }
        } else {
            // 若為公開路徑則直接放行
            chain.doFilter(req, res);
        }
    }

}
