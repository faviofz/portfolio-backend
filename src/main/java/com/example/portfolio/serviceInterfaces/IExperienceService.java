package com.example.portfolio.serviceInterfaces;

import java.util.List;

import com.example.portfolio.entity.Experience;

public interface IExperienceService {

    public List<Experience> getAllExperiences();
    
    public void saveExperience(Experience experience);
    
    public void deleteExperience(Long id);
    
    public Experience findExperienceById(Long id);

}
