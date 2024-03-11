package com.example.jobsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespondedApplication {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
