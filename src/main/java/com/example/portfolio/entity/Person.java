package com.example.portfolio.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  private String firstName;
  
  @Column(name = "LAST_NAME", nullable = false, length = 20)
  private String lastName;
  
  @Column(name = "URL_PROFILE_IMAGE", length = 100)
  private String urlProfileImage;
  
  @Column(name = "URL_BANNER_IMAGE", length = 100)
  private String urlBannerImage;
  
  @Column(name = "ABOUT_ME", length = 400)
  private String aboutMe;
  
  @ToString.Exclude
  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Experience> experiences = new ArrayList<>();
  
  @ToString.Exclude
  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Education> educations = new ArrayList<>();
  
  @ToString.Exclude
  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Project> projects = new ArrayList<>();
  
  public Person(
      String firstName, String lastName, String urlProfileImage,
      String urlBannerImage,
      String aboutMe
  ) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.urlProfileImage = urlProfileImage;
    this.urlBannerImage = urlBannerImage;
    this.aboutMe = aboutMe;
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
    return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName)
        && Objects.equals(lastName, person.lastName) && Objects.equals(
        urlProfileImage,
        person.urlProfileImage) && Objects.equals(urlBannerImage, person.urlBannerImage)
        && Objects.equals(aboutMe, person.aboutMe);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, urlProfileImage, urlBannerImage, aboutMe);
  }
}


