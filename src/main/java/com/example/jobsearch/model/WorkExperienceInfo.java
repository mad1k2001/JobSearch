package com.example.jobsearch.model;


import lombok.*;

@Getter
@Setter
public class WorkExperienceInfo {
    private Long id;
    private Long resumeId;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibility;
}

