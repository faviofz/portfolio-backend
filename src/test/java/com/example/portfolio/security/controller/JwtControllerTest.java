package com.example.portfolio.security.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.portfolio.security.service.JwtService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(JwtController.class)
class JwtControllerTest {
  
  @Autowired
  MockMvc mockMvc;
  @MockBean
  JwtService jwtService;
  @MockBean
  UserDetailsService userService;
  @MockBean
  BCryptPasswordEncoder encoder;
  
  @Test
  void contextLoads() {
    assertThat(this.mockMvc).isNotNull();
  }
  
  
  @Test
  @WithMockUser(username = "favio", password = "1234")
  void login() throws Exception {
    given(this.userService.loadUserByUsername("favio"))
        .willReturn(new User("favio",
                             "$2a$10$M3CbZc1bNexLOMmeXtd49OxA1YiPxH3sj3eaHHjeVecQTtsERpLae",
                             List.of(
                                 (GrantedAuthority) () -> "ADMIN")));
    
    this.mockMvc.perform(post("/auth"))
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(print());
    
  }
  
}