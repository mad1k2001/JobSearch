package com.example.jobsearch.model;

import java.time.LocalDate;

public class Vacancy {
    private Long id;
    private String name;
    private String description;
    private Integer categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActivate;
    private Integer authorId;
    private LocalDate createdDate;
    private LocalDate updateTime;

}
