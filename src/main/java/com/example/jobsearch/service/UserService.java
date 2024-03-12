package com.example.jobsearch.service;

import com.example.jobsearch.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> getUserByEmail(String email);
    List<UserDto> getApplicantsForVacancy(Long vacancyId);
    ResponseEntity<List<UserDto>> getUsersByParams(String name, String phone);
    Boolean userExistsByEmail(String email);
}
