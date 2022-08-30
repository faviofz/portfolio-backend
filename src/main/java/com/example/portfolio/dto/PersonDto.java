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
  
  private String firstName;
  
  private String occupation;
  
  private String lastName;
  
  private String urlProfileImage;
  
  private String urlBannerImage;
  
  private String aboutMe;
  
  public PersonDto(
      String firstName, String lastName, String occupation, String urlProfileImage,
      String urlBannerImage,
      String aboutMe
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.occupation = occupation;
    this.urlProfileImage = urlProfileImage;
    this.urlBannerImage = urlBannerImage;
    this.aboutMe = aboutMe;
  }
}
