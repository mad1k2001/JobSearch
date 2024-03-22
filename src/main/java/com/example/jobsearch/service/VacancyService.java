package com.example.jobsearch.service;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.exeptions.VacancyNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface VacancyService {
    List<VacancyDto> getVacancies();
    List<VacancyDto> getVacanciesByCategory(Long categoryId);
    Optional<VacancyDto> getVacancyById(Long id) throws NoSuchElementException;
    void editVacancy(Long authorId, Long vacancyId, VacancyDto vacancyDto);
    Long addVacancy(VacancyDto vacancy, Long authorId);
    void deleteVacancy(Long authorId, Long vacancyId);
}
