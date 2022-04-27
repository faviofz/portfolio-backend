package com.example.portfolio.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portfolio.entity.Experience;
import com.example.portfolio.repository.ExperienceRepository;
import com.example.portfolio.serviceInterfaces.IExperienceService;

@Service
public class ExperienceService implements IExperienceService {

	@Autowired
	private ExperienceRepository experienceRepository;

	@Override
	public List<Experience> getAllExperiences() {
		return this.experienceRepository.findAll();
	}

	@Override
	public void saveExperience(Experience experience) {
		this.experienceRepository.save(experience);
	}

	@Override
	public void deleteExperience(Long id) {
		this.experienceRepository.deleteById(id);
	}

	@Override
	public Experience findExperienceById(Long id) {
		return this.experienceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Experience no encontrada " + id));
	}
	
	public List<Experience> findExperiencesByPersonId(Long PersonId){
		return this.experienceRepository.findByPersonId(PersonId);
	}

}
