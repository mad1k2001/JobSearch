package com.example.jobsearch.model;

import lombok.*;

@Getter
@Setter
public class RespondedApplication {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
