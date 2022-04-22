package com.example.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EDUCATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @Column(name = "DESCRIPTION", nullable = true, length = 200)
    private String description;

    @Column(name = "STARTED_YEAR", nullable = true)
    @Temporal(value = TemporalType.DATE)
    private Date started_year;

    @Column(name = "ENDED_YEAR", nullable = true)
    @Temporal(value = TemporalType.DATE)
    private Date ended_year;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Person person;

}
