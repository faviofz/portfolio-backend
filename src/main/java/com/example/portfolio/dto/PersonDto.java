package com.example.portfolio.dto;

import com.example.portfolio.entity.Person;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Serializable {

  private String first_name;

  private String last_name;

  private String url_profile_image;

  private String url_banner_image;

  private String about_me;

  public PersonDto(Person person) {
    this.first_name = person.getFirst_name();
    this.last_name = person.getLast_name();
    this.url_profile_image = person.getUrl_profile_image();
    this.url_banner_image = person.getUrl_banner_image();
    this.about_me = person.getAbout_me();
  }
}
