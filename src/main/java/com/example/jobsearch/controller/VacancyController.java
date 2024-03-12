package com.example.jobsearch.controller;

import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("vacancies")
    public ResponseEntity<List<VacancyDto>> getVacancies() {
        return ResponseEntity.ok(vacancyService.getVacancies());
    }

    @GetMapping("vacancies/categoryId/{categoryId}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@PathVariable Long categoryId) {
        List<VacancyDto> vacancies = vacancyService.getVacanciesByCategory(categoryId);
        if (vacancies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/vacancies/vacancyForApp/{userId}")
    public ResponseEntity<List<VacancyDto>> getVacanciesForUser(@PathVariable Long userId) {
        List<VacancyDto> vacancies = vacancyService.getVacanciesForUser(userId);
        if (vacancies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(vacancies);
    }
}