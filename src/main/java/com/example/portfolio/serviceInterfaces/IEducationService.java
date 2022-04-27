package com.example.portfolio.serviceInterfaces;

import java.util.List;

import com.example.portfolio.entity.Education;

public interface IEducationService {

    public List<Education> getAllEducations();
    
    public void saveEducation(Education education);
    
    public void deleteEducation(Long id);
    
    public Education findEducationById(Long id);

}
