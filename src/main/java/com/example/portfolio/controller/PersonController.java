package com.example.portfolio.controller;

import com.example.portfolio.dto.EducationDto;
import com.example.portfolio.dto.ExperienceDto;
import com.example.portfolio.dto.PersonDto;
import com.example.portfolio.dto.ProjectDto;
import com.example.portfolio.dto.TechnologyDto;
import com.example.portfolio.serviceInterfaces.IPersonService;
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
  private IPersonService personService;
  
  @GetMapping(ID)
  public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.personService.findPersonById(id));
  }
  
  @GetMapping(ID + EXPERIENCES)
  public ResponseEntity<List<ExperienceDto>> getAllExperiencesOfPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.personService.getExperiencesOfPerson(id));
  }
  
  @GetMapping(ID + EDUCATIONS)
  public ResponseEntity<List<EducationDto>> getAllEducationsOfPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.personService.getEducationsOfPerson(id));
  }
  
  @GetMapping(ID + PROJECTS)
  public ResponseEntity<List<ProjectDto>> getAllProjectsOfPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.personService.getProjectsOfPerson(id));
  }
  
  @GetMapping(ID + TECHNOLOGIES)
  public ResponseEntity<List<TechnologyDto>> getAllTechnologiesOfPerson(@PathVariable Long id) {
    return ResponseEntity.ok(this.personService.getTechnologiesOfPerson(id));
  }
  
  @PostMapping
  public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) {
    this.personService.savePerson(personDto);
    return ResponseEntity.status(HttpStatus.CREATED)
                         .build();
  }
  
  @PostMapping(ID + EXPERIENCES)
  public ResponseEntity<?> createExperienceOfPerson(
      @PathVariable Long id, @RequestBody ExperienceDto experienceDto
  ) {
    this.personService.saveExperience(id, experienceDto);
    
    return ResponseEntity.status(HttpStatus.CREATED)
                         .build();
  }
  
  @PostMapping(ID + EDUCATIONS)
  public ResponseEntity<?> createEducationOfPerson(
      @PathVariable Long id, @RequestBody EducationDto educationDto
  ) {
    this.personService.saveEducation(id, educationDto);
    
    return ResponseEntity.status(HttpStatus.CREATED)
                         .build();
  }
  
  @PostMapping(ID + PROJECTS)
  public ResponseEntity<?> createProjectOfPerson(
      @PathVariable Long id, @RequestBody ProjectDto projectDto
  ) {
    this.personService.saveProject(id, projectDto);
    
    return ResponseEntity.status(HttpStatus.CREATED)
                         .build();
  }
  
  @PostMapping(ID + TECHNOLOGIES)
  public ResponseEntity<?> createTechnologyOfPerson(
      @PathVariable Long id, @RequestBody TechnologyDto technologyDto
  ) {
    this.personService.saveTechnology(id, technologyDto);
    
    return ResponseEntity.status(HttpStatus.CREATED)
                         .build();
  }
  
  @PutMapping(ID)
  public void editPerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
    this.personService.updatePerson(id, personDto);
  }
  
  @PutMapping(ID + EXPERIENCES + "/{experience_id}")
  public ResponseEntity<?> editExperienceOfPerson(@PathVariable Long id,
                                                  @RequestBody ExperienceDto experienceDto,
                                                  @PathVariable Long experience_id
  ) {
    this.personService.updateExperience(id, experience_id, experienceDto);
    
    return ResponseEntity.noContent()
                         .build();
  }
  
  @PutMapping(ID + EDUCATIONS + "/{education_id}")
  public ResponseEntity<?> editEducationOfPerson(@PathVariable Long id,
                                                 @RequestBody EducationDto educationDto,
                                                 @PathVariable Long education_id
  ) {
    this.personService.updateEducation(id, education_id, educationDto);
    
    return ResponseEntity.noContent()
                         .build();
  }
  
  @PutMapping(ID + PROJECTS + "/{project_id}")
  public ResponseEntity<?> editProjectOfPerson(@PathVariable Long id,
                                               @RequestBody ProjectDto projectDto,
                                               @PathVariable Long project_id
  ) {
    this.personService.updateProject(id, project_id, projectDto);
    
    return ResponseEntity.noContent()
                         .build();
  }
  
  @PutMapping(ID + TECHNOLOGIES + "/{technology_id}")
  public ResponseEntity<?> editTechnologyOfPerson(@PathVariable Long id,
                                                  @RequestBody TechnologyDto technologyDto,
                                                  @PathVariable Long technology_id
  ) {
    this.personService.updateTechnology(id, technology_id, technologyDto);
    
    return ResponseEntity.noContent()
                         .build();
  }
  
  @DeleteMapping(ID)
  public ResponseEntity<?> deletePerson(@PathVariable Long id) {
    this.personService.deletePerson(id);
    
    return ResponseEntity.noContent()
                         .build();
  }
  
  @DeleteMapping(ID + EXPERIENCES + "/{experience_id}")
  public ResponseEntity<?> deleteExperienceOfPerson(@PathVariable Long id,
                                                    @PathVariable Long experience_id
  ) {
    this.personService.deleteExperience(id, experience_id);
    
    return ResponseEntity.noContent()
                         .build();
  }
  
  @DeleteMapping(ID + EDUCATIONS + "/{education_id}")
  public ResponseEntity<?> deleteEducationOfPerson(@PathVariable Long id,
                                                   @PathVariable Long education_id
  ) {
    this.personService.deleteEducation(id, education_id);
    
    return ResponseEntity.noContent()
                         .build();
  }
  
  @DeleteMapping(ID + PROJECTS + "/{project_id}")
  public ResponseEntity<?> deleteProjectOfPerson(@PathVariable Long id,
                                                 @PathVariable Long project_id
  ) {
    this.personService.deleteProject(id, project_id);
    
    return ResponseEntity.noContent()
                         .build();
  }
  
  @DeleteMapping(ID + TECHNOLOGIES + "/{technology_id}")
  public ResponseEntity<?> deleteTechnologyOfPerson(@PathVariable Long id,
                                                    @PathVariable Long technology_id
  ) {
    this.personService.deleteTechnology(id, technology_id);
    
    return ResponseEntity.noContent()
                         .build();
  }
  
}


