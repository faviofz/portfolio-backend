package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.entity.Project;
import java.util.List;

public interface IProjectService {

  List<Project> getAllProjects();

  void saveProject(Project project);

  void deleteProject(Long id);

  Project findProjectById(Long id);
}
