package com.example.portfolio.dto;

import java.util.List;

import com.example.portfolio.entity.Education;
import com.example.portfolio.entity.Experience;
import com.example.portfolio.entity.Proyect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PersonDTO {
    
    private String first_name;
    
    private String last_name;
    
    private String url_profile_image;

    private String url_banner_image;

    private String about_me;
    
    private List<Experience> experiences;
    
    private List<Education> educations;
  
    private List<Proyect> proyects;

}
