package com.example.portfolio.service;

import com.example.portfolio.entity.Education;
import com.example.portfolio.repository.EducationRepository;
import com.example.portfolio.serviceInterfaces.IEducationService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService implements IEducationService {

  @Autowired
  private EducationRepository educationRepository;

  @Override
  public List<Education> getAllEducations() {
    return this.educationRepository.findAll();
  }

  @Override
  public void saveEducation(Education education) {
    this.educationRepository.save(education);
  }

  @Override
  public void deleteEducation(Long id) {
    this.educationRepository.delete(this.findEducationById(id));
  }

  @Override
  public Education findEducationById(Long id) {
    return this.educationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
