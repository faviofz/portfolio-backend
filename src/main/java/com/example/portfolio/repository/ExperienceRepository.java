package com.example.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portfolio.entity.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long>{
    
    List<Experience> findByPersonId(Long id);
    
}
