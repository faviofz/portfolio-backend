package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.dto.EducationDto;
import java.util.List;

public interface IEducationService {
  
  List<EducationDto> getAllEducations();
  
  void saveEducation(EducationDto educationDto);
  
  void deleteEducation(Long id);
  
  EducationDto findEducationById(Long id);
  
}
