package com.pragma.user.adapters.securityconfig;

import com.pragma.user.adapters.securityconfig.jwtconfiguration.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String OWNER_ROLE = "OWNER";
    private static final String CUSTOMER_ROLE = "CUSTOMER";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth-user/login").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/swagger-resources/**").permitAll()
                                .requestMatchers("/webjars/**").permitAll()
                                .requestMatchers("/create-user/owner").permitAll()
                                .requestMatchers("/create-user/admin").hasRole(OWNER_ROLE)
                                .requestMatchers("/auth-user/validate").hasAnyRole(OWNER_ROLE, ADMIN_ROLE, CUSTOMER_ROLE)
                                .requestMatchers("/create-user/ware-house-assistant").hasRole(ADMIN_ROLE)
                                .requestMatchers("/create-user/customer").hasRole(ADMIN_ROLE)
                                .requestMatchers("/auth-user/get-user-id").hasRole(ADMIN_ROLE)
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

