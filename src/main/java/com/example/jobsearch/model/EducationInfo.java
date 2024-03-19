package com.example.jobsearch.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EducationInfo {
    private Long id;
    private Long resumeId;
    private String institution;
    private String program;
    private LocalDate startDate;
    private LocalDate endDate;
    private String degree;
}
