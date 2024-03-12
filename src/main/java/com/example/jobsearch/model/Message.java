package com.example.jobsearch.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private Long id;
    private Long respondedAppId;
    private String content;
    private LocalDateTime timestamp;
}
