package com.example.jobsearch.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
