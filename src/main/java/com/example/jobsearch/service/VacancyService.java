package com.example.jobsearch.service;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getVacancies();
    List<VacancyDto> getVacanciesByCategory(Long categoryId);
    void editVacancy(VacancyDto vacancy);
    Long addVacancy(VacancyDto vacancy);
    void deleteVacancy(Long id);
}
