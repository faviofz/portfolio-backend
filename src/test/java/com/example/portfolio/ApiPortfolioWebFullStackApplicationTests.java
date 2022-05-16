package com.example.portfolio;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.portfolio.security.MyUser;
import com.example.portfolio.security.UserRepository;
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
    MyUser user = new MyUser(1L, "faviowow", bCryptPasswordEncoder.encode("123"));
    MyUser retorno = userRepository.save(user);
    
    assertThat(retorno.getPassword()).isEqualTo(user.getPassword());
  }
  
}
