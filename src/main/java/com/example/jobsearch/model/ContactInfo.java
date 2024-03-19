package com.example.jobsearch.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactInfo {
    private Long id;
    private Long typeId;
    private Long resumeId;
    private String contactValue;
}
