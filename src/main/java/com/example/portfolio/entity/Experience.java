package com.example.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "EXPERIENCES")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
  
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
  private Date started_year;
  
  @Column(name = "ENDED_YEAR")
  @Temporal(value = TemporalType.DATE)
  private Date ended_year;
  
  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  private Person person;
  
  public Experience(
      String title, String description, Date started_year, Date ended_year,
      Person person
  ) {
    super();
    this.title = title;
    this.description = description;
    this.started_year = started_year;
    this.ended_year = ended_year;
    this.person = person;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Experience)) {
      return false;
    }
    Experience experience = (Experience) o;
    return Objects.equals(id, experience.id) && Objects.equals(title, experience.title)
        && Objects.equals(description, experience.description) && Objects.equals(
        started_year,
        experience.started_year) && Objects.equals(ended_year, experience.ended_year)
        && Objects.equals(person, experience.person);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, started_year, ended_year, person);
  }
}
