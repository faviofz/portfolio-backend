package com.example.portfolio.dto;

import com.example.portfolio.entity.Technology;
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

  public TechnologyDto(Technology technology) {
    this.id = technology.getId();
    this.name = technology.getName();
  }
}
