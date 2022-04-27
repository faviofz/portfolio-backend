package com.example.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String first_name;

    private String last_name;

    private String url_profile_image;

    private String url_banner_image;

    private String about_me;

}
