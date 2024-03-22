package com.example.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NotNull
@NotBlank
public class WorkExperienceInfoDto {
    private Long id;
    private Long resumeId;
    @Min(0)
    private Integer years;
    private String companyName;
    private String position;
    private String responsibility;
}
