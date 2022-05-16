package com.example.portfolio.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USER")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "USERNAME", unique = true, nullable = false)
  private String username;
  
  @Column(name = "PASSWORD", nullable = false)
  private String password;
  
}