package com.example.jobsearch.api;

import com.example.jobsearch.dto.ImageDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
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
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDto userDto) {
        if (userService.userExistsByEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        } else {
            return ResponseEntity.badRequest().body("Can't create user");
        }
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<UserDto>> getApplicantsByAccountType(@RequestParam(name = "accountType") String accountType) {
        return ResponseEntity.ok(userService.getApplicantsByAccountType(accountType.strip()));
    }

    @GetMapping("/employers")
    public ResponseEntity<?> getEmployers() {
        try {
            List<UserDto> employers = userService.getEmployersByAccountType("employer");
            return ResponseEntity.ok(employers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get employers: " + e.getMessage());
        }
    }
}
