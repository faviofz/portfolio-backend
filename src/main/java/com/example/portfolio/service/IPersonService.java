package com.example.portfolio.service;

import java.util.List;

import com.example.portfolio.entity.Person;

public interface IPersonService {
    
    public List<Person> getAllPersons();
    
    public void savePerson(Person per);
    
    public void deletePerson(Long id);
    
    public Person findPersonById(Long id);

}
