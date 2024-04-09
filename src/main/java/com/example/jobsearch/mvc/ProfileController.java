package com.example.jobsearch.mvc;

import com.example.jobsearch.dto.ImageDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.UserProfileDto;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping
    public String updateProfile(UserDto updatedUser, ImageDto imageDto, Authentication authentication) {
        userService.editUser(updatedUser, imageDto, authentication);
        return "profile";
    }
}
