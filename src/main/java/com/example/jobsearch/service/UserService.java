package com.example.jobsearch.service;

import com.example.jobsearch.dto.ImageDto;
import com.example.jobsearch.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> getUserByEmail(String email);
    ResponseEntity<List<UserDto>> getUsersByName(String name);
    ResponseEntity<?> getUsersByPhoneNumber(String phone);
    Boolean userExistsByEmail(String email);
    Long addUser(UserDto userDto);
    void editUser(UserDto userDto, Long id, ImageDto imageDto);
    void upload(ImageDto imageDto, Long id);
}
