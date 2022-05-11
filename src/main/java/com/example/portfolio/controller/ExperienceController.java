package com.example.portfolio.controller;

import com.example.portfolio.dto.ExperienceDto;
import com.example.portfolio.entity.Experience;
import com.example.portfolio.entity.Person;
import com.example.portfolio.service.ExperienceService;
import com.example.portfolio.service.PersonService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExperienceController.EXPERIENCES)
public class ExperienceController {
  
  public static final String EXPERIENCES = "/experiences";
  public static final String ID = "/{id}";
  
  @Autowired
  private ExperienceService experienceService;
  
  @Autowired
  private PersonService personService;
  
  @GetMapping
  public ResponseEntity<List<ExperienceDto>> getAllExperiencesOfPerson(
      @RequestParam Long person_id
  ) {
    List<Experience> experienceList = this.experienceService.findExperiencesByPersonId(person_id);
    List<ExperienceDto> experienceDtoList = experienceList.stream().map(e -> new ExperienceDto(
        e.getId(),
        e.getTitle(),
        e.getDescription(),
        e.getStarted_year(),
        e.getEnded_year(),
        e.getPerson().getId())).toList();
    return ResponseEntity.ok(experienceDtoList);
  }
  
  @PostMapping
  public ResponseEntity<?> createExperienceOfPerson(
      @RequestBody ExperienceDto experienceDto
  ) {
    Person person = this.personService.findPersonById(experienceDto.getPerson_id());
    
    this.experienceService.saveExperience(new Experience(
        experienceDto.getTitle(),
        experienceDto.getDescription(),
        experienceDto.getStarted_year(),
        experienceDto.getEnded_year(),
        person));
    
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  
  @PutMapping(ID)
  public ResponseEntity<?> editExperienceOfPerson(
      @PathVariable Long id, @RequestBody ExperienceDto experienceDto
  ) {
    Experience experience = this.experienceService.findExperienceById(id);
    
    experience.setTitle(experienceDto.getTitle());
    experience.setDescription(experienceDto.getDescription());
    experience.setStarted_year(experienceDto.getStarted_year());
    experience.setEnded_year(experienceDto.getEnded_year());
    
    this.experienceService.saveExperience(experience);
    
    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping(ID)
  public ResponseEntity<?> deletePersonExperience(@PathVariable Long id) {
    this.experienceService.deleteExperience(id);
    
    return ResponseEntity.noContent().build();
  }
  
}
