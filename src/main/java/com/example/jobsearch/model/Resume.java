package com.example.jobsearch.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Resume {
    private Long id;
    private Long applicantId;
    private String name;
    private Long categoryId;
    private Double salary;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
