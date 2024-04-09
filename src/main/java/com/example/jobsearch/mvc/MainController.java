package com.example.jobsearch.mvc;

import com.example.jobsearch.dao.VacancyDao;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.model.Vacancy;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;

    @GetMapping
    public String showVacancies(Model model, Authentication authentication) {
        model.addAttribute("user", userService.getUser(authentication));
        List<VacancyDto> vacancies = vacancyService.getVacancies();
        List<ResumeDto> resumes = resumeService.getResume();
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("resumes", resumes);
        return "index";
    }
}
