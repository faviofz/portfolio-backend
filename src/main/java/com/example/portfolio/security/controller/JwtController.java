//package com.example.portfolio.security.controller;
//
//import com.example.portfolio.security.entity.User;
//import com.example.portfolio.security.service.JwtService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class JwtController {
//
//  @Autowired
//  private JwtService jwtService;
//
//  @PostMapping
//  public ResponseEntity<String> login(@RequestBody User user) {
//    String token = jwtService.generateToken(user.getUsername());
//
//    return ResponseEntity.ok(token);
//  }
//
//}
