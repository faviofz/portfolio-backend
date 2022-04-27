package com.example.portfolio.serviceInterfaces;

import java.util.List;

import com.example.portfolio.entity.Person;

public interface IPersonService {
    
    public List<Person> getAllPersons();
    
    public void savePerson(Person person);
    
    public void deletePerson(Long id);
    
    public Person findPersonById(Long id);

}
