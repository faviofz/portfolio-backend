package com.example.portfolio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 20)
    private String first_name;

    @Column(name = "LAST_NAME", nullable = false, length = 20)
    private String last_name;

    @Column(name = "URL_PROFILE_IMAGE", nullable = true, length = 100)
    private String url_profile_image;

    @Column(name = "URL_BANNER_IMAGE", nullable = true, length = 100)
    private String url_banner_image;

    @Column(name = "ABOUT_ME", nullable = true, length = 400)
    private String about_me;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUrl_profile_image() {
        return url_profile_image;
    }

    public void setUrl_profile_image(String url_profile_image) {
        this.url_profile_image = url_profile_image;
    }

    public String getUrl_banner_image() {
        return url_banner_image;
    }

    public void setUrl_banner_image(String url_banner_image) {
        this.url_banner_image = url_banner_image;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

}
