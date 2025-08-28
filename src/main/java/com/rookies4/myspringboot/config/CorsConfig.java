package com.rookies4.myspringboot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean<?> corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 모든 도메인 요청 허용
        configuration.setAllowedOriginPatterns(List.of("*"));

        // 쿠키 인증정보 요청 허용
        configuration.setAllowCredentials(true);

        // 클라언트가 보낼 수 있는 헤더 목록 설정
        configuration.setAllowedHeaders(Arrays.asList(
                "Access-Control-Allow-Headers",
                "Access-Control-Allow-Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Origin","Cache-Control", "Content-Type", "Authorization"));

        // 클라언트가 보낼 수 있는 HTTP 메서드 목록 설정
        configuration.setAllowedMethods(Arrays.asList("POST", "DELETE", "GET", "PATCH", "PUT"));

        // 특정 URL 패턴별로 CORS 정책 설정 (현재는 모든 경로)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}