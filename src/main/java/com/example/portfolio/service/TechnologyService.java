package com.example.portfolio.service;

import com.example.portfolio.dto.TechnologyDto;
import com.example.portfolio.entity.Person;
import com.example.portfolio.entity.Technology;
import com.example.portfolio.exceptions.PersonNotFoundException;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.repository.TechnologyRepository;
import com.example.portfolio.serviceInterfaces.ITechnologyService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnologyService implements ITechnologyService {
  
  @Autowired
  private TechnologyRepository technologyRepository;
  
  @Autowired
  private PersonRepository personRepository;
  
  @Override
  public List<TechnologyDto> getAllTechnologies() {
    return this.technologyRepository.findAll()
        .stream()
        .map(t -> new TechnologyDto(
            t.getId(),
            t.getName()
        ))
        .toList();
  }
  
  @Override
  public void saveTechnology(TechnologyDto technologyDto) {
    Long person_id = technologyDto.getPerson_id();
    Person person = this.personRepository.findById(person_id)
        .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    this.technologyRepository.save(new Technology(technologyDto.getName(), person));
  }
  
  @Override
  public void deleteTechnology(Long id) {
    this.technologyRepository.deleteById(id);
  }
  
  @Override
  public TechnologyDto findTechnologyById(Long id) {
    Technology technology =
        this.technologyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    
    return new TechnologyDto(technology.getId(), technology.getName());
  }
  
  public List<TechnologyDto> findTechnologiesByPersonId(Long person_id) {
    return this.technologyRepository.findByPersonId(person_id)
        .stream()
        .map(t -> new TechnologyDto(
            t.getId(),
            t.getName()
        ))
        .toList();
  }
  
  public void updateTechnology(Long id, TechnologyDto technologyDto) {
    Technology technology = this.technologyRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
    
    technology.setName(technologyDto.getName());
    
    this.technologyRepository.save(technology);
  }
}
