package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.entity.Education;
import java.util.List;

public interface IEducationService {

  List<Education> getAllEducations();

  void saveEducation(Education education);

  void deleteEducation(Long id);

  Education findEducationById(Long id);

}
