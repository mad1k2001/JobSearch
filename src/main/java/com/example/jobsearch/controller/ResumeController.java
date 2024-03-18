package com.example.jobsearch.controller;

import com.example.jobsearch.dto.ResumeDto;
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
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("add")
    public HttpStatus addResume(@RequestBody ResumeDto resumeDto) {
        resumeService.addResume(resumeDto);
        return HttpStatus.OK;
    }

    @PutMapping("/edit/{id}")
    public HttpStatus editResume(@PathVariable Long id, @RequestBody ResumeDto resumeDto) {
        resumeDto.setId(id);
        resumeService.editResume(resumeDto);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return HttpStatus.OK;
    }
}