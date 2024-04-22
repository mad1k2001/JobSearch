package com.example.jobsearch.mvc;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.model.Category;
import com.example.jobsearch.model.Vacancy;
import com.example.jobsearch.service.CategoryService;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vacancy")
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
        return "vacancy/createVacancy";
    }

    @PostMapping
    public String createVacancy(VacancyDto vacancyDto, Authentication authentication) {
        Long authorId = userService.getUser(authentication).getId();
        vacancyService.addVacancy(vacancyDto, authorId);
        return "redirect:/profile";
    }

    @GetMapping("/{id}")
    public String showVacancy(@PathVariable Long id, Model model) {
        Optional<VacancyDto> vacancyOptional = vacancyService.getVacancyById(id);
        if (vacancyOptional.isPresent()) {
            VacancyDto vacancy = vacancyOptional.get();
            model.addAttribute("vacancy", vacancy);
        }
        return "vacancy/vacancies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<VacancyDto> vacancyOptional = vacancyService.getVacancyById(id);
        if (vacancyOptional.isPresent()) {
            VacancyDto vacancy = vacancyOptional.get();
            model.addAttribute("vacancy", vacancy);
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "vacancy/editVacancy";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/edit/{id}")
    public String editVacancy(Authentication authentication, @PathVariable Long id, VacancyDto vacancyDto) {
        Long authorId = userService.getUser(authentication).getId();
        vacancyService.editVacancy(authorId, id, vacancyDto);
        return "redirect:/vacancy/" + id;
    }

}
