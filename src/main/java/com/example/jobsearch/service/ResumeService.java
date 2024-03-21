package com.example.jobsearch.service;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.exeptions.ResumeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    List<ResumeDto> getResume();
    List<ResumeDto> getResumeByCategory(Long categoryId);
    List<ResumeDto> getResumeByApplicantId(Long applicantId);
    Optional<ResumeDto> getResumeById(Long id) throws ResumeNotFoundException;
    List<ResumeDto> findResumeByPosition(String position);
    void editResume(ResumeDto resumeDto, Long applicantId, Long resumeId);
    void addResume(ResumeDto resume, Long applicantId);
    void deleteResume(Long applicantId, Long resumeId);

}
