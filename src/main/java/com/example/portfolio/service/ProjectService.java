package com.example.portfolio.service;

import com.example.portfolio.dto.ProjectDto;
import com.example.portfolio.entity.Person;
import com.example.portfolio.entity.Project;
import com.example.portfolio.exceptions.PersonNotFoundException;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.repository.ProjectRepository;
import com.example.portfolio.serviceInterfaces.IProjectService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {
  
  @Autowired
  private ProjectRepository projectRepository;
  
  @Autowired
  private PersonRepository personRepository;
  
  @Override
  public List<ProjectDto> getAllProjects() {
    return this.projectRepository.findAll().stream()
        .map(p -> new ProjectDto(p.getId(), p.getTitle(), p.getDescription())).toList();
  }
  
  @Override
  public void saveProject(ProjectDto projectDto) {
    Long person_id = projectDto.getPerson_id();
    Person person = this.personRepository.findById(person_id)
        .orElseThrow(() -> new PersonNotFoundException(person_id));
    
    this.projectRepository.save(new Project(
        projectDto.getTitle(),
        projectDto.getDescription(),
        person));
  }
  
  @Override
  public void deleteProject(Long id) {
    this.projectRepository.deleteById(id);
  }
  
  @Override
  public ProjectDto findProjectById(Long id) {
    Project project = this.projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return new ProjectDto(project.getId(), project.getTitle(), project.getDescription());
  }
  
  public List<ProjectDto> findProjectsByPersonId(Long person_id) {
    return this.projectRepository.findByPersonId(person_id).stream()
        .map(p -> new ProjectDto(p.getId(), p.getTitle(), p.getDescription())).toList();
  }
  
  public void updateProject(Long id, ProjectDto projectDto) {
    Project project = this.projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    project.setTitle(projectDto.getTitle());
    project.setDescription(projectDto.getDescription());
    this.projectRepository.save(project);
  }
}
