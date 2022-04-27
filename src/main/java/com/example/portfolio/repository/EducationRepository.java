package com.example.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portfolio.entity.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

}
