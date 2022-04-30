package com.example.portfolio.dto;

import com.example.portfolio.entity.Project;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto implements Serializable {

  private Long id;

  private String title;

  private String description;

  public ProjectDto(Project project) {
    this.id = project.getId();
    this.title = project.getTitle();
    this.description = project.getDescription();
  }
}
