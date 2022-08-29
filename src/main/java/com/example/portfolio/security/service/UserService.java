package com.example.portfolio.security.service;

import com.example.portfolio.security.entity.User;
import com.example.portfolio.security.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    User user;
    List<GrantedAuthority> roles = new ArrayList<>();
    
    user = this.userRepository.findByUsername(username)
                              .orElseThrow(() -> new UsernameNotFoundException(username));
    roles.add(new SimpleGrantedAuthority("ADMIN"));
    
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                  user.getPassword(),
                                                                  roles);
  }
  
}
