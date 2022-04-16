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

@Entity
@Table(name = "EDUCATION")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStarted_year() {
        return started_year;
    }

    public void setStarted_year(Date started_year) {
        this.started_year = started_year;
    }

    public Date getEnded_year() {
        return ended_year;
    }

    public void setEnded_year(Date ended_year) {
        this.ended_year = ended_year;
    }
    
}
