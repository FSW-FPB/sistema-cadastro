package com.fsw_fpb.sistemacadastro.config;

import com.fsw_fpb.sistemacadastro.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF, não necessário para APIs REST com JWT
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/medicos/login", "/atendentes/login", "/pacientes/login", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permite login sem token
                        .anyRequest().authenticated()  // Requer autenticação para todas as outras rotas
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Sem estado (stateless)
                .addFilterBefore(new JwtAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class) // Validação JWT personalizada
                .build();
    }

}
