package com.example.jobsearch.service;

import com.example.jobsearch.dto.ResumeDto;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResume();
    List<ResumeDto> getResumeByCategory(Long categoryId);
    List<ResumeDto> getResumeByApplicantId(Long applicantId);
}
