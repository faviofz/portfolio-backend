package com.example.portfolio.controller;

import com.example.portfolio.dto.ExperienceDto;
import com.example.portfolio.dto.PersonDto;
import com.example.portfolio.entity.Experience;
import com.example.portfolio.entity.Person;
import com.example.portfolio.service.PersonService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(PersonController.PERSONS)
@RestController
public class PersonController {
  
  public static final String PERSONS = "/persons";
  
  public static final String ID = "/{id}";
  
  public static final String CREATE = "/create";
  
  public static final String EDIT = "/edit";
  
  public static final String DELETE = "/delete";
  
  @Autowired
  private PersonService personService;
  
  @GetMapping
  public List<Person> getAllPersons() {
    return this.personService.getAllPersons();
  }
  
  @GetMapping(ID)
  public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
    PersonDto personDto = new PersonDto(this.personService.findPersonById(id));
    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(personDto);
  }
  
  @PostMapping(CREATE)
  public ResponseEntity<String> createPerson(@RequestBody PersonDto dto) {
    this.personService.savePerson(new Person(
        dto.getFirst_name(),
        dto.getLast_name(),
        dto.getUrl_profile_image(),
        dto.getUrl_banner_image(),
        dto.getAbout_me()));
    return ResponseEntity.status(HttpStatus.CREATED).body("Persona creada");
  }
  
  @PutMapping(EDIT + ID)
  public void editPerson(@RequestBody PersonDto dto, @PathVariable Long id) {
    Person person = this.personService.findPersonById(id);
    person.setFirst_name(dto.getFirst_name());
    person.setLast_name(dto.getLast_name());
    person.setAbout_me(dto.getAbout_me());
    person.setUrl_banner_image(dto.getUrl_banner_image());
    person.setUrl_profile_image(dto.getUrl_profile_image());
    this.personService.savePerson(person);
  }
  
  @DeleteMapping(DELETE + ID)
  public ResponseEntity<?> deletePerson(@PathVariable Long id) {
    this.personService.deletePerson(id);
    return ResponseEntity.noContent().build();
  }
  
  @GetMapping(ID + "/experiences")
  public List<Experience> getAllPersonExperiences(@PathVariable Long id) {
    return this.personService.findPersonById(id).getExperiences();
  }
  
  @PostMapping(ID + "/experiences" + CREATE)
  public ResponseEntity<?> createPersonExperience(
      @PathVariable Long id, @RequestBody ExperienceDto experienceDto
  ) {
    Person person = this.personService.findPersonById(id);
    person.getExperiences().add(new Experience(
        experienceDto.getTitle(),
        experienceDto.getDescription(),
        experienceDto.getStarted_year(),
        experienceDto.getEnded_year(),
        person
    ));
    this.personService.savePerson(person);
    
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  
  @PutMapping(value = ID + "/experiences/{experience_id}" + EDIT)
  public ResponseEntity<?> editPersonExperience(
      @PathVariable Long id,
      @PathVariable Long experience_id,
      @RequestBody ExperienceDto experienceDto
  ) {
    Person person = this.personService.findPersonById(id);
    Experience experience = person.getExperiences().stream()
        .filter(exp -> exp.getId().equals(experience_id))
        .findFirst()
        .orElseThrow(EntityNotFoundException::new);
    experience.setTitle(experienceDto.getTitle());
    experience.setDescription(experienceDto.getDescription());
    experience.setStarted_year(experienceDto.getStarted_year());
    experience.setEnded_year(experienceDto.getEnded_year());
    this.personService.savePerson(person);
    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping(ID + "/experiences/{experience_id}" + DELETE)
  public ResponseEntity<?> deletePersonExperience(
      @PathVariable Long id, @PathVariable Long experience_id
  ) {
    Person person = this.personService.findPersonById(id);
    Experience experience = person.getExperiences().stream()
        .filter(exp -> exp.getId().equals(experience_id))
        .findFirst()
        .orElse(null);
    person.getExperiences().remove(experience);
    
    this.personService.savePerson(person);
    
    return ResponseEntity.noContent().build();
  }
}
