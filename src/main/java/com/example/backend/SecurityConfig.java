package com.example.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.backend.common.AuthorizationCheckFilter;

@Configuration
public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                // 在這裡進行相關的安全配置
                http
                                .authorizeHttpRequests(requests -> requests
                                                .requestMatchers("/public/**").permitAll()
                                                .requestMatchers("/error").permitAll()
                                                .anyRequest().authenticated())
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(new AuthorizationCheckFilter(),
                                                UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }
}
