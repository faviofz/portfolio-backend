package com.example.portfolio.controller;

import com.example.portfolio.entity.Education;
import com.example.portfolio.service.EducationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EducationController.EDUCATIONS)
public class EducationController {

  public static final String EDUCATIONS = "/educations";

  @Autowired
  private EducationService educationService;

  public static final String ID = "/{id}";

  public static final String CREATE = "/create";

  public static final String EDIT = "/edit";

  public static final String DELETE = "/delete";

  @GetMapping
  public ResponseEntity<List<Education>> getAllEducations() {
    return new ResponseEntity<>(this.educationService.getAllEducations(), HttpStatus.OK);
  }

  @GetMapping(value = ID)
  public ResponseEntity<Education> getEducation(@PathVariable Long id) {
    return new ResponseEntity<>(this.educationService.findEducationById(id), HttpStatus.OK);
  }
}
