package com.example.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RespondedApplicationDto {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
