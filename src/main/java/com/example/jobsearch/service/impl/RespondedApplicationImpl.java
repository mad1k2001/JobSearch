package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.RespondedApplicationDao;
import com.example.jobsearch.dto.RespondedApplicationDto;
import com.example.jobsearch.enums.AccountType;
import com.example.jobsearch.model.RespondedApplication;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.RespondedApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RespondedApplicationImpl implements RespondedApplicationService {
    private final RespondedApplicationDao respondedApplicationDao;
    private User user;

    @Override
    public List<RespondedApplicationDto> getRespondedVacanciesForApplicant(Long applicantId) {
        if (user.getAccountType() != AccountType.EMPLOYER) {
            log.error("Only employers can view vacancies by applicants.");
        }
        List<RespondedApplication> respondedApplications = respondedApplicationDao.getRespondedVacanciesForApplicant(applicantId);
        return respondedApplications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RespondedApplicationDto> getApplicationsByVacancy(Long vacancyId) {
        if (user.getAccountType() != AccountType.EMPLOYER) {
            log.error("Only employers can view applicants by vacancies.");
        }
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
