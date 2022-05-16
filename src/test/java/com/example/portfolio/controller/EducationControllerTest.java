//package com.example.portfolio.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.then;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.example.portfolio.dto.EducationDto;
//import com.example.portfolio.entity.Education;
//import com.example.portfolio.entity.Person;
//import com.example.portfolio.service.EducationService;
//import com.example.portfolio.service.PersonService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.text.SimpleDateFormat;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//@WebMvcTest(EducationController.class)
//public class EducationControllerTest {
//
//  @Autowired
//  MockMvc mockMvc;
//
//  @MockBean
//  EducationService educationService;
//
//  @MockBean
//  PersonService personService;
//
//  @Autowired
//  ObjectMapper mapper;
//
//  Person person;
//
//  Education education;
//
//  @BeforeEach
//  void setUp() throws Exception {
//    this.person = new Person();
//    person.setId(1L);
//    person.setFirst_name("firstName");
//    person.setLast_name("lastName");
//    person.setUrl_profile_image("/img/profile.jpg");
//    person.setUrl_banner_image("/img/banner.jpg");
//    person.setAbout_me("aboutMe");
//
//    this.education = new Education(
//        1L,
//        "title",
//        "description",
//        new SimpleDateFormat("yyyy").parse("2020"),
//        new SimpleDateFormat("yyyy").parse("2022"),
//        person);
//
//    person.getEducations().add(this.education);
//  }
//
//  @Test
//  void contextLoads() {
//    assertThat(this.mockMvc).isNotNull();
//    assertThat(this.educationService).isNotNull();
//    assertThat(this.personService).isNotNull();
//    assertThat(this.mapper).isNotNull();
//  }
//
//  @Test
//  void getAllEducationsOfPersonTest() throws Exception {
//    given(this.personService.findPersonById(1L)).willReturn(this.person);
//    given(this.educationService.getAllEducationsByPersonId(1L)).willReturn(List.of(this.education));
//
//    this.mockMvc.perform(get(
//            "/educations?person_id={id}",
//            1L))
//        .andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.*").isArray())
//        .andExpect(jsonPath("$[0]").isNotEmpty())
//        .andExpect(jsonPath("$[0].id").value(1L))
//        .andDo(print());
//
//  }
//
//  @Test
//  void createEducationOfPersonTest() throws Exception {
//    EducationDto educationDto = new EducationDto(
//        null,
//        "newTitle",
//        "newDescription",
//        new SimpleDateFormat("yyyy").parse("2020"),
//        new SimpleDateFormat("yyyy").parse("2022"),
//        this.person.getId()
//    );
//
//    given(this.personService.findPersonById(educationDto.getPerson_id())).willReturn(this.person);
//
//    this.mockMvc.perform(post("/educations")
//                             .contentType(MediaType.APPLICATION_JSON)
//                             .content(mapper.writeValueAsString(educationDto)))
//        .andExpect(status().isCreated())
//        .andDo(print());
//
//    then(this.personService).should().findPersonById(educationDto.getPerson_id());
//    then(this.educationService).should().saveEducation(
//        new Education(
//            educationDto.getTitle(),
//            educationDto.getDescription(),
//            educationDto.getStarted_year(),
//            educationDto.getEnded_year(),
//            this.person
//        ));
//
//  }
//}
