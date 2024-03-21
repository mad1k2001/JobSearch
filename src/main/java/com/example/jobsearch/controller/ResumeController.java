package com.example.jobsearch.controller;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.exeptions.ResumeNotFoundException;
import com.example.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("")
    public ResponseEntity<List<ResumeDto>> getResume() {
        return ResponseEntity.ok(resumeService.getResume());
    }

    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity<List<ResumeDto>> getResumeByCategory(@PathVariable Long categoryId) {
        List<ResumeDto> resumes = resumeService.getResumeByCategory(categoryId);
        if (resumes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/applicantId/{applicantId}")
    public ResponseEntity<List<ResumeDto>> getResumeByApplicantId(@PathVariable Long applicantId) {
        List<ResumeDto> resumes = resumeService.getResumeByApplicantId(applicantId);
        if (resumes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(resumeService.getResumeById(id));
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("applicants/{applicantId}/resumes")
    public ResponseEntity<?> addResume(@PathVariable Long applicantId, @RequestBody ResumeDto resumeDto) {
        resumeService.addResume(resumeDto, applicantId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("applicants/{applicantId}/resumes/{resumeId}")
    public ResponseEntity<?> update(@PathVariable Long applicantId, @PathVariable Long resumeId, @RequestBody ResumeDto resumeDto) {
        resumeService.editResume(resumeDto, applicantId, resumeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("applicants/{applicantId}/resumes/{resumeId}")
    public ResponseEntity<?> delete(@PathVariable Long applicantId, @PathVariable Long resumeId) {
        resumeService.deleteResume(applicantId, resumeId);
        return ResponseEntity.noContent().build();
    }
}