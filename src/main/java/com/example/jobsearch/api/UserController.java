package com.example.jobsearch.api;

import com.example.jobsearch.dto.ImageDto;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping(params = "name")
    public ResponseEntity<?> getUsersByName(@RequestParam("name") String name) {
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body("Name parameter is required");
        }
        return userService.getUsersByName(name);
    }

    @GetMapping(params = "phone")
    public ResponseEntity<?> getUserByPhoneNumber(@RequestParam("phone") String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return ResponseEntity.badRequest().body("Phone parameter is required");
        }
        return userService.getUsersByPhoneNumber(phoneNumber);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email path variable is required");
        }
        return userService.getUserByEmail(email);
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> userExistsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.userExistsByEmail(email));
    }

    @PostMapping("add")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDto userDto, ImageDto imageDto, Authentication authentication) {
        if (userService.userExistsByEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        }
        if (userService.addUser(userDto) != 0) {
            userService.upload(imageDto, authentication);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Can't create user");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, Long id, ImageDto imageDto, Authentication auth) {
        userService.upload(imageDto, auth);
        userService.editUser(userDto, id, imageDto, auth);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}