package com.example.jobsearch.model;

import com.example.jobsearch.enums.AccountType;
import lombok.*;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private AccountType accountType;
}
