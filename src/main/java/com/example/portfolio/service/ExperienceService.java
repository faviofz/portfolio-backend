package com.example.portfolio.service;

import com.example.portfolio.dto.ExperienceDto;
import com.example.portfolio.entity.Experience;
import com.example.portfolio.entity.Person;
import com.example.portfolio.exceptions.PersonNotFoundException;
import com.example.portfolio.repository.ExperienceRepository;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.serviceInterfaces.IExperienceService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService implements IExperienceService {
  
  @Autowired
  private ExperienceRepository experienceRepository;
  
  @Autowired
  private PersonRepository personRepository;
  
  @Override
  public List<ExperienceDto> getAllExperiences() {
    return this.experienceRepository.findAll()
        .stream()
        .map(e -> new ExperienceDto(
            e.getId(),
            e.getTitle(),
            e.getDescription(),
            e.getStartedYear(),
            e.getEndedYear()))
        .toList();
  }
  
  @Override
  public void saveExperience(ExperienceDto experienceDto) {
    Long person_id = experienceDto.getPerson_id();
    Person person = this.personRepository.findById(person_id)
        .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    this.experienceRepository.save(new Experience(
        experienceDto.getTitle(),
        experienceDto.getDescription(),
        experienceDto.getStarted_year(),
        experienceDto.getEnded_year(),
        person));
  }
  
  @Override
  public void deleteExperience(Long id) {
    this.experienceRepository.deleteById(id);
  }
  
  @Override
  public ExperienceDto findExperienceById(Long id) {
    Experience experience = this.experienceRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(
            "Experience no encontrada con el ID: " + id));
    return new ExperienceDto(
        experience.getId(),
        experience.getTitle(),
        experience.getDescription(),
        experience.getStartedYear(),
        experience.getEndedYear());
  }
  
  public List<ExperienceDto> findExperiencesByPersonId(Long PersonId) {
    return this.experienceRepository.findByPersonId(PersonId).stream()
        .map(e -> new ExperienceDto(
            e.getId(),
            e.getTitle(),
            e.getDescription(),
            e.getStartedYear(),
            e.getEndedYear())).toList();
  }
  
  public void updateExperience(Long id, ExperienceDto experienceDto) {
    Experience experience = this.experienceRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(
            "Experience no encontrada con el ID: " + id));
    
    experience.setTitle(experienceDto.getTitle());
    experience.setDescription(experienceDto.getDescription());
    experience.setStartedYear(experienceDto.getStarted_year());
    experience.setEndedYear(experienceDto.getEnded_year());
    
    this.experienceRepository.save(experience);
  }
  
}
