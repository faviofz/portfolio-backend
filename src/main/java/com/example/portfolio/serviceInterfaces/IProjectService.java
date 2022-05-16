package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.dto.ProjectDto;
import java.util.List;

public interface IProjectService {
  
  List<ProjectDto> getAllProjects();
  
  void saveProject(ProjectDto projectDto);
  
  void deleteProject(Long id);
  
  ProjectDto findProjectById(Long id);
}
