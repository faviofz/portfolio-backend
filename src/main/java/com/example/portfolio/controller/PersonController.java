package com.example.portfolio.controller;

import com.example.portfolio.dto.PersonDto;
import com.example.portfolio.entity.Person;
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

  @GetMapping(value = ID)
  public PersonDto findPersonById(@PathVariable Long id) {
    Person person = this.personService.findPersonById(id);
    return new PersonDto(
        person
    );
  }

  @PostMapping(value = CREATE)
  public ResponseEntity<String> createPerson(@RequestBody PersonDto dto) {
    this.personService.savePerson(
        new Person(
            dto.getFirst_name(),
            dto.getLast_name(),
            dto.getUrl_profile_image(),
            dto.getUrl_banner_image(),
            dto.getAbout_me()));
    return new ResponseEntity<>("Persona creada", HttpStatus.CREATED);
  }

  @PutMapping(value = EDIT + ID)
  public void editPerson(@RequestBody PersonDto dto, @PathVariable Long id) {
    Person person = this.personService.findPersonById(id);
    person.setFirst_name(dto.getFirst_name());
    person.setLast_name(dto.getLast_name());
    person.setAbout_me(dto.getAbout_me());
    person.setUrl_banner_image(dto.getUrl_banner_image());
    person.setUrl_profile_image(dto.getUrl_profile_image());
    this.personService.savePerson(person);
  }

  @DeleteMapping(value = DELETE + ID)
  public ResponseEntity<?> deletePerson(@PathVariable Long id) {
    this.personService.deletePerson(id);
    return ResponseEntity.noContent().build();
  }

}
