package com.example.portfolio.service;//package com.example.portfolio.service;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.then;
//
//import com.example.portfolio.entity.Person;
//import com.example.portfolio.repository.PersonRepository;
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class PersonServiceTest {
//
//  @Mock
//  private PersonRepository personRepository;
//
//  @InjectMocks
//  PersonService personService;
//
//  @BeforeEach
//  void setUp() {
//  }
//
//  @AfterEach
//  void tearDown() {
//  }
//
//  @Test
//  void contextLoads() {
//    assertThat(this.personService).isNotNull();
//  }
//
//  @Test
//  void getAllPersons() {
//    given(personRepository.findAll())
//        .willReturn(List.of(new Person()));
//
//    List<Person> personList = personService.getAllPersons();
//
//    assertThat(personList).isNotEmpty();
//  }
//
//  @Test
//  void savePerson() {
//    given(personRepository.save(any(Person.class)))
//        .willReturn(new Person());
//
//    this.personService.savePerson(new Person());
//
//    then(personRepository).should()
//        .save(any(Person.class));
//  }
//
//  @Test
//  void deletePerson() {
//    Person person = new Person(1L, "Favio", "Fernandez", null, null, null, null,
//                               null, null);
//
//    this.personService.deletePerson(person.getId());
//
//    then(this.personRepository).should().deleteById(person.getId());
//  }
//
//  @Test
//  void findPersonById() {
//    given(this.personRepository.findById(anyLong())).willReturn(Optional.of(new Person()));
//
//    Person person = this.personService.findPersonById(1L);
//
//    then(this.personRepository).should().findById(1L);
//    assertThat(person).isNotNull();
//  }
//}