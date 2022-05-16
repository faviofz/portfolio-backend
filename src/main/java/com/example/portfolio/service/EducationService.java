package com.example.portfolio.service;

import com.example.portfolio.dto.EducationDto;
import com.example.portfolio.entity.Education;
import com.example.portfolio.entity.Person;
import com.example.portfolio.exceptions.PersonNotFoundException;
import com.example.portfolio.repository.EducationRepository;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.serviceInterfaces.IEducationService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService implements IEducationService {
  
  @Autowired
  private EducationRepository educationRepository;
  
  @Autowired
  private PersonRepository personRepository;
  
  @Override
  public List<EducationDto> getAllEducations() {
    return this.educationRepository.findAll()
        .stream()
        .map(e -> new EducationDto(
            e.getId(),
            e.getTitle(),
            e.getDescription(),
            e.getStartedYear(),
            e.getEndedYear()))
        .toList();
  }
  
  @Override
  public void saveEducation(EducationDto educationDto) {
    Long person_id = educationDto.getPerson_id();
    Person person = this.personRepository.findById(person_id)
        .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    this.educationRepository.save(new Education(
        educationDto.getTitle(),
        educationDto.getDescription(),
        educationDto.getStarted_year(),
        educationDto.getEnded_year(),
        person));
  }
  
  @Override
  public void deleteEducation(Long id) {
    this.educationRepository.delete(this.educationRepository.findById(id)
                                        .orElseThrow(EntityNotFoundException::new));
  }
  
  @Override
  public EducationDto findEducationById(Long id) {
    Education education = this.educationRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
    return new EducationDto(
        education.getId(),
        education.getTitle(),
        education.getDescription(),
        education.getStartedYear(),
        education.getEndedYear());
  }
  
  public List<EducationDto> findEducationsByPersonId(Long person_id) {
    return this.educationRepository.findByPersonId(person_id).stream()
        .map(e -> new EducationDto(
            e.getId(),
            e.getTitle(),
            e.getDescription(),
            e.getStartedYear(),
            e.getEndedYear())).toList();
  }
  
  public void updateEducation(Long id, EducationDto educationDto) {
    Education education = this.educationRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
    
    education.setTitle(educationDto.getTitle());
    education.setDescription(educationDto.getDescription());
    education.setStartedYear(educationDto.getStarted_year());
    education.setEndedYear(educationDto.getEnded_year());
    
    this.educationRepository.save(education);
  }
}
