package com.example.portfolio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.portfolio.entity.MyUser;
import com.example.portfolio.repository.UserRepository;
import com.example.portfolio.service.PersonService;

@SpringBootTest
class ApiPortfolioWebFullStackApplicationTests {
	
	@Autowired
	private PersonService personService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Test
	void crearUsuarioTest() throws Exception {
	    MyUser user = new MyUser(1L,"faviowow",bCryptPasswordEncoder.encode("123"));
	    MyUser retorno = userRepository.save(user);
	    
	    assertThat(retorno.getPassword()).isEqualTo(user.getPassword());
	}
	
}
