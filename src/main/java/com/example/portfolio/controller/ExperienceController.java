package com.example.portfolio.controller;

import com.example.portfolio.dto.ExperienceDto;
import com.example.portfolio.entity.Experience;
import com.example.portfolio.service.ExperienceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(ExperienceController.EXPERIENCES)
@RestController
public class ExperienceController {

  public static final String EXPERIENCES = "/experiences";

  public static final String ID = "/{id}";

  public static final String CREATE = "/create";

  public static final String EDIT = "/edit";

  public static final String DELETE = "/delete";

  @Autowired
  private ExperienceService experienceService;

  @GetMapping()
  public List<Experience> findExperiencesOfPerson(@RequestParam(required = false) Long person_id) {
    return this.experienceService.findExperiencesByPersonId(person_id);
  }

  @GetMapping(value = ID)
  public Experience findExperienceById(@PathVariable Long id) {
    return this.experienceService.findExperienceById(id);
  }

  @PostMapping(value = CREATE)
  public void createExperience(@RequestBody ExperienceDto experienceDTO) {
    this.experienceService.saveExperience(new Experience(
        experienceDTO.getTitle(),
        experienceDTO.getDescription(),
        experienceDTO.getStarted_year(),
        experienceDTO.getEnded_year()));
  }

  @PutMapping(value = EDIT + ID)
  public void editExperience(@RequestBody ExperienceDto experienceDTO, @PathVariable Long id) {
    Experience experience = this.experienceService.findExperienceById(id);
    experience.setTitle(experienceDTO.getTitle());
    experience.setDescription(experienceDTO.getDescription());
    experience.setStarted_year(experienceDTO.getStarted_year());
    experience.setEnded_year(experienceDTO.getEnded_year());
    this.experienceService.saveExperience(experience);
  }

  @DeleteMapping(value = DELETE + ID)
  public void deleteExperience(@PathVariable Long id) {
    this.experienceService.deleteExperience(id);
  }

}
