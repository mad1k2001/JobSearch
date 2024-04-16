package com.example.jobsearch.service;

import com.example.jobsearch.dto.ImageDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.UserProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    ResponseEntity<?> getUserByEmail(String email);
    ResponseEntity<List<UserDto>> getUsersByName(String name);
    ResponseEntity<?> getUsersByPhoneNumber(String phone);
    Boolean userExistsByEmail(String email);
    Long addUser(UserDto userDto);
    void editUser(UserDto userDto, Long id, ImageDto imageDto, Authentication auth);
    void upload(ImageDto imageDto, Authentication authentication);
    UserProfileDto getUser(Authentication authentication);
}