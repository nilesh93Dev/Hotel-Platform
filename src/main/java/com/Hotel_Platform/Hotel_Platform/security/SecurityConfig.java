package com.Hotel_Platform.Hotel_Platform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;



import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Hotel_Platform.Hotel_Platform.service.CustomUserDetailsService;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(prePostEnabled = true) // replaces @EnableGlobalMethodSecurity
public class SecurityConfig {

    private final JwtTenantFilter jwtTenantFilter;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtTenantFilter jwtTenantFilter,
                          CustomUserDetailsService customUserDetailsService) {
        this.jwtTenantFilter = jwtTenantFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/Hotel/tenantmaster/**").permitAll()

            .requestMatchers("/Hotel/auth/admin-login").permitAll()
            .requestMatchers("/Hotel/auth/login").permitAll()
            .requestMatchers("/Hotel/auth/**").authenticated()
            .requestMatchers("/Hotel/usermaster/**").hasAuthority("ADMIN")   // DB role match
            .requestMatchers("/Hotel/**").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtTenantFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}

    
