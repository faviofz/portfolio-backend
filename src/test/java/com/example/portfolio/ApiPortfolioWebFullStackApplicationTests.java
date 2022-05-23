package com.example.portfolio;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.portfolio.security.entity.User;
import com.example.portfolio.security.repository.UserRepository;
import com.example.portfolio.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ApiPortfolioWebFullStackApplicationTests {
  
  @Autowired
  private PersonService personService;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  
  @Test
  void crearUsuarioTest() {
    User user = new User(1L, "faviowow", bCryptPasswordEncoder.encode("123"));
    User retorno = userRepository.save(user);
    
    assertThat(retorno.getPassword()).isEqualTo(user.getPassword());
  }
  
}
