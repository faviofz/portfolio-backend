package com.example.portfolio.dto;

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
  
  private Long person_id;
  
  public ProjectDto(Long id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }
}
