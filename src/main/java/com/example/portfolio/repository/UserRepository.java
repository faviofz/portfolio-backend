package com.example.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portfolio.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long>{

    MyUser findByUsername(String username);
}
