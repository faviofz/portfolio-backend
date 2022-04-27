package com.example.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portfolio.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}