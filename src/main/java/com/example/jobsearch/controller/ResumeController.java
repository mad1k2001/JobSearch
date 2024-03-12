package com.example.jobsearch.controller;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<ResumeDto>> getResume(){
        return ResponseEntity.ok(resumeService.getResume());
    }

    @GetMapping("resume/categoryId/{categoryId}")
    public ResponseEntity<List<ResumeDto>> getResumeByCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(resumeService.getResumeByCategory(categoryId));
    }

    @GetMapping("resume/applicantId/{applicantId}")
    public ResponseEntity<List<ResumeDto>> getResumeByApplicantId(@PathVariable Long applicantId){
        return ResponseEntity.ok(resumeService.getResumeByApplicantId(applicantId));
    }

}
