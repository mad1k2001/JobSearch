package com.example.jobsearch.service;

import com.example.jobsearch.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    ResponseEntity<?> getUsersByEmail(String email);
    List<UserDto> getApplicantsForVacancy(Long vacancyId);
    ResponseEntity<List<UserDto>> getUsersByParams(String name, String phone);
}
