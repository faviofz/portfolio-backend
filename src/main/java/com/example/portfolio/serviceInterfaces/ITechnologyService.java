package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.dto.TechnologyDto;
import java.util.List;

public interface ITechnologyService {
  
  List<TechnologyDto> getAllTechnologies();
  
  void saveTechnology(TechnologyDto technologyDto);
  
  void deleteTechnology(Long id);
  
  TechnologyDto findTechnologyById(Long id);
}
