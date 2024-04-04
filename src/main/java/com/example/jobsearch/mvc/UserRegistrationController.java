package com.example.jobsearch.mvc;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @GetMapping("/create")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String registerUser(UserDto user) {
        userService.addUser(user);

        if (user.getAccountType().equals("employee")) {
            return "redirect:/employee/dashboard";
        } else if (user.getAccountType().equals("employer")) {
            return "redirect:/employer/dashboard";
        } else {
            return "redirect:/login";
        }
    }
}

