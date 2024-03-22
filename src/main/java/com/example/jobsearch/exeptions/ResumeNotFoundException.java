package com.example.jobsearch.exeptions;

public class ResumeNotFoundException  extends Exception{
    public ResumeNotFoundException() {
    }

    public ResumeNotFoundException(String message) {
        super(message);
    }
}
