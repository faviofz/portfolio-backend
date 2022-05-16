package com.example.portfolio.controller;

import com.example.portfolio.dto.EducationDto;
import com.example.portfolio.dto.ExperienceDto;
import com.example.portfolio.dto.PersonDto;
import com.example.portfolio.dto.ProjectDto;
import com.example.portfolio.dto.TechnologyDto;
import com.example.portfolio.service.EducationService;
import com.example.portfolio.service.ExperienceService;
import com.example.portfolio.service.PersonService;
import com.example.portfolio.service.ProjectService;
import com.example.portfolio.service.TechnologyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PersonController.PERSONS)
public class PersonController {
  
  public static final String PERSONS = "/persons";
  
  public static final String EXPERIENCES = "/experiences";
  
  public static final String EDUCATIONS = "/educations";
  
  public static final String PROJECTS = "/projects";
  
  public static final String TECHNOLOGIES = "/technologies";
  
  public static final String ID = "/{id}";
  
  @Autowired
  private PersonService personService;
  
  @Autowired
  private ExperienceService experienceService;
  
  @Autowired
  private EducationService educationService;
  
  @Autowired
  private ProjectService projectService;
  
  @Autowired
  private TechnologyService technologyService;
  
  
  @GetMapping(ID)
  public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.personService.findPersonById(id));
  }
  
  @GetMapping(ID + EXPERIENCES)
  public ResponseEntity<List<ExperienceDto>> getAllExperiencesOfPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.experienceService.findExperiencesByPersonId(id));
  }
  
  @GetMapping(ID + EDUCATIONS)
  public ResponseEntity<List<EducationDto>> getAllEducationsOfPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.educationService.findEducationsByPersonId(id));
  }
  
  @GetMapping(ID + PROJECTS)
  public ResponseEntity<List<ProjectDto>> getAllProjectsOfPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.projectService.findProjectsByPersonId(id));
  }
  
  @GetMapping(ID + TECHNOLOGIES)
  public ResponseEntity<List<TechnologyDto>> getAllTechnologiesOfPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.technologyService.findTechnologiesByPersonId(id));
  }
  
  @PostMapping
  public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) {
    this.personService.savePerson(personDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  
  @PostMapping(ID + EXPERIENCES)
  public ResponseEntity<?> createExperienceOfPerson(
      @PathVariable Long id, @RequestBody ExperienceDto experienceDto
  ) {
    this.experienceService.saveExperience(experienceDto);
    
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  
  @PostMapping(ID + EDUCATIONS)
  public ResponseEntity<?> createEducationOfPerson(
      @PathVariable Long id, @RequestBody EducationDto educationDto
  ) {
    this.educationService.saveEducation(educationDto);
    
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  
  @PostMapping(ID + PROJECTS)
  public ResponseEntity<?> createProjectOfPerson(
      @PathVariable Long id, @RequestBody ProjectDto projectDto
  ) {
    this.projectService.saveProject(projectDto);
    
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  
  @PostMapping(ID + TECHNOLOGIES)
  public ResponseEntity<?> createTechnologyOfPerson(
      @PathVariable Long id, @RequestBody TechnologyDto technologyDto
  ) {
    this.technologyService.saveTechnology(technologyDto);
    
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  
  @PutMapping(ID)
  public void editPerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
    this.personService.updatePerson(id, personDto);
  }
  
  @PutMapping(ID + EXPERIENCES + "/{experience_id}")
  public ResponseEntity<?> editExperienceOfPerson(
      @RequestBody ExperienceDto experienceDto,
      @PathVariable Long experience_id
  ) {
    this.experienceService.updateExperience(experience_id, experienceDto);
    
    return ResponseEntity.noContent().build();
  }
  
  @PutMapping(ID + EDUCATIONS + "/{education_id}")
  public ResponseEntity<?> editEducationOfPerson(
      @RequestBody EducationDto educationDto,
      @PathVariable Long education_id
  ) {
    this.educationService.updateEducation(education_id, educationDto);
    
    return ResponseEntity.noContent().build();
  }
  
  @PutMapping(ID + PROJECTS + "/{project_id}")
  public ResponseEntity<?> editProjectOfPerson(
      @RequestBody ProjectDto projectDto,
      @PathVariable Long project_id
  ) {
    this.projectService.updateProject(project_id, projectDto);
    
    return ResponseEntity.noContent().build();
  }
  
  @PutMapping(ID + TECHNOLOGIES + "/{technology_id}")
  public ResponseEntity<?> editTechnologyOfPerson(
      @RequestBody TechnologyDto technologyDto,
      @PathVariable Long technology_id
  ) {
    this.technologyService.updateTechnology(technology_id, technologyDto);
    
    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping(ID)
  public ResponseEntity<?> deletePerson(@PathVariable Long id) {
    this.personService.deletePerson(id);
    
    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping(ID + EXPERIENCES + "/{experience_id}")
  public ResponseEntity<?> deleteExperienceOfPerson(@PathVariable Long experience_id) {
    this.experienceService.deleteExperience(experience_id);
    
    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping(ID + EDUCATIONS + "/{education_id}")
  public ResponseEntity<?> deleteEducationOfPerson(@PathVariable Long education_id) {
    this.educationService.deleteEducation(education_id);
    
    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping(ID + PROJECTS + "/{project_id}")
  public ResponseEntity<?> deleteProjectOfPerson(@PathVariable Long project_id) {
    this.projectService.deleteProject(project_id);
    
    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping(ID + TECHNOLOGIES + "/{technology_id}")
  public ResponseEntity<?> deleteTechnologyOfPerson(@PathVariable Long technology_id) {
    this.technologyService.deleteTechnology(technology_id);
    
    return ResponseEntity.noContent().build();
  }
  
}


