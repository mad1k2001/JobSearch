package com.example.jobsearch.controller;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vacancies")
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("")
    public ResponseEntity<List<VacancyDto>> getVacancies() {
        return ResponseEntity.ok(vacancyService.getVacancies());
    }

    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@PathVariable Long categoryId) {
        List<VacancyDto> vacancies = vacancyService.getVacanciesByCategory(categoryId);
        if (vacancies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(vacancies);
    }
    @PostMapping("add")
    public HttpStatus addResume(@RequestBody VacancyDto vacancyDto) {
        vacancyService.addVacancy(vacancyDto);
        return HttpStatus.OK;
    }

    @PutMapping("/edit/{id}")
    public HttpStatus editResume(@PathVariable Long id, @RequestBody VacancyDto vacancyDto) {
        vacancyDto.setId(id);
        vacancyService.editVacancy(vacancyDto);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteResume(@PathVariable Long id) {
        vacancyService.deleteVacancy(id);
        return HttpStatus.OK;
    }
}