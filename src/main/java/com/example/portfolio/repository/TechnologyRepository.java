package com.example.portfolio.repository;

import com.example.portfolio.entity.Technology;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
  
  List<Technology> findByPersonId(Long person_id);
}
