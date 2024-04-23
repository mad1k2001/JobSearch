package com.example.jobsearch.mvc;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.model.Category;
import com.example.jobsearch.service.CategoryService;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class CreateResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;
    private final UserService userService;
    private static final String PAGE_TITLE = "Resume";

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("resume", new ResumeDto());
        return "/createResume";
    }

    @PostMapping("/create")
    public String createResume(ResumeDto resumeDto, Authentication authentication) {
        Long authorId = userService.getUser(authentication).getId();
        resumeService.addResume(resumeDto, authorId);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showVacancy(@PathVariable Long id, Model model) {
        Optional<ResumeDto> resumeOptional = resumeService.getResumeById(id);
        if (resumeOptional.isPresent()) {
            ResumeDto resume = resumeOptional.get();
            model.addAttribute("resume", resume);
        }
        return "/resumes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<ResumeDto> resumeOptional = resumeService.getResumeById(id);
        if (resumeOptional.isPresent()) {
            ResumeDto resume = resumeOptional.get();
            model.addAttribute("resume", resume);
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "/editResume";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/edit/{id}")
    public String editResume(ResumeDto resumeDto, Authentication authentication, Long id) {
        Long authorId = userService.getUser(authentication).getId();
        resumeService.editResume(resumeDto, authorId, id);
        return "redirect:/resume/" + id;
    }

    @GetMapping()
    public String resumesGet(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page, Authentication authentication) {
        model.addAttribute("user", userService.getUser(authentication));
        model.addAttribute(PAGE_TITLE, "Resume");
        model.addAttribute("page", page);
        model.addAttribute("resumes", resumeService.getActiveResumes(page));
        model.addAttribute("categories",  categoryService.getAllCategories());
        return "resumeEmployer";
    }
}
