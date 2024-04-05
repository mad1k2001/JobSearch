package com.example.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private Long id;
    private Integer age;
    private String phoneNumber;
    private String email;
    private String password;
    private String accountType;
    private String avatar;
    private String surName;
    private String name;
}
