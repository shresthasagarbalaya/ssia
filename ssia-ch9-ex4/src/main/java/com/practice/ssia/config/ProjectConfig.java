package com.practice.ssia.config;

import com.practice.ssia.csrf.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class ProjectConfig {

    private final CustomCsrfTokenRepository customTokenRepository;

    public ProjectConfig(CustomCsrfTokenRepository customTokenRepository) {
        this.customTokenRepository = customTokenRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c-> {
            c.csrfTokenRepository(customTokenRepository);
            c.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());
        });
        http.authorizeHttpRequests(
                c-> c.anyRequest().permitAll()
        );
        return http.build();
    }
}
