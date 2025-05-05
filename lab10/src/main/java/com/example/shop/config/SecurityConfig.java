package com.example.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("admin")
                        .requestMatchers("/user/**").hasAnyRole("user", "admin")
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = new java.util.ArrayList<>();

            // Извлекаем роли из realm_access.roles
            Object realmAccess = jwt.getClaims().get("realm_access");
            if (realmAccess instanceof Map<?, ?> realmAccessMap) {
                Object roles = realmAccessMap.get("roles");
                if (roles instanceof Collection<?> roleList) {
                    for (Object role : roleList) {
                        authorities.add(() -> "ROLE_" + role);
                    }
                }
            }

            System.out.println("Authorities from token: " + authorities);
            return authorities;
        });

        return converter;
    }

}
