package com.example.portfolio.service;

import com.example.portfolio.entity.Project;
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

  @Override
  public List<Project> getAllProjects() {
    return this.projectRepository.findAll();
  }

  @Override
  public void saveProject(Project project) {
    this.projectRepository.save(project);
  }

  @Override
  public void deleteProject(Long id) {

  }

  @Override
  public Project findProjectById(Long id) {
    return this.projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
