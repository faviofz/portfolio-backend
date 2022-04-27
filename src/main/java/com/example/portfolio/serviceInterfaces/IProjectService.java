package com.example.portfolio.serviceInterfaces;

import java.util.List;

import com.example.portfolio.entity.Project;

public interface IProjectService {
	
	public List<Project> getAllProjects();

	public void saveProject(Project project);

	public void deleteProject(Long id);

	public Project findProjectById(Long id);
}
