package com.example.portfolio.security;

import com.example.portfolio.security.service.JwtService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
  
  @Autowired
  JwtService jwtService;
  
  public JwtAuthorizationFilter(AuthenticationManager manager) {
    super(manager);
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain
  ) throws ServletException, IOException {
    
    String token, username;
    final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      log.info(">>>FILTER JWT");
      token = authorizationHeader.substring("Bearer ".length());
      username = this.jwtService.getClaims(token)
                                .getSubject();
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          username,
          null);
      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext()
                           .setAuthentication(authToken);
    }
    filterChain.doFilter(request, response);
  }
}
