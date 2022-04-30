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
@Table(name = "TECNOLOGIES")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Technology {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME", length = 50)
  private String name;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  private Person person;

  public Technology(String name, Person person) {
    this.name = name;
    this.person = person;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Technology)) {
      return false;
    }
    Technology tecnology = (Technology) o;
    return Objects.equals(id, tecnology.id) && Objects.equals(name, tecnology.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
