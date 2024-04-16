package com.example.jobsearch.api;

import com.example.jobsearch.dto.RespondedApplicationDto;
import com.example.jobsearch.service.RespondedApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/respondedApp")
public class RespondedApplicationController {
    private final RespondedApplicationService respondedApplicationService;

    @GetMapping("/respondedVacancies/{applicantId}")
    public ResponseEntity<List<RespondedApplicationDto>> getRespondedVacanciesForApplicant(@PathVariable Long applicantId) {
        List<RespondedApplicationDto> respondedVacancies = respondedApplicationService.getRespondedVacanciesForApplicant(applicantId);
        if (respondedVacancies.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(respondedVacancies);
    }

    @GetMapping("/respondedApplicants/{vacancyId}")
    public ResponseEntity<List<RespondedApplicationDto>> getApplicationsByVacancy(@PathVariable Long vacancyId) {
        List<RespondedApplicationDto> applications = respondedApplicationService.getApplicationsByVacancy(vacancyId);
        if (applications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(applications);
    }

}
