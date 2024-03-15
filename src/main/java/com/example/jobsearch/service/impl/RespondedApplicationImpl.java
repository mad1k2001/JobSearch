package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.RespondedApplicationDao;
import com.example.jobsearch.dto.RespondedApplicationDto;
import com.example.jobsearch.model.RespondedApplication;
import com.example.jobsearch.service.RespondedApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RespondedApplicationImpl implements RespondedApplicationService {
    private final RespondedApplicationDao respondedApplicationDao;

    @Override
    public List<RespondedApplicationDto> getRespondedVacanciesForApplicant(Long applicantId) {
        List<RespondedApplication> respondedApplications = respondedApplicationDao.getRespondedVacanciesForApplicant(applicantId);
        return respondedApplications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RespondedApplicationDto> getApplicationsByVacancy(Long vacancyId) {
        List<RespondedApplication> respondedApplications = respondedApplicationDao.getApplicationsByVacancy(vacancyId);
        return respondedApplications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private RespondedApplicationDto mapToDto(RespondedApplication respondedApplication) {
        return RespondedApplicationDto.builder()
                .id(respondedApplication.getId())
                .resumeId(respondedApplication.getResumeId())
                .vacancyId(respondedApplication.getVacancyId())
                .confirmation(respondedApplication.getConfirmation())
                .build();
    }
}
