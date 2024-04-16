package com.example.jobsearch.mvc;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.model.Category;
import com.example.jobsearch.service.CategoryService;
import com.example.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class CreateResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;

    @GetMapping
    public String showCreateForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("resume", new ResumeDto());
        return "resume/createResume";
    }

    @GetMapping("/{id}")
    public String showVacancy(@PathVariable Long id, Model model) {
        Optional<ResumeDto> resumeOptional = resumeService.getResumeById(id);
        if (resumeOptional.isPresent()) {
            ResumeDto resume = resumeOptional.get();
            model.addAttribute("resume", resume);
        }
        return "resume/resumes";
    }
}
