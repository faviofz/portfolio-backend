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
  private Date started_year;

  @Column(name = "ENDED_YEAR")
  @Temporal(value = TemporalType.DATE)
  private Date ended_year;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  private Person person;

  public Education(String title, String description, Date started_year, Date ended_year) {
    this.title = title;
    this.description = description;
    this.started_year = started_year;
    this.ended_year = ended_year;
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
        && Objects.equals(description, education.description) && Objects.equals(started_year,
        education.started_year) && Objects.equals(ended_year, education.ended_year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, started_year, ended_year);
  }
}
