package com.example.portfolio.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.portfolio.dto.PersonDto;
import com.example.portfolio.exceptions.PersonNotFoundException;
import com.example.portfolio.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PersonController.class)
class PersonControllerTest {
  
  @Autowired
  MockMvc mockMvc;
  
  @MockBean
  PersonService personService;
  
  PersonDto personDto;
  
  @Autowired
  ObjectMapper mapper = new ObjectMapper();
  
  @BeforeEach
  void setUp() {
    this.personDto = new PersonDto();
    personDto.setId(1L);
    personDto.setFirstName("firstName");
    personDto.setLastName("lastName");
    personDto.setUrlProfileImage("/img/profile.jpg");
    personDto.setUrlBannerImage("/img/banner.jpg");
    personDto.setAboutMe("aboutMe");
  }
  
  @AfterEach
  void tearDown() {
  }
  
  @Test
  void contextLoads() {
    assertThat(personService).isNotNull();
  }
  
  @Test
  void getPerson_shouldSuccess() throws Exception {
    given(this.personService.findPersonById(1L)).willReturn(this.personDto);
    
    mockMvc.perform(get("/persons/1"))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$").isNotEmpty())
           .andExpect(jsonPath("$.firstName").value("firstName"))
           .andDo(print());
  }
  
  @Test
  void getPerson_shouldFail() throws Exception {
    given(this.personService.findPersonById(anyLong())).willThrow(PersonNotFoundException.class);
    
    mockMvc.perform(get("/persons/1"))
           .andExpect(status().isNotFound())
           .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(
               PersonNotFoundException.class))
           .andDo(print());
    
    then(this.personService).should()
                            .findPersonById(anyLong());
  }
  
  @Test
  void createPerson() throws Exception {
    mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
                                    .content(mapper.writeValueAsString(new PersonDto())))
           .andExpect(status().isCreated())
           .andDo(print());
    
    then(this.personService).should()
                            .savePerson(any(PersonDto.class));
  }
  
  @Test
  void editPerson() throws Exception {
    
    mockMvc.perform(put("/persons/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new PersonDto())))
           .andExpect(status().isNoContent())
           .andDo(print());
    
    then(this.personService).should()
                            .updatePerson(anyLong(), any(PersonDto.class));
  }
  
  @Test
  void deletePerson() throws Exception {
    mockMvc.perform(delete("/persons/{id}", 1L))
           .andExpect(status().isNoContent())
           .andDo(print());
    
    then(this.personService).should()
                            .deletePerson(anyLong());
  }
  
}