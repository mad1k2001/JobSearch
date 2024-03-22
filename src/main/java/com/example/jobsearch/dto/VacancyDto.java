package com.example.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NotNull
@NotBlank
public class VacancyDto {
    private Long id;
    @Size(min = 2, max = 50)
    private String name;
    @Size(min = 2, max = 50)
    private String description;
    private Long categoryId;
    @DecimalMin(value = "0.0")
    private Double salary;
    @Min(value = 0)
    private Integer expFrom;
    @Min(value = 0)
    @Max(value = 60)
    private Integer expTo;
    private Boolean isActivate;
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
