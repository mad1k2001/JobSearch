package com.example.jobsearch.service;

import com.example.jobsearch.dto.RespondedApplicationDto;

import java.util.List;

public interface RespondedApplicationService {
    List<RespondedApplicationDto> getRespondedVacanciesForApplicant(Long applicantId);
    List<RespondedApplicationDto> getApplicationsByVacancy(Long vacancyId);
}
