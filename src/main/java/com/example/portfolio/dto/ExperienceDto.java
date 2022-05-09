package com.example.portfolio.dto;

import com.example.portfolio.entity.Experience;
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

  public ExperienceDto(Experience experience) {
    this.id = experience.getId();
    this.title = experience.getTitle();
    this.description = experience.getDescription();
    this.started_year = experience.getStarted_year();
    this.ended_year = experience.getEnded_year();
    this.person_id = experience.getPerson().getId();
  }
}
