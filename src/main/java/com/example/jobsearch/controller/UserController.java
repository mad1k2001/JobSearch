package com.example.jobsearch.controller;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("users/name/{name}")
    public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable String name) {
        List<UserDto> users = userService.getUsersByName(name);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("users/phone/{phoneNumber}")
    public ResponseEntity<UserDto> getUsersByPhoneNumber(@PathVariable String phoneNumber) {
        UserDto user = userService.getUsersByPhoneNumber(phoneNumber);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("users/email/{email}")
    public ResponseEntity<UserDto> getUsersByEmail(@PathVariable String email) {
        UserDto user = userService.getUsersByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("vacancies/applicants/{vacancyId}")
    public ResponseEntity<List<UserDto>> getApplicantsForVacancy(@PathVariable Long vacancyId) {
        List<UserDto> applicants = userService.getApplicantsForVacancy(vacancyId);
        if (applicants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(applicants);
    }
}