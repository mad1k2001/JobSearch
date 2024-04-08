package com.example.jobsearch.mvc;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.model.Category;
import com.example.jobsearch.model.Resume;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/create-resume")
@RequiredArgsConstructor
public class CreateResumeController {
    private final UserService userService;
    private final ResumeService resumeService;

    @GetMapping
    public String showCreateForm(Model model) {
        List<Category> categories = resumeService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("resume", new ResumeDto());
        return "createResume";
    }


    @PostMapping
    public String createResume(ResumeDto resume, Authentication authentication, Model model) {
        ResponseEntity<?> userResponse = userService.getUserByEmail(authentication.getName());
        if (userResponse.getStatusCode().isError()) {
            return "error";
        }

        User user = (User) userResponse.getBody();
        Long applicantId = user.getId();

        resumeService.addResume(resume, applicantId);

        return "redirect:/profile";
    }
}
