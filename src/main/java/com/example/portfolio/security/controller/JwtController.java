package com.example.portfolio.security.controller;

import com.example.portfolio.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class JwtController {
  
  @Autowired
  private JwtService jwtService;
  
  @PreAuthorize("authenticated")
  @PostMapping
  public ResponseEntity<String> login(@AuthenticationPrincipal UserDetails userDetails) {
    String token = jwtService.generateToken(userDetails.getUsername());
    
    return ResponseEntity.ok(token);
  }
  
}
