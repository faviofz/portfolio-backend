package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.dto.ExperienceDto;
import java.util.List;

public interface IExperienceService {
  
  List<ExperienceDto> getAllExperiences();
  
  void saveExperience(ExperienceDto experienceDto);
  
  void deleteExperience(Long id);
  
  ExperienceDto findExperienceById(Long id);
  
}
