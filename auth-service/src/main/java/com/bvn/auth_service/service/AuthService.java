package com.bvn.auth_service.service;

import com.bvn.auth_service.dto.LoginRequestDTO;
import com.bvn.auth_service.model.User;
import com.bvn.auth_service.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private static final Logger log = LoggerFactory.getLogger(AuthService.class);
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public AuthService(UserService userService, PasswordEncoder passwordEncoder,
      JwtUtil jwtUtil) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
  }

  public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
    Optional<String> token = userService.findByEmail(loginRequestDTO.getEmail())
        .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(),
            u.getPassword()))
        .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));

    return token;
  }

  public boolean validateToken(String token) {
    try {
      jwtUtil.validateToken(token);
      return true;
    } catch (JwtException e){
      return false;
    }
  }
}