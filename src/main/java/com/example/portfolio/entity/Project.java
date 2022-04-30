package com.example.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PROJECTS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "TITLE", length = 50)
  private String title;

  @Column(name = "DESCRIPTION", length = 200)
  private String description;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  private Person person;

  public Project(String title, String description) {
    this.title = title;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Project)) {
      return false;
    }
    Project project = (Project) o;
    return Objects.equals(id, project.id) && Objects.equals(title, project.title) && Objects.equals(
        description, project.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description);
  }
}
