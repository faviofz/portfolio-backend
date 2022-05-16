package com.example.portfolio.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Serializable {
  
  private Long id;
  
  private String first_name;
  
  private String last_name;
  
  private String url_profile_image;
  
  private String url_banner_image;
  
  private String about_me;
  
  public PersonDto(
      String first_name, String last_name, String url_profile_image, String url_banner_image,
      String about_me
  ) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.url_profile_image = url_profile_image;
    this.url_banner_image = url_banner_image;
    this.about_me = about_me;
  }
}
