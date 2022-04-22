//package com.example.portfolio.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.portfolio.entity.MyUser;
//import com.example.portfolio.repository.UserRepository;
//
//@Service
//public class UserService implements UserDetailsService {
//    
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        MyUser user = this.userRepository.findByUsername(username);
//        
//        List<GrantedAuthority> roles = new ArrayList<>();
//        
//        roles.add(new SimpleGrantedAuthority("ADMIN"));
//        
//        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), roles);
//        
//        return userDetails;
//    }
//
//}
