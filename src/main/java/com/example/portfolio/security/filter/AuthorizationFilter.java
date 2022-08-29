package com.example.portfolio.security.filter;

import com.example.portfolio.security.service.JwtService;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
public class AuthorizationFilter extends BasicAuthenticationFilter {
  
  @Autowired
  private JwtService jwtService;
  
  public AuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain chain
  ) throws IOException, ServletException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    
    if (header != null) {
      UsernamePasswordAuthenticationToken authenticationToken = authenticate(request);
      
      SecurityContextHolder.getContext()
                           .setAuthentication(authenticationToken);
    }
    chain.doFilter(request, response);
  }
  
  private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    String token, username;
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      log.info(">>>FILTER JWT");
      token = authorizationHeader.substring("Bearer ".length());
      username = this.jwtService.getClaims(token)
                                .getSubject();
      
      if (username != null && jwtService.isValidToken(token, username)) {
        return new UsernamePasswordAuthenticationToken(
            username,
            null, new ArrayList<>());
      }
    }
    return null;
  }
}
