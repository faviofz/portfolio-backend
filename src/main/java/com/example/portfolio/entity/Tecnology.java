package com.example.portfolio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TECNOLOGIES")
@Data @NoArgsConstructor @AllArgsConstructor
public class Tecnology {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NAME", length = 50)
    private String name;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Person person;
}
