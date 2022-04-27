package com.example.portfolio.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDTO {
	private Long id;

	private String title;

	private String description;

	private Date started_year;

	private Date ended_year;
}
