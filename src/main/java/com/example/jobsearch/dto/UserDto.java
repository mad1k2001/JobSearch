package com.example.jobsearch.dto;

import com.example.jobsearch.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String surname;
    @NotNull
    @NotBlank
    @Min(18)
    @Max(120)
    private Integer age;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 6, max = 255)
    private String password;
    @Pattern(regexp="\\+\\d{11}")
    private String phoneNumber;
    @Pattern(regexp=".+\\.jpg$")
    @Size(max = 2097152)
    private String avatar;
    @NotNull
    @NotBlank
    private AccountType accountType;
}
