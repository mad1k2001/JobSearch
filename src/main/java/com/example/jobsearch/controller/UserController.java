package com.example.jobsearch.controller;

import com.example.jobsearch.dto.ResumeDto;
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

    @GetMapping(params = "name")
    public ResponseEntity<List<UserDto>> getUsersByName(@RequestParam("name") String name) {
        return userService.getUsersByName(name);
    }

    @GetMapping(params = "phone")
    public ResponseEntity<?> getUserByPhoneNumber(@RequestParam("phone") String phoneNumber) {
        return userService.getUsersByPhoneNumber(phoneNumber);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> userExistsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.userExistsByEmail(email));
    }

    @PostMapping("add")
    public HttpStatus addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return HttpStatus.OK;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        userService.editUser(userDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}