package com.example.jobsearch.mvc;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.model.Category;
import com.example.jobsearch.service.CategoryService;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/create-resume")
@RequiredArgsConstructor
public class CreateResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;

    @GetMapping
    public String showCreateForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("resume", new ResumeDto());
        return "createResume";
    }

//    @GetMapping()
//    public String resumesGet(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page) {
//        model.addAttribute(PAGE_TITLE, "Резюме");
//        model.addAttribute("page", page);
//        model.addAttribute("resumes", resumeService.getActiveResumes(page));
//        model.addAttribute("categories",  categoryService.getCategoriesList());
//        return "resume/resumes";
//    }
}
