package com.example.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXPERIENCES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE", nullable = false, length = 50)
	private String title;

	@Column(name = "DESCRIPTION", nullable = true, length = 200)
	private String description;

	@Column(name = "STARTED_YEAR", nullable = true)
	@Temporal(value = TemporalType.DATE)
	private Date started_year;

	@Column(name = "ENDED_YEAR", nullable = true)
	@Temporal(value = TemporalType.DATE)
	private Date ended_year;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;

	public Experience(String title, String description, Date started_year, Date ended_year) {
		super();
		this.title = title;
		this.description = description;
		this.started_year = started_year;
		this.ended_year = ended_year;
	}

}
