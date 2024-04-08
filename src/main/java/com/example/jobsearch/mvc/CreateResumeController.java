package com.example.jobsearch.mvc;

import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/create-resume")
@RequiredArgsConstructor
public class CreateResumeController {
    private final UserService userService;
    private final ResumeService resumeService;

}
