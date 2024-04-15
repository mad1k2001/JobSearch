package com.example.jobsearch.api;

import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vacancies")
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.getVacancyById(id));

    }

    @PostMapping("add/{authorId}")
    public ResponseEntity<?> addVacancy(@PathVariable Long authorId, VacancyDto vacancyDto) {
        vacancyService.addVacancy(vacancyDto, authorId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("edit/{authorId}/{vacancyId}")
    public ResponseEntity<?> editVacancy(@PathVariable Long authorId, @PathVariable Long vacancyId, VacancyDto vacancyDto ){
        vacancyService.editVacancy(authorId, vacancyId, vacancyDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("delete/{authorId}/vacancies/{vacancyId}")
    public ResponseEntity<?> delete(@PathVariable Long authorId, @PathVariable Long vacancyId){
        vacancyService.deleteVacancy(authorId, vacancyId);
        return ResponseEntity.noContent().build();
    }
}
