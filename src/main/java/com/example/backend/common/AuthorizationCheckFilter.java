package com.example.backend.common;

import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthorizationCheckFilter extends OncePerRequestFilter{

    protected void doFilterInternal (HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
                //如果不是登入就攔截
                if(!req.getServletPath().equals("/login")){
                    String authorHeader =  req.getHeader(AUTHORIZATION);
                    String bearer ="Bearer ";
                    //以jjwt驗證token，只要驗證成功就放行
                    //驗證失敗會拋exception，直接將錯誤訊息傳回
                    if(authorHeader!= null && authorHeader.startsWith(bearer)){
                        try {
                            String token = authorHeader.substring(bearer.length());
                        
                            // 使用 SecretKeySpec 包裝 MySecret
                            Key key = new SecretKeySpec("MySecret".getBytes(), SignatureAlgorithm.HS256.getJcaName());
                        
                            Claims claims = Jwts.parserBuilder()
                                    .setSigningKey(key)
                                    .build()
                                    .parseClaimsJws(token)
                                    .getBody();
                        
                            System.out.println("JWT payload:" + claims.toString());
                        
                            chain.doFilter(req, res);
                        } catch (Exception e) {
                            System.err.println("Error : " + e);
                            res.setStatus(FORBIDDEN.value());
                        
                            Map<String, String> err = new HashMap<>();
                            err.put("jwt_err", e.getMessage());
                            res.setContentType(APPLICATION_JSON_VALUE);
                            new ObjectMapper().writeValue(res.getOutputStream(), err);
                        }
                        
                    }else{
                        res.setStatus(UNAUTHORIZED.value());
                    }
                }else{
                    chain.doFilter(req, res);
                }
        
    }

    
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }

}