package com.example.jobsearch.controller;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
