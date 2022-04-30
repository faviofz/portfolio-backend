package com.example.portfolio.service;

import com.example.portfolio.entity.Technology;
import com.example.portfolio.repository.TechnologyRepository;
import com.example.portfolio.serviceInterfaces.ITechnologyService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnologyService implements ITechnologyService {

  @Autowired
  private TechnologyRepository technologyRepository;

  @Override
  public List<Technology> getAllTechnologies() {
    return this.technologyRepository.findAll();
  }

  @Override
  public void saveTechnology(Technology technology) {
    this.technologyRepository.save(technology);
  }

  @Override
  public void deleteTechnology(Long id) {
    this.technologyRepository.delete(this.findTechnologyById(id));
  }

  @Override
  public Technology findTechnologyById(Long id) {
    return this.technologyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
