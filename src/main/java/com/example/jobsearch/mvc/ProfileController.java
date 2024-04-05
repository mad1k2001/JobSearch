package com.example.jobsearch.mvc;

import com.example.jobsearch.model.User;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profile(Model model, Authentication authentication){
        model.addAttribute("user", userService.getUser(authentication));
        model.addAttribute("resumes",resumeService.getResumesByApplicant(authentication));
        model.addAttribute("vacancies", vacancyService.getVacanciesByEmployer(authentication));
        return "profile";
    }
}
