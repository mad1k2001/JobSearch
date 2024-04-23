package com.example.jobsearch.mvc;

import com.example.jobsearch.dao.VacancyDao;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.model.Vacancy;
import com.example.jobsearch.service.CategoryService;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final CategoryService categoryService;
    private static final String PAGE_TITLE = "Resume";
    private static final String PAGE_TITLE_V = "Vacancy";

    @GetMapping()
    public String resumesGet(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        model.addAttribute(PAGE_TITLE_V, "Vacancy");
        model.addAttribute("page", page);
        model.addAttribute("vacancies", vacancyService.getActiveVacancies(page));
        model.addAttribute("categories",  categoryService.getAllCategories());
        return "index";
    }
}
