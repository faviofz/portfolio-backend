package com.example.portfolio.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EDUCATIONS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Education {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  
  @Column(name = "TITLE", nullable = false, length = 50)
  private String title;
  
  @Column(name = "DESCRIPTION", length = 200)
  private String description;
  
  @Column(name = "STARTED_YEAR")
  @Temporal(value = TemporalType.DATE)
  private Date startedYear;
  
  @Column(name = "ENDED_YEAR")
  @Temporal(value = TemporalType.DATE)
  private Date endedYear;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @ToString.Exclude
  private Person person;
  
  public Education(String title, String description, Date startedYear, Date endedYear) {
    this.title = title;
    this.description = description;
    this.startedYear = startedYear;
    this.endedYear = endedYear;
  }
  
  public Education(
      String title, String description, Date startedYear, Date endedYear, Person person
  ) {
    this.title = title;
    this.description = description;
    this.startedYear = startedYear;
    this.endedYear = endedYear;
    this.person = person;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Education)) {
      return false;
    }
    Education education = (Education) o;
    return Objects.equals(id, education.id) && Objects.equals(title, education.title)
        && Objects.equals(description, education.description) && Objects.equals(
        startedYear,
        education.startedYear) && Objects.equals(endedYear, education.endedYear);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, startedYear, endedYear);
  }
}
