package com.example.portfolio.service;

import com.example.portfolio.dto.EducationDto;
import com.example.portfolio.dto.ExperienceDto;
import com.example.portfolio.dto.PersonDto;
import com.example.portfolio.dto.ProjectDto;
import com.example.portfolio.dto.TechnologyDto;
import com.example.portfolio.entity.Education;
import com.example.portfolio.entity.Experience;
import com.example.portfolio.entity.Person;
import com.example.portfolio.entity.Project;
import com.example.portfolio.entity.Technology;
import com.example.portfolio.exceptions.PersonNotFoundException;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.serviceInterfaces.IPersonService;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "person")
public class PersonService implements IPersonService {
  
  @Autowired
  private PersonRepository personRepository;
  
  //  @Override
  //  @Cacheable("allpersonscache")
  //  public List<PersonDto> getAllPersons() {
  //    return this.personRepository.findAll()
  //                                .stream()
  //                                .map(p -> new PersonDto(p.getId(),
  //                                                        p.getFirstName(),
  //                                                        p.getLastName(),
  //                                                        p.getUrlProfileImage(),
  //                                                        p.getUrlBannerImage(),
  //                                                        p.getAboutMe()))
  //                                .toList();
  //  }
  
  @Override
  public void savePerson(PersonDto personDto) {
    this.personRepository.save(new Person(personDto.getFirstName(),
                                          personDto.getLastName(),
                                          personDto.getUrlProfileImage(),
                                          personDto.getUrlBannerImage(),
                                          personDto.getAboutMe()));
  }
  
  @Override
  @CacheEvict(key = "#id")
  public void deletePerson(Long id) {
    this.personRepository.deleteById(id);
  }
  
  @Override
  @Cacheable(key = "#id")
  public PersonDto findPersonById(Long id) {
    Person person = this.personRepository.findById(id)
                                         .orElseThrow(() -> new PersonNotFoundException(id));
    return new PersonDto(person.getId(),
                         person.getFirstName(),
                         person.getLastName(),
                         person.getUrlProfileImage(),
                         person.getUrlBannerImage(),
                         person.getAboutMe());
  }
  
  @Override
  @CachePut(key = "#id")
  public void updatePerson(Long id, PersonDto personDto) {
    Person person = this.personRepository.findById(id)
                                         .orElseThrow(() -> new PersonNotFoundException(id));
    
    person.setFirstName(personDto.getFirstName());
    person.setLastName(personDto.getLastName());
    person.setUrlProfileImage(personDto.getUrlProfileImage());
    person.setUrlBannerImage(personDto.getUrlBannerImage());
    person.setAboutMe(personDto.getAboutMe());
    
    this.personRepository.save(person);
  }
  
  @Override
  public List<ExperienceDto> getExperiencesOfPerson(Long id) {
    Person person = this.personRepository.findById(id)
                                         .orElseThrow(() -> new PersonNotFoundException(id));
    
    return person.getExperiences()
                 .stream()
                 .map(e -> new ExperienceDto(e.getId(),
                                             e.getTitle(),
                                             e.getDescription(),
                                             e.getStartedYear(),
                                             e.getEndedYear()))
                 .toList();
  }
  
  @Override
  public void saveExperience(Long person_id, ExperienceDto experienceDto) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    person.getExperiences()
          .add(new Experience(experienceDto.getTitle(),
                              experienceDto.getDescription(),
                              experienceDto.getStarted_year(),
                              experienceDto.getEnded_year(),
                              person));
    
    this.personRepository.save(person);
  }
  
  @Override
  public void updateExperience(Long person_id, Long experience_id, ExperienceDto experienceDto) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    Experience experience = person.getExperiences()
                                  .stream()
                                  .filter(e -> Objects.equals(e.getId(), experience_id))
                                  .findFirst()
                                  .orElseThrow(EntityNotFoundException::new);
    
    experience.setTitle(experienceDto.getTitle());
    experience.setDescription(experienceDto.getDescription());
    experience.setStartedYear(experienceDto.getStarted_year());
    experience.setEndedYear(experienceDto.getEnded_year());
    
    this.personRepository.save(person);
  }
  
  @Override
  public void deleteExperience(Long person_id, Long experience_id) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    person.getExperiences()
          .removeIf(experience -> Objects.equals(experience.getId(), experience_id));
    this.personRepository.save(person);
  }
  
  @Override
  public List<EducationDto> getEducationsOfPerson(Long id) {
    Person person = this.personRepository.findById(id)
                                         .orElseThrow(() -> new PersonNotFoundException(id));
    
    return person.getEducations()
                 .stream()
                 .map(e -> new EducationDto(e.getId(),
                                            e.getTitle(),
                                            e.getDescription(),
                                            e.getStartedYear(),
                                            e.getEndedYear()))
                 .toList();
  }
  
  @Override
  public void saveEducation(Long person_id, EducationDto educationDto) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    person.getEducations()
          .add(new Education(educationDto.getTitle(),
                             educationDto.getDescription(),
                             educationDto.getStarted_year(),
                             educationDto.getEnded_year(),
                             person));
    
    this.personRepository.save(person);
  }
  
  @Override
  public void updateEducation(Long person_id, Long education_id, EducationDto educationDto) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    Education education = person.getEducations()
                                .stream()
                                .filter(e -> Objects.equals(e.getId(), education_id))
                                .findFirst()
                                .orElseThrow(EntityNotFoundException::new);
    
    education.setTitle(educationDto.getTitle());
    education.setDescription(educationDto.getDescription());
    education.setStartedYear(educationDto.getStarted_year());
    education.setEndedYear(educationDto.getEnded_year());
    
    this.personRepository.save(person);
  }
  
  @Override
  public void deleteEducation(Long person_id, Long education_id) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    person.getEducations()
          .removeIf(e -> Objects.equals(e.getId(), education_id));
    
    this.personRepository.save(person);
  }
  
  @Override
  public List<ProjectDto> getProjectsOfPerson(Long id) {
    Person person = this.personRepository.findById(id)
                                         .orElseThrow(() -> new PersonNotFoundException(id));
    
    return person.getProjects()
                 .stream()
                 .map(p -> new ProjectDto(p.getId(), p.getTitle(), p.getDescription()))
                 .toList();
    
  }
  
  @Override
  public void saveProject(Long person_id, ProjectDto projectDto) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    person.getProjects()
          .add(new Project(projectDto.getTitle(), projectDto.getDescription(), person));
    
    this.personRepository.save(person);
  }
  
  @Override
  public void updateProject(Long person_id, Long project_id, ProjectDto projectDto) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    Project project = person.getProjects()
                            .stream()
                            .filter(p -> Objects.equals(p.getId(), project_id))
                            .findFirst()
                            .orElseThrow(EntityNotFoundException::new);
    
    project.setTitle(projectDto.getTitle());
    project.setDescription(projectDto.getDescription());
    
    this.personRepository.save(person);
  }
  
  @Override
  public void deleteProject(Long person_id, Long project_id) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    person.getProjects()
          .removeIf(p -> Objects.equals(p.getId(), project_id));
    
    this.personRepository.save(person);
  }
  
  @Override
  public List<TechnologyDto> getTechnologiesOfPerson(Long id) {
    Person person = this.personRepository.findById(id)
                                         .orElseThrow(() -> new PersonNotFoundException(id));
    
    return person.getTechnologies()
                 .stream()
                 .map(t -> new TechnologyDto(t.getId(), t.getName()))
                 .toList();
    
  }
  
  @Override
  public void saveTechnology(Long person_id, TechnologyDto technologyDto) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    person.getTechnologies()
          .add(new Technology(technologyDto.getName()));
    
    this.personRepository.save(person);
  }
  
  @Override
  public void updateTechnology(Long person_id, Long technology_id, TechnologyDto technologyDto) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    Technology technology = person.getTechnologies()
                                  .stream()
                                  .filter(t -> Objects.equals(t.getId(), technology_id))
                                  .findFirst()
                                  .orElseThrow(EntityNotFoundException::new);
    technology.setName(technologyDto.getName());
    
    this.personRepository.save(person);
  }
  
  @Override
  public void deleteTechnology(Long person_id, Long technology_id) {
    Person person = this.personRepository.findById(person_id)
                                         .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    person.getTechnologies()
          .removeIf(t -> Objects.equals(t.getId(), technology_id));
    
    this.personRepository.save(person);
  }
  
}
