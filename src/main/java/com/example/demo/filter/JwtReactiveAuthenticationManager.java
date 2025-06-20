package com.example.demo.filter;

import com.example.demo.user.ReactiveUserDetailsServiceImpl;
import com.example.demo.util.JwtUtil;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class JwtReactiveAuthenticationManager
    implements ReactiveAuthenticationManager {

  private final JwtUtil jwtUtil;
  private final ReactiveUserDetailsServiceImpl userDetailsService;

  @Override
  public Mono<Authentication> authenticate(Authentication auth) {
    String token = auth.getCredentials().toString();
    String email = jwtUtil.extractEmail(token);

    return userDetailsService.findByUsername(email)
        .filter(user -> jwtUtil.validateToken(token, user))
        .map(user -> new UsernamePasswordAuthenticationToken(user, token, user.getAuthorities()));
  }
}