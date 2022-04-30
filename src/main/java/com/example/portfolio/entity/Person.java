package com.example.portfolio.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PERSONS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "FIRST_NAME", nullable = false, length = 20)
  private String first_name = "first_name";

  @Column(name = "LAST_NAME", nullable = false, length = 20)
  private String last_name;

  @Column(name = "URL_PROFILE_IMAGE", length = 100)
  private String url_profile_image;

  @Column(name = "URL_BANNER_IMAGE", length = 100)
  private String url_banner_image;

  @Column(name = "ABOUT_ME", length = 400)
  private String about_me;

  //    @JsonManagedReference
  @ToString.Exclude
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "person_id")
  private List<Experience> experiences = new java.util.ArrayList<>();

  //    @JsonManagedReference
  @ToString.Exclude
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "person_id")
  private List<Education> educations = new java.util.ArrayList<>();

  //    @JsonManagedReference
  @ToString.Exclude
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "person_id")
  private List<Project> projects = new java.util.ArrayList<>();

  public Person(String first_name, String last_name, String url_profile_image,
      String url_banner_image,
      String about_me) {
    super();
    this.first_name = first_name;
    this.last_name = last_name;
    this.url_profile_image = url_profile_image;
    this.url_banner_image = url_banner_image;
    this.about_me = about_me;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Person)) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id) && Objects.equals(first_name, person.first_name)
        && Objects.equals(last_name, person.last_name) && Objects.equals(url_profile_image,
        person.url_profile_image) && Objects.equals(url_banner_image, person.url_banner_image)
        && Objects.equals(about_me, person.about_me);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, first_name, last_name, url_profile_image, url_banner_image, about_me);
  }
}


