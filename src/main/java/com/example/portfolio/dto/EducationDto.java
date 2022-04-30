package com.example.portfolio.dto;

import com.example.portfolio.entity.Education;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto implements Serializable {

  private Long id;

  private String title;

  private String description;

  private Date started_year;

  private Date ended_year;

  public EducationDto(Education education) {
    this.id = education.getId();
    this.title = education.getTitle();
    this.description = education.getDescription();
    this.started_year = education.getStarted_year();
    this.ended_year = education.getEnded_year();
  }
}
