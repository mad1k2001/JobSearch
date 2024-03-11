package com.example.jobsearch.controller;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("users/name/{name}")
    public ResponseEntity<UserDto> getUsersByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getUsersByName(name));
    }

    @GetMapping("users/phone/{phoneNumber}")
    public ResponseEntity<UserDto> getUsersByPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.ok(userService.getUsersByPhoneNumber(phoneNumber));
    }

    @GetMapping("users/email/{email}")
    public ResponseEntity<UserDto> getUsersByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUsersByEmail(email));
    }
}
