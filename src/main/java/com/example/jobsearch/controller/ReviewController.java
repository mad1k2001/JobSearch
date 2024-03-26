package com.example.jobsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {

    @GetMapping
    public HttpStatus getReviews(){
        return HttpStatus.OK;
    }
}
