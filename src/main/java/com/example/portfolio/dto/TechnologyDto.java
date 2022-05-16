package com.example.portfolio.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDto implements Serializable {
  
  private Long id;
  
  private String name;
  
  private Long person_id;
  
  public TechnologyDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
