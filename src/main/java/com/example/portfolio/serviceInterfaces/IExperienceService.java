package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.entity.Experience;
import java.util.List;

public interface IExperienceService {

  List<Experience> getAllExperiences();

  void saveExperience(Experience experience);

  void deleteExperience(Long id);

  Experience findExperienceById(Long id);

}
