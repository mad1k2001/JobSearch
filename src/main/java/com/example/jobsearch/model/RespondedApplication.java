package com.example.jobsearch.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RespondedApplication {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
