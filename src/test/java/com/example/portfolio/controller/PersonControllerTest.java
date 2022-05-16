//package com.example.portfolio.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.then;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.example.portfolio.dto.PersonDto;
//import com.example.portfolio.entity.Person;
//import com.example.portfolio.exceptions.PersonNotFoundException;
//import com.example.portfolio.service.PersonService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.List;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//@WebMvcTest(PersonController.class)
//class PersonControllerTest {
//
//  @Autowired
//  MockMvc mockMvc;
//
//  @MockBean
//  PersonService personService;
//
//  Person person;
//
//  @Autowired
//  ObjectMapper mapper = new ObjectMapper();
//
//  @BeforeEach
//  void setUp() {
//    this.person = new Person();
//    person.setId(1L);
//    person.setFirst_name("firstName");
//    person.setLast_name("lastName");
//    person.setUrl_profile_image("/img/profile.jpg");
//    person.setUrl_banner_image("/img/banner.jpg");
//    person.setAbout_me("aboutMe");
//  }
//
//  @AfterEach
//  void tearDown() {
//  }
//
//  @Test
//  void contextLoads() {
//    assertThat(personService).isNotNull();
//  }
//
//  @Test
//  void getAllPersons() throws Exception {
//    given(this.personService.getAllPersons()).willReturn(List.of(this.person));
//
//    mockMvc.perform(get("/persons")).andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.*").isArray())
//        .andExpect(jsonPath("$[0].first_name").value("firstName"));
//  }
//
//  @Test
//  void getPerson_shouldSuccess() throws Exception {
//    given(this.personService.findPersonById(1L)).willReturn(this.person);
//
//    mockMvc.perform(get("/persons/1"))
//        .andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.*").isNotEmpty())
//        .andDo(print());
//  }
//
//  @Test
//  void getPerson_shouldFail() throws Exception {
//    given(this.personService.findPersonById(
//        anyLong())).willThrow(PersonNotFoundException.class);
//
//    mockMvc.perform(get("/persons/1")).andDo(print())
//        .andExpect(status().isNotFound())
//        .andExpect(result -> assertThat(result.getResolvedException())
//            .isInstanceOf(PersonNotFoundException.class));
//
//    then(this.personService).should().findPersonById(anyLong());
//  }
//
//  @Test
//  void createPerson() throws Exception {
//    mockMvc.perform(post("/persons")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(new PersonDto())))
//        .andExpect(status().isCreated())
//        .andDo(print());
//
//    then(this.personService).should().savePerson(any(Person.class));
//  }
//
//  @Test
//  void editPerson() throws Exception {
//    given(this.personService.findPersonById(1L)).willReturn(this.person);
//
//    mockMvc.perform(put("/persons/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(new PersonDto())))
//        .andExpect(status().isOk())
//        .andDo(print());
//
//    then(this.personService).should().savePerson(any(Person.class));
//  }
//
//  @Test
//  void deletePerson() throws Exception {
//    mockMvc.perform(delete("/persons/{id}", 1L))
//        .andExpect(status().isNoContent())
//        .andDo(print());
//
//    then(this.personService).should().deletePerson(1L);
//  }
//
//}