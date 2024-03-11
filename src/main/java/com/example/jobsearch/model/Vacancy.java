package com.example.jobsearch.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Vacancy {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActivate;
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

}
