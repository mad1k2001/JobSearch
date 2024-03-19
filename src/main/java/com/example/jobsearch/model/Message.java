package com.example.jobsearch.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {
    private Long id;
    private Long respondedAppId;
    private String content;
    private LocalDateTime timestamp;
}
