package com.example.jobsearch.controller;

import com.example.jobsearch.dto.ImageDto;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.model.User;
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
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto, ImageDto imageDto) {
        if (userService.userExistsByEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        }
        Long id = userService.addUser(userDto);
        if (id != 0){
            userService.upload(imageDto, id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't create user");
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<?> editUser(@PathVariable String email, @RequestBody UserDto userDto) {
        if (!userService.userExistsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        userDto.setEmail(email);
        userService.editUser(userDto);
        return ResponseEntity.ok().build();
    }
}