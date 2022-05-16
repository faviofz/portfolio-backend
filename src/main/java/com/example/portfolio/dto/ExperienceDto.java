package com.example.portfolio.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDto implements Serializable {
  
  private Long id;
  
  private String title;
  
  private String description;
  
  private Date started_year;
  
  private Date ended_year;
  
  private Long person_id;
  
  public ExperienceDto(
      Long id, String title, String description, Date started_year, Date ended_year
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.started_year = started_year;
    this.ended_year = ended_year;
  }
}
