package com.rookies4.myspringboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity //Authentication (인증) 활성화
@EnableMethodSecurity //Authorization (권한) 활성화
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    //authentication(인증)을 위한 User 생성 (관리자, 일반사용자)
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("adminboot")
                .password(encoder.encode("pwd1"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("userboot")
                .password(encoder.encode("pwd2"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return  //csrf 기능 비활성화
                http.csrf(csrf -> csrf.disable())
                        //요청별로 권한을 설정
                        .authorizeHttpRequests(auth -> {
                            // api/users/welcome 경로는 인증 없이 접근가능함
                            auth.requestMatchers("/api/users/welcome","/userinfos/new").permitAll()
                                    //  api/users/welcome 경로는 인증이 반드시 필요함
                                    .requestMatchers("/api/users/**").authenticated();
                        })
                        // form로그인 페이지는 스프링이 디폴트로 제공하는 페이지를 사용하겠다.
                        .formLogin(withDefaults())
                        .build();
    }
}
