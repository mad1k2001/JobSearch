package com.example.jobsearch.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NotBlank
@NotNull
public class ResumeDto {
    private Long id;
    private Long applicantId;
    private String name;
    private Long categoryId;
    @DecimalMin(value = "0.0")
    private Double salary;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    @Valid
    private List<@Valid WorkExperienceInfoDto> workExperienceList;
    @Valid
    private List<@Valid EducationInfoDto> educationList;
    @Valid
    private List<@Valid ContactInfoDto> contactInfo;
}
