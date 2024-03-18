package com.example.jobsearch.service;

import com.example.jobsearch.dto.ResumeDto;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    List<ResumeDto> getResume();
    List<ResumeDto> getResumeByCategory(Long categoryId);
    List<ResumeDto> getResumeByApplicantId(Long applicantId);
    Optional<ResumeDto> getResumeById(Long id);
    void editResume(ResumeDto resume);
    Long addResume(ResumeDto resume);
    void deleteResume(Long id);
}
