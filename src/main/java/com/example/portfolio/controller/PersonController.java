package com.example.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.portfolio.dto.PersonDTO;
import com.example.portfolio.entity.Person;
import com.example.portfolio.service.PersonService;

@RequestMapping("/persons")
@RestController
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping()
    public List<Person> getAllPersons() {
        return this.personService.getAllPersons();
    }
    
    @GetMapping("/{id}")
    public PersonDTO findPersonById(@PathVariable Long id) {
        Person person = this.personService.findPersonById(id);
        return new PersonDTO(
                person.getFirst_name(),
                person.getLast_name(),
                person.getAbout_me(),
                person.getUrl_banner_image(),
                person.getUrl_profile_image(),
                person.getExperiences(),
                person.getEducations(),
                person.getProyects());
    }
    
    @PostMapping("/create")
    public void createPerson(@RequestBody PersonDTO dto) {
        Person person = new Person();
        person.setFirst_name(dto.getFirst_name());
        person.setLast_name(dto.getLast_name());
        person.setAbout_me(dto.getAbout_me());
        person.setUrl_banner_image(dto.getUrl_banner_image());
        person.setUrl_profile_image(dto.getUrl_profile_image());
        person.setExperiences(dto.getExperiences());
        person.setEducations(dto.getEducations());
        person.setProyects(dto.getProyects());
        this.personService.savePerson(person);
    }
    
    @PutMapping("/edit/{id}")
    public void editPerson(@RequestBody PersonDTO dto, @PathVariable Long id) {
        Person person = this.personService.findPersonById(id);
        person.setFirst_name(dto.getFirst_name());
        person.setLast_name(dto.getLast_name());
        person.setAbout_me(dto.getAbout_me());
        person.setUrl_banner_image(dto.getUrl_banner_image());
        person.setUrl_profile_image(dto.getUrl_profile_image());
        person.setExperiences(dto.getExperiences());
        person.setEducations(dto.getEducations());
        person.setProyects(dto.getProyects());
        this.personService.savePerson(person);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable Long id) {
        this.personService.deletePerson(id);
    }
}
