package com.example.jobsearch.service;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.exeptions.ResumeNotFoundException;
import com.example.jobsearch.model.Category;
import com.example.jobsearch.model.Resume;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ResumeService {
    List<ResumeDto> getResume();
    List<ResumeDto> getResumeByCategory(Long categoryId);
    List<ResumeDto> getResumeByApplicantId(Long applicantId);
    Optional<ResumeDto> getResumeById(Long id) throws NoSuchElementException;
    void editResume(ResumeDto resumeDto, Long applicantId, Long resumeId);
    Long addResume(ResumeDto resume, Long applicantId);
    void deleteResume(Long applicantId, Long resumeId);
    List<Resume> getResumesByApplicant(Authentication authentication);
    List<Category> getAllCategories();
}
