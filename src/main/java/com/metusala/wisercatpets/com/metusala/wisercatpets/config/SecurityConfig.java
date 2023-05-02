package com.metusala.wisercatpets.com.metusala.wisercatpets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                // allow requests to /options and OPTIONS type
                .requestMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .requestMatchers("/options")
                .permitAll()
                // all others need login
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                // redirect to / after login
                .successForwardUrl("/");
        httpSecurity.headers()
                .frameOptions()
                .disable();
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                DataSource dataSource, PasswordEncoder passwordEncoder) throws Exception {
        // get users from database
        auth.jdbcAuthentication().dataSource(dataSource);
    }

}