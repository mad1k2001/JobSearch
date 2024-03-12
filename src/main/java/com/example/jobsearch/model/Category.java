package com.example.jobsearch.model;

import lombok.*;

@Getter
@Setter
public class Category {
    private Long id;
    private String name;
    private Long parentId;

}
