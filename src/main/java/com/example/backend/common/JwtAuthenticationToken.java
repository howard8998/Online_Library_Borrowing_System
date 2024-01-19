package com.example.backend.common;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String username;
    private String token;

    public JwtAuthenticationToken(String token) {
        super(null);
        this.token = token;
        this.username = null;
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    // 如果需要存储更多的用户信息，可以添加相应的 getter 和 setter 方法

    // 省略一些方法，比如 equals, hashCode 等
}
