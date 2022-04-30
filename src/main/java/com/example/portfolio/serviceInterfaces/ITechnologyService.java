package com.example.portfolio.serviceInterfaces;

import com.example.portfolio.entity.Technology;
import java.util.List;

public interface ITechnologyService {

  List<Technology> getAllTechnologies();

  void saveTechnology(Technology technology);

  void deleteTechnology(Long id);

  Technology findTechnologyById(Long id);
}
