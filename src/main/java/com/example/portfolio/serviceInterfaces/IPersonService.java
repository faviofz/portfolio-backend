package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.entity.Person;
import java.util.List;

public interface IPersonService {

  List<Person> getAllPersons();

  void savePerson(Person person);

  void deletePerson(Long id);

  Person findPersonById(Long id);

}
