package com.example.portfolio.security.filter;

import com.example.portfolio.security.entity.User;
import com.example.portfolio.security.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  
  @Autowired
  private JwtService jwtService;
  
  public AuthenticationFilter(AuthenticationManager manager) {
    setAuthenticationManager(manager);
  }
  
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response
  ) throws AuthenticationException {
    try {
      User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
      ArrayList<GrantedAuthority> roles = new ArrayList<>();
      roles.add(new SimpleGrantedAuthority("ADMIN"));
      
      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(user.getUsername(),
                                                  user.getPassword(),
                                                  roles)
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          FilterChain chain, Authentication auth
  ) throws IOException, ServletException {
    UserDetails user = (UserDetails) auth.getPrincipal();
    String token = jwtService.generateToken(user.getUsername());
    response.getWriter()
            .write(token);
  }
}
