package com.example.portfolio.controller;

import com.example.portfolio.dto.PersonDto;
import com.example.portfolio.entity.Person;
import com.example.portfolio.service.PersonService;
import java.util.List;
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

@RestController
@RequestMapping(PersonController.PERSONS)
public class PersonController {
  
  public static final String PERSONS = "/persons";
  
  public static final String ID = "/{id}";
  
  @Autowired
  private PersonService personService;
  
  @GetMapping
  public ResponseEntity<List<Person>> getAllPersons() {
    return ResponseEntity.ok(this.personService.getAllPersons());
  }
  
  @GetMapping(ID)
  public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
    PersonDto personDto = new PersonDto(this.personService.findPersonById(id));
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(personDto);
  }
  
  @PostMapping
  public ResponseEntity<String> createPerson(@RequestBody PersonDto dto) {
    this.personService.savePerson(new Person(
        dto.getFirst_name(),
        dto.getLast_name(),
        dto.getUrl_profile_image(),
        dto.getUrl_banner_image(),
        dto.getAbout_me()));
    return ResponseEntity.status(HttpStatus.CREATED).body("Persona creada");
  }
  
  @PutMapping(ID)
  public void editPerson(@RequestBody PersonDto dto, @PathVariable Long id) {
    Person person = this.personService.findPersonById(id);
    person.setFirst_name(dto.getFirst_name());
    person.setLast_name(dto.getLast_name());
    person.setAbout_me(dto.getAbout_me());
    person.setUrl_banner_image(dto.getUrl_banner_image());
    person.setUrl_profile_image(dto.getUrl_profile_image());
    this.personService.savePerson(person);
  }
  
  @DeleteMapping(ID)
  public ResponseEntity<?> deletePerson(@PathVariable Long id) {
    this.personService.deletePerson(id);
    return ResponseEntity.noContent().build();
  }
  
}


