package com.example.portfolio.service;

import com.example.portfolio.dto.PersonDto;
import com.example.portfolio.entity.Person;
import com.example.portfolio.exceptions.PersonNotFoundException;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.serviceInterfaces.IPersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {
  
  @Autowired
  private PersonRepository personRepository;
  
  
  @Override
  public List<PersonDto> getAllPersons() {
    return this.personRepository.findAll()
        .stream()
        .map(p -> new PersonDto(
            p.getId(),
            p.getFirstName(),
            p.getLastName(),
            p.getUrlProfileImage(),
            p.getUrlBannerImage(),
            p.getAboutMe()))
        .toList();
  }
  
  @Override
  public void savePerson(PersonDto personDto) {
    this.personRepository.save(new Person(
        personDto.getFirst_name(),
        personDto.getLast_name(),
        personDto.getUrl_profile_image(),
        personDto.getUrl_banner_image(),
        personDto.getAbout_me()));
  }
  
  @Override
  public void deletePerson(Long id) {
    this.personRepository.deleteById(id);
  }
  
  @Override
  public PersonDto findPersonById(Long id) {
    Person person = this.personRepository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException(id));
    System.out.println(person.getExperiences());
    return new PersonDto(person.getId(), person.getFirstName(), person.getLastName(),
                         person.getUrlProfileImage(), person.getUrlBannerImage(),
                         person.getAboutMe());
  }
  
  public void updatePerson(Long id, PersonDto personDto) {
    Person person =
        this.personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    
    person.setFirstName(personDto.getFirst_name());
    person.setLastName(personDto.getLast_name());
    person.setUrlProfileImage(personDto.getUrl_profile_image());
    person.setUrlBannerImage(personDto.getUrl_banner_image());
    person.setAboutMe(personDto.getAbout_me());
    
    this.personRepository.save(person);
  }
}
