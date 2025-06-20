package com.example.demo.security;

import com.example.demo.filter.JwtReactiveAuthenticationManager;
import com.example.demo.security.JwtServerAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtReactiveAuthenticationManager authManager;
  private final JwtServerAuthenticationConverter authConverter;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    AuthenticationWebFilter jwtFilter = new AuthenticationWebFilter(authManager);
    jwtFilter.setServerAuthenticationConverter(authConverter);
    jwtFilter.setSecurityContextRepository(NoOpServerSecurityContextRepository.getInstance());

    return http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
        .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
        .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        .authorizeExchange(exchanges -> exchanges
            .pathMatchers("/api/v1/users/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .pathMatchers(HttpMethod.GET, "/api/v1/users").hasRole("ADMIN")
            .anyExchange().authenticated())
        .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        .build();
  }
}
