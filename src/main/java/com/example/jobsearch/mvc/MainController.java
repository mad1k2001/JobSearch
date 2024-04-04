package com.example.jobsearch.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main")

public class MainController {

    @GetMapping("hi")
    public String index(Model model){
        String str = "world!";
        model.addAttribute("world", str );

        return "index";
    }
}
