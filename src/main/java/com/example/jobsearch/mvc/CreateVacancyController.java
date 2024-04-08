package com.example.jobsearch.mvc;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.model.Category;
import com.example.jobsearch.service.CategoryService;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/create-vacancy")
@RequiredArgsConstructor
public class CreateVacancyController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping
    public String showVacancyForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("vacancy", new VacancyDto());
        return "createVacancy";
    }

    @PostMapping
    public String createVacancy(VacancyDto vacancyDto, Authentication authentication) {
        Long authorId = userService.getUser(authentication).getId();
        vacancyService.addVacancy(vacancyDto, authorId);
        return "redirect:/profile";
    }
}
