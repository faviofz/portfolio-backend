package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.dto.PersonDto;
import java.util.List;

public interface IPersonService {
  
  List<PersonDto> getAllPersons();
  
  void savePerson(PersonDto personDto);
  
  void deletePerson(Long id);
  
  PersonDto findPersonById(Long id);
  
}
