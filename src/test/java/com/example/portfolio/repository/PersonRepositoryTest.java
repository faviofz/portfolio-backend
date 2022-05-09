package com.example.portfolio.repository;

import com.example.portfolio.entity.Person;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PersonRepositoryTest {
  
  @Autowired
  private PersonRepository personRepository;
  
  @BeforeEach
  void SetUp() {
    Person person = new Person(
        "Fz",
        "811",
        "img/profile.jpg",
        "img/banner.jpg",
        "Sobre mí");
    this.personRepository.save(person);
  }
  
  @Test
  void contextLoads() {
    Assertions.assertThat(this.personRepository).isNotNull();
  }
  
  @Test
  void saveTest() {
    Person person = new Person(
        "Favio",
        "Fernandez",
        "img/profile.jpg",
        "img/banner.jpg",
        "Sobre mí");
    
    Person savedPerson = this.personRepository.save(person);
    
    Assertions.assertThat(savedPerson.getId()).isNotNull();
    Assertions.assertThat(savedPerson.getFirst_name())
        .isEqualTo(person.getFirst_name());
  }
  
  @Test
  void findAllTest() {
    List<Person> personList = this.personRepository.findAll();
    
    Assertions.assertThat(personList).isNotEmpty();
    Assertions.assertThat(personList).isInstanceOf(List.class);
  }
}