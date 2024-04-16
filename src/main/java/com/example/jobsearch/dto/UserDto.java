package com.example.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String surName;
    @NotNull
    @NotBlank
    @Min(18)
    @Max(120)
    private Integer age;
    @NotBlank
    private @Email String email;
    @NotBlank
    @Size(min = 4, max = 24)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]) (?=.*[a-zA-Z]).+$")
    private String password;
    @Pattern(regexp="\\+\\d{11}")
    private String phoneNumber;
    @Pattern(regexp=".+\\.jpg$")
    @Size(max = 2097152)
    private String avatar;
    private Boolean enabled;
    @NotNull
    @NotBlank
    private Long accountType;
}
