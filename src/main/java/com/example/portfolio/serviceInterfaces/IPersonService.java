package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.dto.EducationDto;
import com.example.portfolio.dto.ExperienceDto;
import com.example.portfolio.dto.PersonDto;
import com.example.portfolio.dto.ProjectDto;
import com.example.portfolio.dto.TechnologyDto;
import java.util.List;

public interface IPersonService {
  
  List<PersonDto> getAllPersons();
  
  void savePerson(PersonDto personDto);
  
  void deletePerson(Long id);
  
  PersonDto findPersonById(Long id);
  
  void updatePerson(Long id, PersonDto personDto);
  
  List<ExperienceDto> getExperiencesOfPerson(Long id);
  
  void saveExperience(Long person_id, ExperienceDto experienceDto);
  
  void updateExperience(Long person_id, Long experience_id, ExperienceDto experienceDto);
  
  void deleteExperience(Long person_id, Long experience_id);
  
  List<EducationDto> getEducationsOfPerson(Long id);
  
  void saveEducation(Long person_id, EducationDto educationDto);
  
  void updateEducation(Long person_id, Long education_id, EducationDto educationDto);
  
  void deleteEducation(Long person_id, Long education_id);
  
  List<ProjectDto> getProjectsOfPerson(Long id);
  
  void saveProject(Long person_id, ProjectDto projectDto);
  
  void updateProject(Long person_id, Long project_id, ProjectDto projectDto);
  
  void deleteProject(Long person_id, Long project_id);
  
  List<TechnologyDto> getTechnologiesOfPerson(Long id);
  
  void saveTechnology(Long person_id, TechnologyDto technologyDto);
  
  void updateTechnology(Long person_id, Long technology_id, TechnologyDto technologyDto);
  
  void deleteTechnology(Long person_id, Long technology_id);
}