package com.example.portfolio.service;

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
  public List<Person> getAllPersons() {
    return this.personRepository.findAll();
  }

  @Override
  public void savePerson(Person per) {
    this.personRepository.save(per);
  }

  @Override
  public void deletePerson(Long id) {
    this.personRepository.deleteById(id);
  }

  @Override
  public Person findPersonById(Long id) {
    return this.personRepository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException(id));
  }

}
