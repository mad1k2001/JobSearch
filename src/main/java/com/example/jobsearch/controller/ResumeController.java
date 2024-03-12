package com.example.jobsearch.controller;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("resume")
    public ResponseEntity<List<ResumeDto>> getResume() {
        return ResponseEntity.ok(resumeService.getResume());
    }

    @GetMapping("resume/categoryId/{categoryId}")
    public ResponseEntity<List<ResumeDto>> getResumeByCategory(@PathVariable Long categoryId) {
        List<ResumeDto> resumes = resumeService.getResumeByCategory(categoryId);
        if (resumes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("resume/applicantId/{applicantId}")
    public ResponseEntity<List<ResumeDto>> getResumeByApplicantId(@PathVariable Long applicantId) {
        List<ResumeDto> resumes = resumeService.getResumeByApplicantId(applicantId);
        if (resumes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(resumes);
    }
}