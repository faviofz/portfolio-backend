package com.example.portfolio.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PERSONS")
@Data
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

    @Column(name = "URL_PROFILE_IMAGE", nullable = true, length = 100)
    private String url_profile_image;

    @Column(name = "URL_BANNER_IMAGE", nullable = true, length = 100)
    private String url_banner_image;

    @Column(name = "ABOUT_ME", nullable = true, length = 400)
    private String about_me;

//    @JsonManagedReference
    @JsonIgnore
    @OneToMany(/* mappedBy = "person", */ cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<Experience> experiences;

//    @JsonManagedReference
    @OneToMany(/* mappedBy = "person", */ cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<Education> educations;

//    @JsonManagedReference
    @OneToMany(/* mappedBy = "person", */ cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<Project> projects;

}
