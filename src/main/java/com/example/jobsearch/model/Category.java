package com.example.jobsearch.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {
    private Long id;
    private String name;
    private Long parentId;

}
