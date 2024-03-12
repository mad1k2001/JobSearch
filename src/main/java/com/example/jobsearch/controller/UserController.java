package com.example.jobsearch.controller;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam(value = "name", required = false)String name,
                                                  @RequestParam(value = "phone", required = false) String phone) {
        return userService.getUsersByParams(name, phone);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUsersByEmail(@PathVariable String email) {
//        UserDto user = userService.getUsersByEmail(email);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
        return userService.getUsersByEmail(email);
    }

    @GetMapping("/vacancy/{vacancyId}")
    public ResponseEntity<List<UserDto>> getApplicantsForVacancy(@PathVariable Long vacancyId) {
        List<UserDto> applicants = userService.getApplicantsForVacancy(vacancyId);
        if (applicants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(applicants);
    }
}