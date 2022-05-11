package com.example.portfolio.controller;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.example.portfolio.dto.ExperienceDto;
import com.example.portfolio.entity.Experience;
import com.example.portfolio.entity.Person;
import com.example.portfolio.service.ExperienceService;
import com.example.portfolio.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ExperienceController.class)
class ExperienceControllerTest {
  
  @Autowired
  MockMvc mockMvc;
  
  @MockBean
  ExperienceService experienceService;
  
  @MockBean
  PersonService personService;
  
  @Autowired
  ObjectMapper mapper = new ObjectMapper();
  
  Person person;
  Experience experience;
  
  @BeforeEach
  void setUp() throws ParseException {
    this.person = new Person();
    person.setId(1L);
    person.setFirst_name("firstName");
    person.setLast_name("lastName");
    person.setUrl_profile_image("/img/profile.jpg");
    person.setUrl_banner_image("/img/banner.jpg");
    person.setAbout_me("aboutMe");
    
    this.experience = new Experience(
        1L,
        "title",
        "description",
        new SimpleDateFormat("yyyy").parse("2020"),
        new SimpleDateFormat("yyyy").parse("2022"),
        person);
    
    person.getExperiences().add(this.experience);
  }
  
  @Test
  void contextLoads() {
    assertThat(this.experienceService).isNotNull();
  }
  
  @Test
  void getAllExperiencesOfPersonTest() throws Exception {
    given(this.experienceService.findExperiencesByPersonId(1L)).willReturn(List.of(this.experience));
    
    this.mockMvc.perform(get("/experiences?person_id={id}", 1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.*").isArray())
        .andExpect(jsonPath("$[0].title").value("title"))
        .andDo(print());
  }
  
  @Test
  void createExperienceOfPersonTest() throws Exception {
    ExperienceDto experienceDto = new ExperienceDto(
        null,
        "newTitle",
        "newDescription",
        new SimpleDateFormat("yyyy").parse("2020"),
        new SimpleDateFormat("yyyy").parse("2022"),
        1L);
    
    given(this.personService.findPersonById(experienceDto.getPerson_id())).willReturn(this.person);
    
    this.mockMvc.perform(post("/experiences")
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(mapper.writeValueAsString(experienceDto)))
        .andExpect(status().isCreated())
        .andDo(print());
    
    then(this.personService).should().findPersonById(experienceDto.getPerson_id());
    then(this.experienceService).should().saveExperience(
        new Experience(
            experienceDto.getTitle(),
            experienceDto.getDescription(),
            experienceDto.getStarted_year(),
            experienceDto.getEnded_year(),
            this.person
        ));
  }
  
  @Test
  void editExperienceOfPersonTest() throws Exception {
    ExperienceDto experienceDto = new ExperienceDto(
        null,
        "replaceTitle",
        "replaceDescription",
        new SimpleDateFormat("yyyy").parse("2020"),
        new SimpleDateFormat("yyyy").parse("2022"),
        null);
    
    given(this.experienceService.findExperienceById(1L)).willReturn(this.experience);
    
    this.mockMvc.perform(put("/experiences/{id}", 1L)
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(mapper.writeValueAsString(experienceDto)))
        .andExpect(status().isNoContent())
        .andDo(print());
    
    then(this.experienceService).should().saveExperience(this.experience);
    
    assertThat(this.experience.getTitle()).isEqualTo(experienceDto.getTitle());
    assertThat(this.experience.getDescription()).isEqualTo(experienceDto.getDescription());
    assertThat(this.experience.getStarted_year()).isEqualTo(experienceDto.getStarted_year());
    assertThat(this.experience.getEnded_year()).isEqualTo(experienceDto.getEnded_year());
  }
  
  @Test
  void deleteExperienceOfPersonTest() throws Exception {
    
    this.mockMvc.perform(delete("/experiences/{id}", anyLong()))
        .andExpect(status().isNoContent())
        .andDo(print());
    
    then(this.experienceService).should().deleteExperience(anyLong());
    
  }
}